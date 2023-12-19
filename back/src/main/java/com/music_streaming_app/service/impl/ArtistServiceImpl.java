package com.music_streaming_app.service.impl;

import com.music_streaming_app.dto.ArtistDto;
import com.music_streaming_app.entity.Artist;
import com.music_streaming_app.mapper.ArtistMapper;
import com.music_streaming_app.repository.ArtistRepository;
import com.music_streaming_app.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArtistServiceImpl implements ArtistService {

    private static final Logger logger = LoggerFactory.getLogger(TrackServiceImpl.class);

    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    @Override
    public ResponseEntity<List<ArtistDto>> getAllArtists() {
        logger.info("ArtistServiceImpl getAllArtists start");
        List<Artist> artists = artistRepository.findAll();
        logger.info("ArtistServiceImpl getAllArtists end");
        return ResponseEntity.ok(artistMapper.toDtoList(artists));
    }

    @Override
    public ResponseEntity<ArtistDto> getArtistById(Long id) {
        logger.info("ArtistServiceImpl getArtistById start");
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isEmpty()) {
            logger.info("Artist with id {} does not exist", id);
            return ResponseEntity.notFound().build();
        }
        logger.info("ArtistServiceImpl getArtistById end");
        return ResponseEntity.ok().body(artistMapper.toDto(artist.get()));
    }

    @Override
    @Transactional
    public ResponseEntity<ArtistDto> saveArtist(ArtistDto artistDto) {
        logger.info("ArtistServiceImpl saveArtist start");
        Artist artist = artistMapper.toEntity(artistDto);
        if (artistRepository.existsById(artist.getId())) {
            logger.info("Artist with id {} already exists", artist.getId());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        artistRepository.save(artist);
        logger.info("ArtistServiceImpl saveArtist end");
        return ResponseEntity.ok().body(artistMapper.toDto(artist));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteArtistById(Long id) {
        logger.info("ArtistServiceImpl deleteArtistById start");
        if (!artistRepository.existsById(id)) {
            logger.info("Artist with id {} does not exist", id);
            return ResponseEntity.notFound().build();
        }
        artistRepository.deleteById(id);
        logger.info("ArtistServiceImpl deleteArtistById end");
        return ResponseEntity.noContent().build();
    }

    @Override
    @Transactional
    public ResponseEntity<ArtistDto> updateArtist(Long id, ArtistDto artistDto) {
        logger.info("ArtistServiceImpl updateArtist start");
        if (!artistRepository.existsById(id)) {
            logger.info("Artist with id {} does not exist", id);
            return ResponseEntity.notFound().build();
        }
        Artist artist = artistMapper.toEntity(artistDto);
        artist.setId(id);
        artistRepository.save(artist);
        logger.info("ArtistServiceImpl updateArtist end");
        return ResponseEntity.ok().body(artistMapper.toDto(artist));
    }
}
