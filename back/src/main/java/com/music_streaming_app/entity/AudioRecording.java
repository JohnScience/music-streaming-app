package com.music_streaming_app.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "audio_recordings")
public class AudioRecording {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Lob
    @Column(name = "audio_blob", nullable = false)
    private Blob audioBlob;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "sourceUrl", nullable = false)
    private String sourceUrl;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;
}
