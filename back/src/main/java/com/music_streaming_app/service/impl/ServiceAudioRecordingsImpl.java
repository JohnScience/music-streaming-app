package com.music_streaming_app.service.impl;

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

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
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
//                    .author(dtoAudioRecording.getArtist())
                    .description(dtoAudioRecording.getDescription())
                    .sourceUrl(dtoAudioRecording.getSourceUrl()).build();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }

        repositoryAudioRecordings.save(audioRecording);
        return true;
    }

    public ResponseEntity<StreamingResponseBody> getAudioRecording(Optional<AudioRecording> audioRecordingOptional) {
        Blob audioBlob = audioRecordingOptional.get().getAudioBlob();
        StreamingResponseBody responseBody = outputStream -> {
            try (InputStream inputStream = audioBlob.getBinaryStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            headers.setContentLength((int) audioBlob.length());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .headers(headers)
                .body(responseBody);
    }

    public ResponseEntity<List<DtoAudioRecording>> getAllDtoAudioRecordings() {
        List<DtoAudioRecording> dtoAudioRecordings = new ArrayList<>();

        for (AudioRecording audioRecording : getAllAudioRecordings()) {
            DtoAudioRecording dtoAudioRecording = DtoAudioRecording.builder()
                    .id(audioRecording.getId())
                    .artist(audioRecording.getArtist())
                    .description(audioRecording.getDescription())
                    .sourceUrl(audioRecording.getSourceUrl())
                    .createdAt(audioRecording.getCreatedAt())
                    .build();

            dtoAudioRecordings.add(dtoAudioRecording);
        }

        return ResponseEntity.ok(dtoAudioRecordings);
    }
}
