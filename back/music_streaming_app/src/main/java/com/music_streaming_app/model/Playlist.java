package com.music_streaming_app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Playlist {
    private Long id;
    private String name;
    private List<Track> tracks;
    private String description;
    private String cover;
    private Set<String> tags;
}
