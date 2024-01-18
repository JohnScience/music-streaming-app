package com.music_streaming_app.service.impl;

import com.music_streaming_app.entity.BlockedTokens;
import com.music_streaming_app.entity.User;
import com.music_streaming_app.repository.TokenBlacklistRepository;
import com.music_streaming_app.service.RefreshTokenService;
import com.music_streaming_app.service.TokenBlacklistService;
import com.music_streaming_app.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenBlacklistServiceImpl implements TokenBlacklistService {

    private final TokenBlacklistRepository repository;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;

    @Override
    public void addToBlackList(String token) {
        repository.save(new BlockedTokens(token));
    }

    @Override
    public boolean isBlacklisted(String token) {
        Optional<BlockedTokens> tokenOpt = repository.findByToken(token);
        return tokenOpt.isPresent();
    }

    @Override
    public ResponseEntity<?> logout(HttpServletRequest request, Authentication authentication) {
        final String token = jwtUtils.extractTokenFromRequest(request);
        addToBlackList(token);
        User user = (User) authentication.getPrincipal();
        System.out.println("logout: user is " + user);
        refreshTokenService.delete(user);
        return ResponseEntity.ok().build();
    }
}
