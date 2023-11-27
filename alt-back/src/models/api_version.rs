use std::fmt::Display;

use axum::{
    async_trait,
    extract::{FromRequestParts, Path},
    http::{request::Parts, StatusCode},
    response::{IntoResponse, Response},
};
use semver::{Comparator, VersionReq};

use crate::error::UnsupportedApiReqError;

#[derive(Clone, Copy)]
pub(crate) struct ApiVersion {
    pub(crate) major: u64,
    pub(crate) minor: u64,
    pub(crate) patch: u64,
}

impl Display for ApiVersion {
    fn fmt(&self, f: &mut std::fmt::Formatter<'_>) -> std::fmt::Result {
        write!(f, "{}.{}.{}", self.major, self.minor, self.patch)
    }
}

impl TryFrom<VersionReq> for ApiVersion {
    type Error = UnsupportedApiReqError;

    fn try_from(version_req: VersionReq) -> Result<Self, Self::Error> {
        let VersionReq { comparators } = version_req;
        if comparators.len() != 1 {
            return Err(UnsupportedApiReqError::ComplexSemverComparators);
        };
        let Comparator {
            op: _,
            major,
            minor,
            patch,
            pre,
        } = comparators.into_iter().next().unwrap();
        if !pre.is_empty() {
            return Err(UnsupportedApiReqError::PreReleaseVersions);
        };
        let simple_version = ApiVersion {
            major,
            minor: minor.unwrap_or(0),
            patch: patch.unwrap_or(0),
        };
        if major != 1 {
            return Err(UnsupportedApiReqError::Only100Supported);
        };
        if !matches!(minor, None | Some(0)) {
            return Err(UnsupportedApiReqError::Only100Supported);
        };
        if !matches!(patch, None | Some(0)) {
            return Err(UnsupportedApiReqError::Only100Supported);
        };
        Ok(simple_version)
    }
}

impl TryFrom<Path<VersionReq>> for ApiVersion {
    type Error = UnsupportedApiReqError;

    fn try_from(Path(version_req): Path<VersionReq>) -> Result<Self, Self::Error> {
        ApiVersion::try_from(version_req)
    }
}

#[async_trait]
impl<S> FromRequestParts<S> for ApiVersion
where
    S: Send + Sync,
{
    type Rejection = Response;

    async fn from_request_parts(parts: &mut Parts, state: &S) -> Result<Self, Self::Rejection> {
        let Path(api_version_req) = Path::<VersionReq>::from_request_parts(parts, state)
            .await
            .map_err(|_| (StatusCode::BAD_REQUEST, "Invalid api version").into_response())?;
        ApiVersion::try_from(api_version_req).map_err(|e| e.into_response())
    }
}
