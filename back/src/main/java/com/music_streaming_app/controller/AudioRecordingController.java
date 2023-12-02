package com.music_streaming_app.controller;

import com.music_streaming_app.dto.DtoAudioRecording;
import com.music_streaming_app.entity.AudioRecording;
import com.music_streaming_app.service.impl.ServiceAudioRecordingsImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/audio_recording")
public class AudioRecordingController {

    private static final Logger logger = LoggerFactory.getLogger(AudioRecordingController.class);

    private final ServiceAudioRecordingsImpl serviceAudioRecordingsImpl;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveAudioRecording(@ModelAttribute DtoAudioRecording dtoAudioRecording) {

        logger.info("Post dto: " + dtoAudioRecording.toString());
        boolean saveInDateBase = serviceAudioRecordingsImpl.saveAudioRecording(dtoAudioRecording);

        return ResponseEntity.ok("Audio recording " + saveInDateBase);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponseBody> getAudioRecording(@PathVariable Long id) {
        Optional<AudioRecording> audioRecordingOptional = serviceAudioRecordingsImpl.getAudioRecordingById(id);

        if (audioRecordingOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        //Чтобы не дергать бд несколько раз, то уже полученный объект передаём в метод
        return serviceAudioRecordingsImpl.getAudioRecording(audioRecordingOptional);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DtoAudioRecording>> getAllAudioRecordings() {
        return serviceAudioRecordingsImpl.getAllDtoAudioRecordings();
    }
}
