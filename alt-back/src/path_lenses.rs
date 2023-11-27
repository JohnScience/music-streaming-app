use axum::{
    async_trait,
    extract::{FromRequestParts, Path},
    http::request::Parts,
    response::{IntoResponse, Response},
};
use semver::VersionReq;
use serde::{de::DeserializeOwned, Deserialize};

pub(crate) trait Lens: DeserializeOwned + Send {
    type InnerType;

    fn into_inner(self) -> Self::InnerType;
}

pub(crate) trait HasLens {
    type Lens: Lens<InnerType = Self>;
}

macro_rules! decl_lens {
    ($lens:ident { $field:ident: $field_ty:ty } $(for $field_name:literal)?) => {
        #[derive(Deserialize)]
        pub(crate) struct $lens {
            $(#[serde(rename = $field_name)])?
            pub(crate) $field: $field_ty,
        }

        impl Lens for $lens {
            type InnerType = $field_ty;

            fn into_inner(self) -> Self::InnerType {
                let Self { $field } = self;
                $field
            }
        }
    };
}

pub(crate) struct LensPath<T: HasLens>(pub(crate) T);

// https://www.tutorchase.com/answers/a-level/computer-science/what-are-lenses--and-how-are-they-used-in-functional-programming

decl_lens!(IdLens { id: u64 });
decl_lens!(VersionLens { api_version_req: VersionReq } for "version");

impl HasLens for u64 {
    type Lens = IdLens;
}

impl HasLens for VersionReq {
    type Lens = VersionLens;
}

#[async_trait]
impl<T, S> FromRequestParts<S> for LensPath<T>
where
    T: HasLens,
    S: Send + Sync,
{
    type Rejection = Response;

    async fn from_request_parts(parts: &mut Parts, state: &S) -> Result<Self, Self::Rejection> {
        let Path(path) = Path::<T::Lens>::from_request_parts(parts, state)
            .await
            .map_err(|e| e.into_response())?;
        let inner = path.into_inner();
        Ok(Self(inner))
    }
}
