package com.music_streaming_app.service;

import com.music_streaming_app.dto.ArtistDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArtistService {

    ResponseEntity<List<ArtistDto>> getAllArtists();

    ResponseEntity<ArtistDto> getArtistById(Long id);

    ResponseEntity<ArtistDto> saveArtist(ArtistDto artistDto);

    ResponseEntity<Void> deleteArtistById(Long id);

    ResponseEntity<ArtistDto> updateArtist(Long id, ArtistDto artistDto);
}
