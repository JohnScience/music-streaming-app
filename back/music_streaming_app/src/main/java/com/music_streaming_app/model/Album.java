package com.music_streaming_app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Album {
    private Long id;
    private String name;
    private LocalDate release;
    private Artist artist;
    private Set<Track> tracks;
    private String cover;
    private Set<String> tags;
}
