package com.music_streaming_app.controller;

import com.music_streaming_app.dto.DtoAudioRecording;
import com.music_streaming_app.entity.EntityAudioRecording;
import com.music_streaming_app.service.impl.ServiceAudioRecordingsImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/audio_recordings")
public class AudioRecordingController {

    private static final Logger logger = LoggerFactory.getLogger(AudioRecordingController.class);

    private final ServiceAudioRecordingsImpl serviceAudioRecordingsImpl;

    public AudioRecordingController(ServiceAudioRecordingsImpl serviceAudioRecordingsImpl) {
        this.serviceAudioRecordingsImpl = serviceAudioRecordingsImpl;
    }

    // Запись данных в БД
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createAudioRecording(@ModelAttribute DtoAudioRecording dtoAudioRecording) {

        logger.info("Post dto: " + dtoAudioRecording.toString());
        boolean saveInDateBase = serviceAudioRecordingsImpl.saveAudioRecording(dtoAudioRecording);

        return ResponseEntity.ok("Audio recording " + saveInDateBase);
    }


    // Получаем файл в виде потока
    @GetMapping("/audio/{id}")
    public ResponseEntity<StreamingResponseBody> getAudioRecording(@PathVariable Long id) {

        // Получаем файловые данные из сервиса
        Optional<EntityAudioRecording> audioRecordingOptional = serviceAudioRecordingsImpl.getAudioRecordingById(id);

        if (audioRecordingOptional.isPresent()) {
            EntityAudioRecording audioRecording = audioRecordingOptional.get();
            Blob fileBytes = audioRecording.getAudioBlob();

            // Создаем объект StreamingResponseBody для потоковой передачи данных
            StreamingResponseBody responseBody = outputStream -> {
                // Получаем поток байтов из объекта Blob
                try (InputStream inputStream = fileBytes.getBinaryStream()) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        // Записываем байты в выходной поток
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.flush();
                } catch (SQLException | IOException e) {
                    throw new RuntimeException(e);
                }
            };

            // Устанавливаем заголовки ответа для указания типа контента и длины файла
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            try {
                headers.setContentLength((int) fileBytes.length());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Возвращаем StreamingResponseBody в ответе
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(responseBody);
        } else {
            // Обрабатываем случай, когда аудиозапись не найдена
            return ResponseEntity.notFound().build();
        }
    }

    // Получаем все записи из БД
    @GetMapping("/allAudio")
    public ResponseEntity<List<DtoAudioRecording>> getAllAudioRecordings() {

        List<EntityAudioRecording> allAudioRecordings = serviceAudioRecordingsImpl.getAllAudioRecordings();

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
