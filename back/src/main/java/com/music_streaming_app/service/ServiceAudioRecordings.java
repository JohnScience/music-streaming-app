package com.music_streaming_app.service;

import com.music_streaming_app.dto.DtoAudioRecording;
import com.music_streaming_app.entity.AudioRecording;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ServiceAudioRecordings {

    List<AudioRecording> getAllAudioRecordings();

    Set<AudioRecording> getFeaturedAudioRecordings();

    Optional<AudioRecording> getAudioRecordingById(Long id);

    boolean saveAudioRecording(DtoAudioRecording audioRecording);
}
