package com.music_streaming_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "audio_recordings")
public class AudioRecording {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "source_url", nullable = false)
    private String sourceUrl;

    @Column(name = "duration_secs", nullable = false)
    private int durationSecs;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Lob
    @Column(name = "audio_blob", nullable = false)
    private Blob audioBlob;

    @Lob
    @Column(name = "thumbnail")
    private Blob thumbnail;

    @ManyToMany(mappedBy = "audioRecordings")
    private Set<Author> authors = new java.util.LinkedHashSet<>();

}
