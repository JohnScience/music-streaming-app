package com.music_streaming_app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DtoRoleActionsRequest {
    private String email;
    private String role;
}
