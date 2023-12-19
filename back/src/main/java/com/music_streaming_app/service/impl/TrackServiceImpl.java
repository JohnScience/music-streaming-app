package com.music_streaming_app.service.impl;

import com.music_streaming_app.converter.BlobConverter;
import com.music_streaming_app.dto.TrackDto;
import com.music_streaming_app.entity.Track;
import com.music_streaming_app.mapper.TrackMapper;
import com.music_streaming_app.repository.TrackRepository;
import com.music_streaming_app.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrackServiceImpl implements TrackService {

    private static final Logger logger = LoggerFactory.getLogger(TrackServiceImpl.class);

    private final TrackRepository trackRepository;
    private final TrackMapper trackMapper;
    private final BlobConverter blobConverter;

    @Override
    public ResponseEntity<List<TrackDto>> getAllTracks() {
        logger.info("TrackTrackServiceImpl getAllTracks start");
        List<Track> tracks = trackRepository.findAll();
        logger.info("TrackTrackServiceImpl getAllTracks end");
        return ResponseEntity.ok(trackMapper.toDtoList(tracks));
    }

    @Override
    public ResponseEntity<StreamingResponseBody> getTrackById(Long id) {
        logger.info("TrackTrackServiceImpl getTrackById start");
        Optional<Track> trackOptional =
                Optional.of(trackRepository.getReferenceById(id));
        Blob audioBlob = trackOptional.get().getAudioBlob();
        StreamingResponseBody responseBody = blobConverter.toStreamingResponseBody(audioBlob);
        HttpHeaders headers = setUpHttpHeaders(audioBlob);
        logger.info("TrackTrackServiceImpl getTrackById end");
        return ResponseEntity.ok().headers(headers).body(responseBody);
    }

    @Override
    @Transactional
    public ResponseEntity<TrackDto> saveTrack(TrackDto trackDto) {
        logger.info("TrackTrackServiceImpl saveTrack start");
        Track track = trackMapper.toEntity(trackDto);
        if (trackRepository.existsById(track.getId())) {
            logger.info("Track with id {} already exists", track.getId());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        trackRepository.save(track);
        logger.info("TrackTrackServiceImpl saveTrack end");
        return ResponseEntity.ok().body(trackMapper.toDto(track));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> deleteTrackById(Long id) {
        logger.info("TrackTrackServiceImpl deleteTrackById start");
        if (!trackRepository.existsById(id)) {
            logger.info("Track with id {} does not exist", id);
            return ResponseEntity.notFound().build();
        }
        trackRepository.deleteById(id);
        logger.info("TrackTrackServiceImpl deleteTrackById end");
        return ResponseEntity.noContent().build();
    }

    @Override
    @Transactional
    public ResponseEntity<TrackDto> updateTrack(Long id, TrackDto trackDto) {
        logger.info("TrackTrackServiceImpl updateTrack start");
        if (!trackRepository.existsById(id)) {
            logger.info("Track with id {} does not exist", id);
            return ResponseEntity.notFound().build();
        }
        Track track = trackMapper.toEntity(trackDto);
        track.setId(id);
        trackRepository.save(track);
        logger.info("TrackTrackServiceImpl updateTrack end");
        return ResponseEntity.ok().body(trackMapper.toDto(track));
    }

    private HttpHeaders setUpHttpHeaders(Blob audioBlob) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            headers.setContentLength((int) audioBlob.length());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return headers;
    }
}