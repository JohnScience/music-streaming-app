package com.music_streaming_app.service;

import com.music_streaming_app.dto.DtoJwtResponse;
import com.music_streaming_app.dto.DtoUserCredentialsRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface AuthenticationService {
    @Transactional
    ResponseEntity<?> register(DtoUserCredentialsRequest registerRequest);

    ResponseEntity<DtoJwtResponse> authenticate(DtoUserCredentialsRequest authRequest);
}
