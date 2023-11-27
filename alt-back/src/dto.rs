use axum::body::Bytes;
use axum_typed_multipart::TryFromMultipart;

#[derive(TryFromMultipart)]
#[try_from_multipart(rename_all = "camelCase")]
pub(crate) struct AudioRecording {
    pub(crate) title: String,
    pub(crate) author_id: u32,
    pub(crate) description: String,
    pub(crate) source_url: String,
    pub(crate) file: Bytes,
    pub(crate) thumbnail: Option<Bytes>,
}

#[derive(TryFromMultipart)]
pub(crate) struct Author {
    pub(crate) name: String,
    pub(crate) description: String,
}
