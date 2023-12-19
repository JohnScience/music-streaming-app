package com.music_streaming_app.service.impl;

import com.music_streaming_app.converter.TrackConverter;
import com.music_streaming_app.dto.TrackDto;
import com.music_streaming_app.entity.Track;
import com.music_streaming_app.repository.TrackRepository;
import com.music_streaming_app.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TrackServiceImpl implements TrackService {

    private static final Logger logger = LoggerFactory.getLogger(TrackServiceImpl.class);

    private final TrackRepository trackRepository;

    @Override
    public List<Track> getAllTracks() {
        logger.info("TrackTrackServiceImpl getAllTracks start");
        List<Track> tracks = trackRepository.findAll();
        logger.info("TrackTrackServiceImpl getAllTracks end");
        return tracks;
    }

    @Override
    public ResponseEntity<List<TrackDto>> getFeaturedTracks() {
        logger.info("TrackTrackServiceImpl getFeaturedTracks start");
        ResponseEntity<List<TrackDto>> responseEntity = ResponseEntity.ok(
                trackRepository.findFeatured()
                        .stream()
                        .map(TrackConverter::toTrackDto)
                        .toList()
        );
        logger.info("TrackTrackServiceImpl getFeaturedTracks end");
        return responseEntity;
    }

    @Override
    public ResponseEntity<StreamingResponseBody> getTrackById(Long id) {
        logger.info("TrackTrackServiceImpl getTrackById start");
        Optional<Track> trackOptional =
                Optional.of(trackRepository.getReferenceById(id));
        Blob audioBlob = trackOptional.get().getAudioBlob();
        StreamingResponseBody responseBody = TrackConverter.toStreamingResponseBody(audioBlob);
        HttpHeaders headers = setUpHttpHeaders(audioBlob);
        logger.info("TrackTrackServiceImpl getTrackById end");
        return ResponseEntity.ok()
                .headers(headers)
                .body(responseBody);
    }

    @Override
    public void saveTrack(TrackDto trackDto) {
        logger.info("TrackTrackServiceImpl saveTrack start");
        Track track = TrackConverter.toTrack(trackDto);
        trackRepository.save(track);
        logger.info("TrackTrackServiceImpl saveTrack end");
    }

    @Override
    public ResponseEntity<List<TrackDto>> getAllTrackDto() {
        logger.info("TrackTrackServiceImpl getAllTrackDto start");
        List<TrackDto> trackDtos = getAllTracks().stream()
                .map(TrackConverter::toTrackDto)
                .collect(Collectors.toList());
        logger.info("TrackTrackServiceImpl getAllTrackDto end");
        return ResponseEntity.ok(trackDtos);
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