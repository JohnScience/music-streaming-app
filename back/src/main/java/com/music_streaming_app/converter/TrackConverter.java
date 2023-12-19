package com.music_streaming_app.converter;

import com.music_streaming_app.dto.TrackDto;
import com.music_streaming_app.entity.Track;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

@Component
public class TrackConverter {

    public static TrackDto toTrackDto(Track ar) {
        return TrackDto.builder()
                .id(ar.getId())
                .artist(ar.getArtist())
                .description(ar.getDescription())
                .sourceUrl(ar.getSourceUrl())
                .build();
    }

    public static StreamingResponseBody toStreamingResponseBody(Blob audioBlob) {
        StreamingResponseBody responseBody = outputStream -> {
            try (InputStream inputStream = audioBlob.getBinaryStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                outputStream.flush();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        };
        return responseBody;
    }

    public static Track toTrack(TrackDto trackDto) {
        Track track;
        try {
            track = Track.builder()
                    .audioBlob(new SerialBlob(trackDto.getFile().getBytes()))
                    .artist(trackDto.getArtist())
                    .description(trackDto.getDescription())
                    .sourceUrl(trackDto.getSourceUrl())
                    .build();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return track;
    }
}