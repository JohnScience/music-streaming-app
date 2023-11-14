package com.music_streaming_app.service;

import com.music_streaming_app.dto.DtoAudioRecording;
import com.music_streaming_app.entity.EntityAudioRecording;
import com.music_streaming_app.reposytory.RepositoryAudioRecordings;
import com.music_streaming_app.service.impl.ServiceAudioRecordingsImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceAudioRecordings implements ServiceAudioRecordingsImpl {
    private final RepositoryAudioRecordings repositoryAudioRecordings;

    public ServiceAudioRecordings(RepositoryAudioRecordings repositoryAudioRecordings) {
        this.repositoryAudioRecordings = repositoryAudioRecordings;
    }

    @Override
    public List<EntityAudioRecording> getAllAudioRecordings() {
        return repositoryAudioRecordings.findAll();
    }

    @Override
    public Optional<EntityAudioRecording> getAudioRecordingById(Long id) {
        return Optional.of(repositoryAudioRecordings.getReferenceById(id));
    }

    @Override
    @Transactional
    public boolean saveAudioRecording(DtoAudioRecording audioRecording) {

        EntityAudioRecording entityAudioRecording = new EntityAudioRecording();

        try {
            entityAudioRecording.setAudioBlob(audioRecording.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        entityAudioRecording.setAuthor(audioRecording.getAuthor());
        entityAudioRecording.setDescription(audioRecording.getDescription());
        entityAudioRecording.setSourceUrl(audioRecording.getSourceUrl());
        entityAudioRecording.setCreatedAt(LocalDateTime.now());

        repositoryAudioRecordings.save(entityAudioRecording);

        return true;
    }
}
