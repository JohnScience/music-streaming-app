package com.music_streaming_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoAudioRecording {
    private Long id;
    private MultipartFile file;
    private String author;
    private String description;
    private String sourceUrl;
    private LocalDateTime createdAt;
}
