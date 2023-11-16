package com.music_streaming_app.service.impl;

import com.music_streaming_app.dto.DtoAudioRecording;
import com.music_streaming_app.entity.AudioRecording;
import com.music_streaming_app.reposytory.RepositoryAudioRecordings;
import com.music_streaming_app.service.ServiceAudioRecordings;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceAudioRecordingsImpl implements ServiceAudioRecordings {

    private final RepositoryAudioRecordings repositoryAudioRecordings;

    @Override
    public List<AudioRecording> getAllAudioRecordings() {
        return repositoryAudioRecordings.findAll();
    }

    @Override
    public Optional<AudioRecording> getAudioRecordingById(Long id) {
        return Optional.of(repositoryAudioRecordings.getReferenceById(id));
    }

    @Override
    @Transactional
    public boolean saveAudioRecording(DtoAudioRecording dtoAudioRecording) {
        AudioRecording audioRecording;
        try {
            audioRecording = AudioRecording.builder()
                    .audioBlob(new SerialBlob(dtoAudioRecording.getFile().getBytes()))
                    .author(dtoAudioRecording.getAuthor())
                    .description(dtoAudioRecording.getDescription())
                    .sourceUrl(dtoAudioRecording.getSourceUrl()).build();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        repositoryAudioRecordings.save(audioRecording);
        return true;
    }
}
