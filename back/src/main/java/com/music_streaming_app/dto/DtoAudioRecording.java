package com.music_streaming_app.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;

import java.util.UUID;

@Data
public class DtoAudioRecording {
    private UUID id;
    private MultipartFile file;
    private String author;
    private String description;
    private String sourceUrl;
    private LocalDateTime createdAt;

    public Blob getFileBlob() throws IOException, SQLException {
        if (file != null) {
            return new javax.sql.rowset.serial.SerialBlob(file.getBytes());
        }
        return null;
    }
}
