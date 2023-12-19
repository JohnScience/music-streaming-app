package com.music_streaming_app.service;

import com.music_streaming_app.dto.TrackDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

public interface TrackService {

    ResponseEntity<List<TrackDto>> getAllTracks();

    ResponseEntity<StreamingResponseBody> getTrackById(Long id);

    ResponseEntity<TrackDto> saveTrack(TrackDto trackDto);

    ResponseEntity<Void> deleteTrackById(Long id);

    ResponseEntity<TrackDto> updateTrack(Long id, TrackDto trackDto);
}