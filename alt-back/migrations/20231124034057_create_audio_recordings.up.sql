CREATE TABLE audio_recordings (
  id SERIAL PRIMARY KEY,
  title VARCHAR NOT NULL,
  author_id INTEGER REFERENCES authors(id) NOT NULL,
  description TEXT NOT NULL,
  source_url VARCHAR NOT NULL,
  duration_secs INTEGER NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT NOW(),
  audio_blob BYTEA NOT NULL,
  thumbnail BYTEA
)