package com.music_streaming_app.service.impl;

import com.music_streaming_app.converter.AudioRecordingConverter;
import com.music_streaming_app.dto.DtoAudioRecording;
import com.music_streaming_app.entity.AudioRecording;
import com.music_streaming_app.repository.RepositoryAudioRecordings;
import com.music_streaming_app.service.ServiceAudioRecordings;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public Set<AudioRecording> getFeaturedAudioRecordings() {
        return repositoryAudioRecordings.findFeatured();
    }

    @Override
    public ResponseEntity<StreamingResponseBody> getAudioRecordingById(Long id) {
        Optional<AudioRecording> audioRecordingOptional =
                Optional.of(repositoryAudioRecordings.getReferenceById(id));
        Blob audioBlob = audioRecordingOptional.get().getAudioBlob();
        StreamingResponseBody responseBody = AudioRecordingConverter.toStreamingResponseBody(audioBlob);
        HttpHeaders headers = setUpHttpHeaders(audioBlob);
        return ResponseEntity.ok()
                .headers(headers)
                .body(responseBody);
    }

    @Override
    public boolean saveAudioRecording(DtoAudioRecording dtoAudioRecording) {
        AudioRecording audioRecording = AudioRecordingConverter.toAudioRecording(dtoAudioRecording);
        repositoryAudioRecordings.save(audioRecording);
        return true;
    }

    private HttpHeaders setUpHttpHeaders(Blob audioBlob) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            headers.setContentLength((int) audioBlob.length());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return headers;
    }

    @Override
    public ResponseEntity<List<DtoAudioRecording>> getAllDtoAudioRecordings() {
        List<DtoAudioRecording> dtoAudioRecordings = getAllAudioRecordings().stream()
                .map(AudioRecordingConverter::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoAudioRecordings);
    }
}
