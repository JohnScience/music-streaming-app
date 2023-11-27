use crate::{error::UnsupportedApiReqError, models::ApiVersion, path_lenses::LensPath};
use axum::{
    extract::FromRequestParts,
    http::Request,
    response::{IntoResponse, Response},
};
use semver::VersionReq;

pub(crate) async fn resolve_api_version<B>(request: Request<B>) -> Result<Request<B>, Response> {
    let (mut parts, body) = request.into_parts();
    let LensPath(api_version_req) = LensPath::<VersionReq>::from_request_parts(&mut parts, &())
        .await
        .map_err(|e| e.into_response())?;
    let api_version: ApiVersion = api_version_req
        .try_into()
        .map_err(UnsupportedApiReqError::into_response)?;
    parts.extensions.insert(api_version);
    Ok(Request::from_parts(parts, body))
}
