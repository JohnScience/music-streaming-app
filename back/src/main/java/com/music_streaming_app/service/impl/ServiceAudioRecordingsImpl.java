package com.music_streaming_app.service.impl;

import com.music_streaming_app.dto.DtoAudioRecording;
import com.music_streaming_app.entity.EntityAudioRecording;

import java.util.List;
import java.util.UUID;

public interface ServiceAudioRecordingsImpl {

    List<EntityAudioRecording> getAllAudioRecordings();
    EntityAudioRecording getAudioRecordingById(UUID id);
    boolean saveAudioRecording(DtoAudioRecording audioRecording);
}
