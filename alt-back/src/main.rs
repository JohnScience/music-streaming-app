use axum::middleware::map_request;
use axum::routing::post;
use axum::{routing::get, Router};
use dotenvy::dotenv;
use middleware::resolve_api_version;
use sqlx::postgres::PgPool;
use std::env;
use std::net::SocketAddr;

mod dto;
pub(crate) mod entities;
mod error;
mod handlers;
mod middleware;
mod models;
pub(crate) mod path_lenses;

pub(crate) async fn establish_connection() -> PgPool {
    let database_url = env::var("DATABASE_URL").expect("DATABASE_URL must be set");
    let db = PgPool::connect(&database_url)
        .await
        .unwrap_or_else(|e| panic!("Error connecting to {database_url}: {e}"));
    db
}

fn app(db: PgPool) -> Router {
    use handlers::*;

    let audio_recording_routes = Router::new()
        .route("/all", get(all_audio_recordings))
        .route("/:id", get(audio_recording_by_id))
        .route("/save", post(save_audio_recording));

    let author_routes = Router::new()
        .route("/all", get(all_authors))
        .route("/save", post(save_author));

    let api_routes = Router::new()
        .nest("/audio_recording", audio_recording_routes)
        .nest("/author", author_routes);

    Router::new().route("/", get(index)).nest(
        "/api/v:version",
        api_routes
            .layer(map_request(resolve_api_version))
            .with_state(db),
    )
}

#[tokio::main]
async fn main() {
    // load the .env file if it exists
    dotenv().ok();

    let db = establish_connection().await;

    let addr: SocketAddr = env::var("SOCKET_ADDR")
        .expect("SOCKET_ADDR must be set")
        .parse()
        .expect("SOCKET_ADDR must be a valid address");

    // run it with hyper on localhost:3000
    axum::Server::bind(&addr)
        .serve(app(db).into_make_service())
        .await
        .unwrap();
}
