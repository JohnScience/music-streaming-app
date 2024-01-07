package com.music_streaming_app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Artist {
    private Long id;
    private String name;
    private String description;
    private LocalDate since;
}
