package com.music_streaming_app.controller;

import com.music_streaming_app.dto.ArtistDto;
import com.music_streaming_app.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/artists")
public class ArtistController {

    private static final Logger logger = LoggerFactory.getLogger(TrackController.class);

    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getAllArtists() {
        logger.info("ArtistController getAllArtists start");
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDto> getArtistById(@PathVariable Long id) {
        logger.info("ArtistController getArtistById start: id = " + id);
        return artistService.getArtistById(id);
    }

    @PostMapping
    public ResponseEntity<ArtistDto> saveArtist(@ModelAttribute ArtistDto artistDto) {
        logger.info("ArtistController saveArtist start: artistDto = " + artistDto.toString());
        return artistService.saveArtist(artistDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtistById(@PathVariable Long id) {
        logger.info("ArtistController deleteArtistById start");
        return artistService.deleteArtistById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistDto> updateArtist(@PathVariable Long id,
                                                  @RequestBody ArtistDto artistDto) {
        logger.info("ArtistController updateArtist start");
        return artistService.updateArtist(id, artistDto);
    }
}
