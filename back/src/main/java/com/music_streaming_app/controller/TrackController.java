package com.music_streaming_app.controller;

import com.music_streaming_app.dto.TrackDto;
import com.music_streaming_app.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/tracks")
public class TrackController {

    private static final Logger logger = LoggerFactory.getLogger(TrackController.class);

    private final TrackService trackService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TrackDto> saveTrack(@ModelAttribute TrackDto trackDto) {
        logger.info("TrackController saveTrack start: trackDto = " + trackDto.toString());
        return trackService.saveTrack(trackDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponseBody> getTrackById(@PathVariable Long id) {
        logger.info("TrackController getTrackById start: id = " + id);
        return trackService.getTrackById(id);
    }

    @GetMapping
    public ResponseEntity<List<TrackDto>> getAllTracks() {
        logger.info("TrackController getAllTracks start");
        return trackService.getAllTracks();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrackById(@PathVariable Long id) {
        logger.info("TrackController deleteTrackById start");
        return trackService.deleteTrackById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrackDto> updateTrack(@PathVariable Long id,
                                                @RequestBody TrackDto trackDto) {
        logger.info("TrackController updateTrack start");
        return trackService.updateTrack(id, trackDto);
    }
}