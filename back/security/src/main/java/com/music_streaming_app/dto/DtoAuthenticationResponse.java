package com.music_streaming_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoAuthenticationResponse {
    private String token;
}
