package com.music_streaming_app.service;

import com.music_streaming_app.dto.TrackDto;
import com.music_streaming_app.entity.Track;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

public interface TrackService {

    List<Track> getAllTracks();

    ResponseEntity<StreamingResponseBody> getTrackById(Long id);

    void saveTrack(TrackDto trackDto);

    ResponseEntity<List<TrackDto>> getAllTrackDto();
}