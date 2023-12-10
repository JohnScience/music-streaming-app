package com.music_streaming_app.service.impl;

import com.music_streaming_app.converter.AudioRecordingConverter;
import com.music_streaming_app.dto.DtoAudioRecording;
import com.music_streaming_app.entity.AudioRecording;
import com.music_streaming_app.repository.RepositoryAudioRecordings;
import com.music_streaming_app.service.ServiceAudioRecordings;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ServiceAudioRecordingsImpl implements ServiceAudioRecordings {

    private static final Logger logger = LoggerFactory.getLogger(ServiceAudioRecordingsImpl.class);

    private final RepositoryAudioRecordings repositoryAudioRecordings;

    @Override
    public List<AudioRecording> getAllAudioRecordings() {
        logger.info("ServiceAudioRecordingsImpl getAllAudioRecordings start");
        List<AudioRecording> audioRecordings = repositoryAudioRecordings.findAll();
        logger.info("ServiceAudioRecordingsImpl getAllAudioRecordings end");
        return audioRecordings;
    }

    @Override
    public ResponseEntity<List<DtoAudioRecording>> getFeaturedAudioRecordings() {
        logger.info("ServiceAudioRecordingsImpl getFeaturedAudioRecordings start");
        ResponseEntity<List<DtoAudioRecording>> responseEntity = ResponseEntity.ok(
                repositoryAudioRecordings.findFeatured()
                        .stream()
                        .map(AudioRecordingConverter::toDto)
                        .toList()
        );
        logger.info("ServiceAudioRecordingsImpl getFeaturedAudioRecordings end");
        return responseEntity;
    }

    @Override
    public ResponseEntity<StreamingResponseBody> getAudioRecordingById(Long id) {
        logger.info("ServiceAudioRecordingsImpl getAudioRecordingById start");
        Optional<AudioRecording> audioRecordingOptional =
                Optional.of(repositoryAudioRecordings.getReferenceById(id));
        Blob audioBlob = audioRecordingOptional.get().getAudioBlob();
        StreamingResponseBody responseBody = AudioRecordingConverter.toStreamingResponseBody(audioBlob);
        HttpHeaders headers = setUpHttpHeaders(audioBlob);
        logger.info("ServiceAudioRecordingsImpl getAudioRecordingById end");
        return ResponseEntity.ok()
                .headers(headers)
                .body(responseBody);
    }

    @Override
    public void saveAudioRecording(DtoAudioRecording dtoAudioRecording) {
        logger.info("ServiceAudioRecordingsImpl saveAudioRecording start");
        AudioRecording audioRecording = AudioRecordingConverter.toAudioRecording(dtoAudioRecording);
        repositoryAudioRecordings.save(audioRecording);
        logger.info("ServiceAudioRecordingsImpl saveAudioRecording end");
    }

    @Override
    public ResponseEntity<List<DtoAudioRecording>> getAllDtoAudioRecordings() {
        logger.info("ServiceAudioRecordingsImpl getAllDtoAudioRecordings start");
        List<DtoAudioRecording> dtoAudioRecordings = getAllAudioRecordings().stream()
                .map(AudioRecordingConverter::toDto)
                .collect(Collectors.toList());
        logger.info("ServiceAudioRecordingsImpl getAllDtoAudioRecordings end");
        return ResponseEntity.ok(dtoAudioRecordings);
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
}
