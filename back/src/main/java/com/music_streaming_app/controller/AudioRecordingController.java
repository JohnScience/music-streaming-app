package com.music_streaming_app.controller;

import com.music_streaming_app.dto.DtoAudioRecording;
import com.music_streaming_app.service.ServiceAudioRecordings;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/audio_recording")
public class AudioRecordingController {

    private static final Logger logger = LoggerFactory.getLogger(AudioRecordingController.class);

    private final ServiceAudioRecordings serviceAudioRecordings;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveAudioRecording(@ModelAttribute DtoAudioRecording dtoAudioRecording) {
        logger.info("AudioRecordingController saveAudioRecording start: dtoAudioRecording = " + dtoAudioRecording.toString());
        logger.info("Post dto: " + dtoAudioRecording.toString());
        serviceAudioRecordings.saveAudioRecording(dtoAudioRecording);
        return ResponseEntity.ok("Audio recording is saved successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponseBody> getAudioRecording(@PathVariable Long id) {
        logger.info("AudioRecordingController getAudioRecording start: id = " + id);
        return serviceAudioRecordings.getAudioRecordingById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DtoAudioRecording>> getAllAudioRecordings() {
        logger.info("AudioRecordingController getAllAudioRecordings start");
        return serviceAudioRecordings.getAllDtoAudioRecordings();
    }

    @Operation(summary = "Получаем рекомендуемые записи из БД")
    @GetMapping("/featured")
    public ResponseEntity<List<DtoAudioRecording>> getFeaturedAudioRecordings() {
        logger.info("AudioRecordingController getFeaturedAudioRecordings start");
        return serviceAudioRecordings.getFeaturedAudioRecordings();
    }
}
