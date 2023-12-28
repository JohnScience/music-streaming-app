package com.music_streaming_app.controller;

import com.music_streaming_app.dto.GenreDto;
import com.music_streaming_app.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/genres")
public class GenreController {

    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);

    private final GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDto>> getAllGenres() {
        logger.info("GenreController getAllGenres start");
        return genreService.getAllGenres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable Long id) {
        logger.info("GenreController getGenreById start: id = " + id);
        return genreService.getGenreById(id);
    }

    @PostMapping
    public ResponseEntity<GenreDto> saveGenre(@ModelAttribute GenreDto genreDto) {
        logger.info("GenreController saveGenre start: genreDto = " + genreDto.toString());
        return genreService.saveGenre(genreDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenreById(@PathVariable Long id) {
        logger.info("GenreController deleteGenreById start");
        return genreService.deleteGenreById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable Long id,
                                                @RequestBody GenreDto genreDto) {
        logger.info("GenreController updateGenre start");
        return genreService.updateGenre(id, genreDto);
    }
}