package com.music_streaming_app.converter;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

@Component
public class BlobConverter {

    private static final int BUFFER_SIZE = 4096;

    public StreamingResponseBody toStreamingResponseBody(Blob audioBlob) {
        return outputStream -> {
            try (InputStream inputStream = audioBlob.getBinaryStream()) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
    }
}