package com.music_streaming_app.service;

import com.music_streaming_app.dto.DtoAudioRecording;
import com.music_streaming_app.entity.EntityAudioRecording;

import java.util.List;
import java.util.Optional;

public interface ServiceAudioRecordings {

    List<EntityAudioRecording> getAllAudioRecordings();

    Optional<EntityAudioRecording> getAudioRecordingById(Long id);

    boolean saveAudioRecording(DtoAudioRecording audioRecording);
}
