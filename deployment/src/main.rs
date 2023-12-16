use std::{ops::Deref, sync::Mutex, time::Duration};

use actix_web::{get, web, App, HttpResponse, HttpServer, Responder};
use const_format::concatcp;
use strum::IntoStaticStr;

const PORT: u16 = 8089;
const PROJECT_NAME: &str = "music-streaming-app";
const GIT_BRANCH: &str = "main";
const GH_USER: &str = "JohnScience";
const GH_REPO: &str = concatcp!("https://github.com/", GH_USER, "/", PROJECT_NAME, ".git");
const PROJECT_PATH: &str = concatcp!("./", PROJECT_NAME);

#[derive(PartialEq, IntoStaticStr)]
enum UpdateStatus {
    Ongoing,
    Pending,
    PendingAwaited,
    None,
}

struct AppState {
    update_status: Mutex<UpdateStatus>,
}

async fn update(data: web::Data<AppState>) -> std::io::Result<()> {
    let mut status = data.update_status.lock().unwrap();
    *status = UpdateStatus::Ongoing;
    drop(status);
    std::process::Command::new("git")
        .arg("fetch")
        .arg("--depth=1")
        .current_dir(PROJECT_PATH)
        //.stderr(std::process::Stdio::null())
        .stdout(std::process::Stdio::null())
        .status()?;
    std::process::Command::new("git")
        .arg("reset")
        .arg("--hard")
        .arg(concatcp!("origin/", GIT_BRANCH))
        .current_dir(PROJECT_PATH)
        .stdout(std::process::Stdio::null())
        .status()?;
    std::process::Command::new("docker")
        .arg("compose")
        .arg("build")
        .current_dir(PROJECT_PATH)
        .stdout(std::process::Stdio::null())
        // docker compose abuses stderr for output
        .stderr(std::process::Stdio::null())
        .status()?;
    std::process::Command::new("docker")
        .arg("compose")
        .arg("down")
        .current_dir(PROJECT_PATH)
        .stdout(std::process::Stdio::null())
        .status()?;
    std::process::Command::new("docker")
        .arg("compose")
        .arg("up")
        .arg("-d")
        .current_dir(PROJECT_PATH)
        .stdout(std::process::Stdio::null())
        .status()?;
    let mut status = data.update_status.lock().unwrap();
    match *status {
        UpdateStatus::Ongoing => *status = UpdateStatus::None,
        UpdateStatus::Pending => *status = UpdateStatus::PendingAwaited,
        _ => unreachable!(),
    };
    Ok(())
}

#[get("/deploy_status")]
async fn deploy_status(data: web::Data<AppState>) -> impl Responder {
    let status = data.update_status.lock().unwrap();
    let body: &'static str = status.deref().into();
    HttpResponse::Ok().body(body)
}

#[get("/df")]
async fn df() -> impl Responder {
    let df_output = std::process::Command::new("df")
        .output()
        .unwrap_or_else(|e| panic!("df error: {}", e));
    let df_output = String::from_utf8(df_output.stdout).unwrap();
    HttpResponse::Ok().body(df_output)
}

#[get("/deploy")]
async fn deploy(data: web::Data<AppState>) -> impl Responder {
    let mut status = data.update_status.lock().unwrap();
    match *status {
        UpdateStatus::Pending | UpdateStatus::PendingAwaited => {
            return HttpResponse::Ok().body("Update already scheduled")
        }
        UpdateStatus::Ongoing => {
            *status = UpdateStatus::Pending;
            drop(status);
            let _handle = actix_web::rt::spawn(async move {
                loop {
                    actix_web::rt::time::sleep(Duration::from_secs(5)).await;
                    let status = data.update_status.lock().unwrap();
                    if *status == UpdateStatus::PendingAwaited {
                        break;
                    }
                }
                update(data)
                    .await
                    .unwrap_or_else(|e| eprintln!("Update error: {}", e));
            });
        }
        UpdateStatus::None => {
            drop(status);
            let _handle = actix_web::rt::spawn(async move {
                update(data)
                    .await
                    .unwrap_or_else(|e| eprintln!("Update error: {}", e));
            });
        }
    };

    HttpResponse::Ok().body("Update scheduled")
}

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    std::env::set_current_dir("./").unwrap();

    if !std::path::PathBuf::from(PROJECT_PATH).exists() {
        std::process::Command::new("git")
            .arg("clone")
            .arg(GH_REPO)
            .arg("--depth=1")
            .arg("--branch")
            .arg(GIT_BRANCH)
            .arg("--single-branch")
            .arg(PROJECT_PATH)
            .stdout(std::process::Stdio::null())
            .status()
            .unwrap();
        println!("Cloned the {GH_REPO} repo");
    }

    let state = web::Data::new(AppState {
        update_status: Mutex::new(UpdateStatus::None),
    });

    HttpServer::new(move || {
        App::new()
            .app_data(state.clone())
            .service(deploy)
            .service(deploy_status)
            .service(df)
    })
    .bind(("0.0.0.0", PORT))?
    .run()
    .await
}
