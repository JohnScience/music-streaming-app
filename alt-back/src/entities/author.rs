use serde::Serialize;
use sqlx::{types::time::PrimitiveDateTime, PgPool};

use super::{ids::AuthorId, serialize_primitive_datetime};
use crate::dto;

#[derive(sqlx::FromRow, Debug, Serialize)]
pub(crate) struct Author {
    #[sqlx(try_from = "i32")]
    pub(crate) id: AuthorId,
    pub(crate) name: String,
    pub(crate) description: String,
    #[serde(serialize_with = "serialize_primitive_datetime")]
    pub(crate) created_at: PrimitiveDateTime,
}

impl Author {
    pub(crate) async fn insert(db: PgPool, dto: dto::Author) -> sqlx::Result<AuthorId> {
        let dto::Author { name, description } = dto;
        let id = sqlx::query_scalar!(
            r#"
                INSERT INTO authors (name, description)
                VALUES ($1, $2)
                RETURNING id
                "#,
            name,
            description
        )
        .fetch_one(&db)
        .await
        .map(AuthorId)?;
        Ok(id)
    }

    pub(crate) async fn all(db: &PgPool) -> sqlx::Result<Vec<Author>> {
        let authors = sqlx::query_as!(
            Author,
            r#"
                SELECT id, name, description, created_at
                FROM authors
                "#
        )
        .fetch_all(db)
        .await?;
        Ok(authors)
    }
}
