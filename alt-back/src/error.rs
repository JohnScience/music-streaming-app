use axum::response::IntoResponse;
use thiserror::Error;

#[derive(Error, Debug)]
pub(crate) enum UnsupportedApiReqError {
    #[error("Complex semver comparators are not supported")]
    ComplexSemverComparators,
    #[error("Pre-release versions are not supported")]
    PreReleaseVersions,
    #[error("Only version 1.0.0 is supported")]
    Only100Supported,
}

impl IntoResponse for UnsupportedApiReqError {
    fn into_response(self) -> axum::response::Response {
        (axum::http::StatusCode::BAD_REQUEST, self.to_string()).into_response()
    }
}
