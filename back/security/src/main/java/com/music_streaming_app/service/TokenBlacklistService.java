package com.music_streaming_app.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

public interface TokenBlacklistService {
    @Transactional
    ResponseEntity<?> logout(HttpServletRequest request, Authentication authentication);

    boolean isBlacklisted(String token);

    void addToBlackList(String token);
}
