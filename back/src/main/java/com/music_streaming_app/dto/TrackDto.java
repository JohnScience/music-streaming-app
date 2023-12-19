package com.music_streaming_app.dto;

import com.music_streaming_app.entity.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrackDto {

    private Long id;
    private String title;
    private Artist artist;
    private String description;
    private String sourceUrl;
    private int durationSecs;
    private LocalDateTime createdAt;
    private Blob audioBlob;
    private Blob cover;
}