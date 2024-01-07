package com.music_streaming_app.controller;

import com.music_streaming_app.model.Artist;
import com.music_streaming_app.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/artist")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @PostMapping
    public Long createArtist(@RequestBody Artist artist) {
        return artistService.createArtist(artist);
    }

    @GetMapping
    public List<Artist> getAll() {
        return artistService.getAll();
    }

    @GetMapping("/{id}")
    public Artist getOneById(@PathVariable("id") Long id) {
        return artistService.getOneById(id);
    }

    @PutMapping
    public void update(@RequestBody Artist artist) {
        artistService.update(artist);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        artistService.delete(id);
    }
}
