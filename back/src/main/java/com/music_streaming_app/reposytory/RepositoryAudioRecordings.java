package com.music_streaming_app.reposytory;

import com.music_streaming_app.entity.EntityAudioRecording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RepositoryAudioRecordings extends JpaRepository<EntityAudioRecording, UUID> {
}
