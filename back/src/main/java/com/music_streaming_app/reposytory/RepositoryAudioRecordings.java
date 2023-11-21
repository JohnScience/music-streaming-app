package com.music_streaming_app.reposytory;

import com.music_streaming_app.entity.AudioRecording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryAudioRecordings extends JpaRepository<AudioRecording, Long> {
}
