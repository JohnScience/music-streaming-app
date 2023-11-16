package com.music_streaming_app.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class DtoAudioRecording {
    private Long id;
    private MultipartFile file;
    private String author;
    private String description;
    private String sourceUrl;
    private LocalDateTime createdAt;
}
