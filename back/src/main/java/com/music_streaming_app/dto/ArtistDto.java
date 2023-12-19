package com.music_streaming_app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
}