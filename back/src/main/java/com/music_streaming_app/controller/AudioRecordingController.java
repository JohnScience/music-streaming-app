package com.music_streaming_app.controller;

import com.music_streaming_app.dto.DtoAudioRecording;
import com.music_streaming_app.entity.EntityAudioRecording;
import com.music_streaming_app.service.ServiceAudioRecordings;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/audio_recordings")
public class AudioRecordingController {

    private static final Logger logger = LoggerFactory.getLogger(AudioRecordingController.class);
    private final ServiceAudioRecordings serviceAudioRecordings;

    public AudioRecordingController(ServiceAudioRecordings serviceAudioRecordings) {
        this.serviceAudioRecordings = serviceAudioRecordings;
    }

    // Запись данных в БД
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createAudioRecording(@ModelAttribute DtoAudioRecording dtoAudioRecording) {

        logger.info("Post dto: " + dtoAudioRecording.toString());
        boolean saveInDateBase = serviceAudioRecordings.saveAudioRecording(dtoAudioRecording);

        return ResponseEntity.ok("Audio recording " + saveInDateBase);
    }


    // Получаем файл в виде потока
    @GetMapping("/audio/{id}")
    public ResponseEntity<StreamingResponseBody> getAudioRecording(@PathVariable UUID id) {

        // Получаем файловые данные из сервиса
        byte[] fileBytes = serviceAudioRecordings.getAudioRecordingById(id).getAudioBlob();

        // Создаем объект StreamingResponseBody для потоковой передачи данных
        StreamingResponseBody responseBody = outputStream -> {
            // Записываем файловые данные в выходной поток
            outputStream.write(fileBytes);
            outputStream.flush();
        };

        // Устанавливаем заголовки ответа для указания типа контента и длины файла
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(fileBytes.length);

        // Вернем StreamingResponseBody в ответе
        return ResponseEntity.ok()
                .headers(headers)
                .body(responseBody);
    }

    // Получаем все записи из БД
    @GetMapping("/allAudio")
    public ResponseEntity<List<DtoAudioRecording>> getAllAudioRecordings() {

        List<EntityAudioRecording> allAudioRecordings = serviceAudioRecordings.getAllAudioRecordings();

        List<DtoAudioRecording> dtoAudioRecordings = new ArrayList<>();

        for (EntityAudioRecording entityAudioRecording : allAudioRecordings) {
            DtoAudioRecording dtoAudioRecording = new DtoAudioRecording();
            dtoAudioRecording.setId(entityAudioRecording.getId());
            dtoAudioRecording.setAuthor(entityAudioRecording.getAuthor());
            dtoAudioRecording.setDescription(entityAudioRecording.getDescription());
            dtoAudioRecording.setSourceUrl(entityAudioRecording.getSourceUrl());
            dtoAudioRecording.setCreatedAt(entityAudioRecording.getCreatedAt());
            dtoAudioRecordings.add(dtoAudioRecording);
        }

        return ResponseEntity.ok(dtoAudioRecordings);
    }


}
