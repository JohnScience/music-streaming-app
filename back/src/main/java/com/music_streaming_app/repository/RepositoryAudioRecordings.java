package com.music_streaming_app.repository;

import com.music_streaming_app.entity.AudioRecording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface RepositoryAudioRecordings extends JpaRepository<AudioRecording, Long> {

  @Query(
    nativeQuery = true,
    value = "select * from audio_recordings where id in (select audio_recording_id from featured)"
  )
  Set<AudioRecording> findFeatured();


}
