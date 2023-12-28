package com.music_streaming_app.service.impl;

import com.music_streaming_app.dto.GenreDto;
import com.music_streaming_app.entity.Genre;
import com.music_streaming_app.mapper.GenreMapper;
import com.music_streaming_app.repository.GenreRepository;
import com.music_streaming_app.service.GenreService;
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
public class GenreServiceImpl implements GenreService {

    private static final Logger logger = LoggerFactory.getLogger(GenreServiceImpl.class);

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public ResponseEntity<List<GenreDto>> getAllGenres() {
        logger.info("GenreServiceImpl getAllGenres start");
        List<Genre> genres = genreRepository.findAll();
        logger.info("GenreServiceImpl getAllGenres end");
        return ResponseEntity.ok(genreMapper.toDtoList(genres));
    }

    @Override
    public ResponseEntity<GenreDto> getGenreById(Long id) {
        logger.info("GenreServiceImpl getGenreById start");
        Optional<Genre> genre = genreRepository.findById(id);
        if (genre.isEmpty()) {
            logger.info("Genre with id {} does not exist", id);
            return ResponseEntity.notFound().build();
        }
        logger.info("GenreServiceImpl getGenreById end");
        return ResponseEntity.ok().body(genreMapper.toDto(genre.get()));
    }

    @Override
    @Transactional
    public ResponseEntity<GenreDto> saveGenre(GenreDto genreDto) {
        logger.info("GenreServiceImpl saveArtist start");
        Genre genre = genreMapper.toEntity(genreDto);
        if (genreRepository.existsById(genre.getId())) {
            logger.info("Genre with id {} already exists", genre.getId());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        genreRepository.save(genre);
        logger.info("GenreServiceImpl saveGenre end");
        return ResponseEntity.ok().body(genreMapper.toDto(genre));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteGenreById(Long id) {
        logger.info("GenreServiceImpl deleteGenreById start");
        if (!genreRepository.existsById(id)) {
            logger.info("Genre with id {} does not exist", id);
            return ResponseEntity.notFound().build();
        }
        genreRepository.deleteById(id);
        logger.info("GenreServiceImpl deleteGenreById end");
        return ResponseEntity.noContent().build();
    }

    @Override
    @Transactional
    public ResponseEntity<GenreDto> updateGenre(Long id, GenreDto genreDto) {
        logger.info("GenreServiceImpl updateGenre start");
        if (!genreRepository.existsById(id)) {
            logger.info("Genre with id {} does not exist", id);
            return ResponseEntity.notFound().build();
        }
        Genre genre = genreMapper.toEntity(genreDto);
        genre.setId(id);
        genreRepository.save(genre);
        logger.info("GenreServiceImpl updateGenre end");
        return ResponseEntity.ok().body(genreMapper.toDto(genre));
    }
}