package com.music_streaming_app.controller;

import com.music_streaming_app.dto.DtoAudioRecording;
import com.music_streaming_app.entity.AudioRecording;
import com.music_streaming_app.service.impl.ServiceAudioRecordingsImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/audio_recordings")
public class AudioRecordingController {

    private static final Logger logger = LoggerFactory.getLogger(AudioRecordingController.class);

    private final ServiceAudioRecordingsImpl serviceAudioRecordingsImpl;

    // Запись данных в БД
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> saveAudioRecording(@ModelAttribute DtoAudioRecording dtoAudioRecording) {

        logger.info("Post dto: " + dtoAudioRecording.toString());
        boolean saveInDateBase = serviceAudioRecordingsImpl.saveAudioRecording(dtoAudioRecording);

        return ResponseEntity.ok("Audio recording " + saveInDateBase);
    }

    // Получаем файл в виде потока
    @GetMapping("/{id}")
    public ResponseEntity<StreamingResponseBody> getAudioRecording(@PathVariable Long id) {

        // Получаем файловые данные из сервиса
        Optional<AudioRecording> audioRecordingOptional = serviceAudioRecordingsImpl.getAudioRecordingById(id);

        // Обрабатываем случай, когда аудиозапись не найдена
        if (audioRecordingOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Blob audioBlob = audioRecordingOptional.get().getAudioBlob();

        // Создаем объект StreamingResponseBody для потоковой передачи данных
        StreamingResponseBody responseBody = outputStream -> {
            // Получаем поток байтов из объекта Blob
            try (InputStream inputStream = audioBlob.getBinaryStream()) {
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
            headers.setContentLength((int) audioBlob.length());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Возвращаем StreamingResponseBody в ответе
        return ResponseEntity.ok()
                .headers(headers)
                .body(responseBody);
    }

    // Получаем все записи из БД
    @GetMapping
    public ResponseEntity<List<DtoAudioRecording>> getAllAudioRecordings() {
        List<AudioRecording> allAudioRecordings = serviceAudioRecordingsImpl.getAllAudioRecordings();

        List<DtoAudioRecording> dtoAudioRecordings = new ArrayList<>();

        for (AudioRecording audioRecording : allAudioRecordings) {
            DtoAudioRecording dtoAudioRecording = DtoAudioRecording.builder()
                    .id(audioRecording.getId())
                    .artist(audioRecording.getArtist())
                    .description(audioRecording.getDescription())
                    .sourceUrl(audioRecording.getSourceUrl())
                    .build();

            dtoAudioRecordings.add(dtoAudioRecording);
        }

        return ResponseEntity.ok(dtoAudioRecordings);
    }

    @Operation(summary = "Получаем рекомендуемые записи из БД")
    @GetMapping("/featured")
    public ResponseEntity<List<DtoAudioRecording>> getFeaturedAudioRecordings() {

        return ResponseEntity.ok(
                serviceAudioRecordingsImpl.getFeaturedAudioRecordings()
                        .stream()
                        .map(this::toDto)
                        .toList());

    }

    private DtoAudioRecording toDto(AudioRecording ar) {
        return DtoAudioRecording.builder()
                .id(ar.getId())
                .artist(ar.getArtist())
                .description(ar.getDescription())
                .sourceUrl(ar.getSourceUrl())
                .build();
    }
}
