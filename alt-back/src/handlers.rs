use axum::{extract::State, http::StatusCode, Json};
use axum_typed_multipart::TypedMultipart;
use sqlx::PgPool;

use crate::entities::author::Author as AuthorEntity;
use crate::{
    dto,
    entities::audio_recording::{AudioBlobLens, AudioRecordingMetadata},
    path_lenses::LensPath,
};

pub(crate) async fn all_audio_recordings(
    State(db): State<PgPool>,
) -> (StatusCode, Json<Vec<AudioRecordingMetadata>>) {
    match AudioRecordingMetadata::all(&db).await {
        Ok(audio_recordings) => (StatusCode::OK, Json(audio_recordings)),
        Err(_) => (StatusCode::INTERNAL_SERVER_ERROR, Json(vec![])),
    }
}

pub(crate) async fn audio_recording_by_id(
    LensPath(id): LensPath<u64>,
    State(db): State<PgPool>,
) -> (StatusCode, Vec<u8>) {
    let audio_blob = AudioBlobLens::find_by_id(db, id as i32)
        .await
        .unwrap_or_else(|_| panic!("No audio recording with id {} found", id));
    let Some(audio_blob) = audio_blob else {
        return (StatusCode::NOT_FOUND, Vec::new());
    };
    (StatusCode::OK, audio_blob)
}

pub(crate) async fn save_audio_recording(
    State(db): State<PgPool>,
    TypedMultipart(audio_recording): TypedMultipart<dto::AudioRecording>,
) -> (StatusCode, String) {
    match AudioRecordingMetadata::insert(&db, audio_recording).await {
        Ok(id) => (StatusCode::OK, format!("{id}", id = id.0)),
        Err(e) => (
            StatusCode::INTERNAL_SERVER_ERROR,
            format!("Error saving audio recording: {}", e),
        ),
    }
}

pub(crate) async fn all_authors(State(db): State<PgPool>) -> (StatusCode, Json<Vec<AuthorEntity>>) {
    match AuthorEntity::all(&db).await {
        Ok(authors) => (StatusCode::OK, Json(authors)),
        Err(_) => (StatusCode::INTERNAL_SERVER_ERROR, Json(vec![])),
    }
}

pub(crate) async fn save_author(
    State(db): State<PgPool>,
    TypedMultipart(author): TypedMultipart<dto::Author>,
) -> (StatusCode, String) {
    match AuthorEntity::insert(db, author).await {
        Ok(id) => (StatusCode::OK, format!("{id}", id = id.0)),
        Err(e) => (
            StatusCode::INTERNAL_SERVER_ERROR,
            format!("Error saving author: {}", e),
        ),
    }
}

pub(crate) async fn index() -> &'static str {
    "Hello, World!"
}
