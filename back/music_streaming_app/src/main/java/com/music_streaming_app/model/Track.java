package com.music_streaming_app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Track {
    private Long id;
    private String title;
    private String sourceUrl;
    private LocalDate created;
    private String genre;
    private Double duration;
    private String thumbnail;
    private String description;
    private String artist;
    private Set<String> tags;
}
