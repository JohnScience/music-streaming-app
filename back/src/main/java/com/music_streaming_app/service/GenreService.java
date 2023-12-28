package com.music_streaming_app.service;

import com.music_streaming_app.dto.GenreDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenreService {

    ResponseEntity<List<GenreDto>> getAllGenres();

    ResponseEntity<GenreDto> getGenreById(Long id);

    ResponseEntity<GenreDto> saveGenre(GenreDto genreDto);

    ResponseEntity<Void> deleteGenreById(Long id);

    ResponseEntity<GenreDto> updateGenre(Long id, GenreDto genreDto);
}