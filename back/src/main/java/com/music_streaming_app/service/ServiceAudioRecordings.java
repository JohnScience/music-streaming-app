package com.music_streaming_app.service;

import com.music_streaming_app.dto.DtoAudioRecording;
import com.music_streaming_app.entity.AudioRecording;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;
import java.util.Set;

public interface ServiceAudioRecordings {

    List<AudioRecording> getAllAudioRecordings();

    Set<AudioRecording> getFeaturedAudioRecordings();

    ResponseEntity<StreamingResponseBody> getAudioRecordingById(Long id);

    boolean saveAudioRecording(DtoAudioRecording audioRecording);

    ResponseEntity<List<DtoAudioRecording>> getAllDtoAudioRecordings();
}
