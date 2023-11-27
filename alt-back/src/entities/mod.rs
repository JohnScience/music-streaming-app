use sqlx::types::time::PrimitiveDateTime;

pub(crate) mod audio_recording;
pub(crate) mod author;

pub(crate) mod ids {
    use derive_more::From;
    use serde::Serialize;
    use sqlx::Type;

    #[derive(Type, Debug, Serialize, From)]
    #[sqlx(transparent)]
    pub(crate) struct AuthorId(pub(crate) i32);

    #[derive(Type, Debug, From, Serialize)]
    #[sqlx(transparent)]
    pub(crate) struct AudioRecordingId(pub(crate) i32);
}

fn serialize_primitive_datetime<S>(
    primitive_datetime: &PrimitiveDateTime,
    serializer: S,
) -> Result<S::Ok, S::Error>
where
    S: serde::Serializer,
{
    let yy = primitive_datetime.year();
    let mo: u8 = primitive_datetime.month().into();
    let dd = primitive_datetime.day();
    let hh = primitive_datetime.hour();
    let mm = primitive_datetime.minute();
    let ss = primitive_datetime.second();

    // This is an ISO 8601 and RFC 3339 compliant date-time string.
    let s = format!("{yy:04}-{mo:02}-{dd:02}T{hh:02}:{mm:02}:{ss:02}");
    serializer.serialize_str(&s)
}
