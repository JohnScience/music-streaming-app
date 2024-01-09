package com.music_streaming_app.controller;

import com.music_streaming_app.model.Genre;
import com.music_streaming_app.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/genre")
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @PostMapping
    public Long create(@RequestBody Genre genre) {
        return genreService.create(genre.getName());
    }

    @GetMapping("/all")
    public List<Genre> getAll() {
        return genreService.getAll();
    }

    @GetMapping("/{id}")
    public Genre getById(@PathVariable("id") Long id) {
        return genreService.getById(id);
    }

    @PutMapping
    public void update(@RequestBody Genre genre) {
        genreService.update(genre);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        genreService.delete(id);
    }
}
