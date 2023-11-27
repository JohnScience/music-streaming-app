use serde::Serialize;
use sqlx::{types::time::PrimitiveDateTime, PgPool};

use crate::dto;

use super::{
    ids::{AudioRecordingId, AuthorId},
    serialize_primitive_datetime,
};

// This is a subset of the fields of AudioRecording.
#[derive(sqlx::FromRow, Debug, Serialize)]
pub(crate) struct AudioRecordingMetadata {
    #[sqlx(try_from = "i32")]
    pub(crate) id: AudioRecordingId,
    pub(crate) title: String,
    #[sqlx(try_from = "i32")]
    pub(crate) author_id: AuthorId,
    pub(crate) description: String,
    pub(crate) source_url: String,
    pub(crate) duration_secs: i32,
    #[serde(serialize_with = "serialize_primitive_datetime")]
    pub(crate) created_at: PrimitiveDateTime,
}

#[derive(sqlx::FromRow)]
pub(crate) struct AudioBlobLens {
    pub(crate) audio_blob: Vec<u8>,
}

impl AudioRecordingMetadata {
    pub(crate) async fn insert(
        db: &PgPool,
        dto: dto::AudioRecording,
    ) -> sqlx::Result<AudioRecordingId> {
        let dto::AudioRecording {
            title,
            author_id,
            description,
            source_url,
            file,
            thumbnail,
        } = dto;
        let audio_blob = file.as_ref();
        // TODO: check if the the author with author_id exists
        let author_id = author_id as i32;
        // TODO: implement measuring duration
        let duration_secs = 1010101010;
        let id = sqlx::query_scalar!(
                "INSERT INTO audio_recordings (title, author_id, description, source_url, duration_secs, audio_blob, thumbnail) VALUES ($1, $2, $3, $4, $5, $6, $7) RETURNING id",
                title,
                author_id,
                description,
                source_url,
                duration_secs,
                audio_blob,
                thumbnail.as_deref()
            )
            .fetch_one(db)
            .await
            .map(AudioRecordingId)?;
        Ok(id)
    }

    pub(crate) async fn all(db: &PgPool) -> sqlx::Result<Vec<AudioRecordingMetadata>> {
        let audio_recordings = sqlx::query_as!(
            AudioRecordingMetadata,
            "SELECT id, title, author_id, description, source_url, duration_secs, created_at FROM audio_recordings"
        )
        .fetch_all(db)
        .await?;
        Ok(audio_recordings)
    }
}

impl AudioBlobLens {
    fn into_inner(self) -> Vec<u8> {
        let AudioBlobLens { audio_blob } = self;
        audio_blob
    }

    pub(crate) async fn find_by_id(db: PgPool, id: i32) -> sqlx::Result<Option<Vec<u8>>> {
        let audio_recording = sqlx::query_as::<_, AudioBlobLens>(
            "SELECT audio_blob FROM audio_recordings WHERE id = $1",
        )
        .bind(id)
        .fetch_optional(&db)
        .await?
        .map(AudioBlobLens::into_inner);
        Ok(audio_recording)
    }
}
