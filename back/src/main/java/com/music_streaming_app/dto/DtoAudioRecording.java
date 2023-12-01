package com.music_streaming_app.dto;

import com.music_streaming_app.entity.Artist;
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
    private Artist artist;
    private String description;
    private String sourceUrl;
}
