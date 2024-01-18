package com.music_streaming_app.service.impl;

import com.music_streaming_app.dto.DtoJwtResponse;
import com.music_streaming_app.dto.DtoRefreshTokenRequest;
import com.music_streaming_app.entity.RefreshToken;
import com.music_streaming_app.entity.User;
import com.music_streaming_app.repository.RefreshTokenRepository;
import com.music_streaming_app.service.RefreshTokenService;
import com.music_streaming_app.service.UserService;
import com.music_streaming_app.util.JwtUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository tokenRepository;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @Override
    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder()
                .user((User) userService.loadUserByUsername(username))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        return tokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken findByToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException("Refresh token not found"));
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            tokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " is expired. Please make a new login!");
        }
        return token;
    }

    @Override
    public ResponseEntity<DtoJwtResponse> refreshToken(DtoRefreshTokenRequest request) {
        return ResponseEntity.ok(
                Stream.of(findByToken(request.getToken()))
                        .map(this::verifyExpiration)
                        .map(RefreshToken::getUser)
                        .map(user -> {
                            String accessToken = jwtUtils.generateToken(user);
                            return DtoJwtResponse.builder()
                                    .accessToken(accessToken)
                                    .refreshToken(request.getToken())
                                    .build();
                        }).findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("Refresh token not found"))
        );
    }

    @Override
    public void delete(User user) {
        tokenRepository.deleteByUserId(user.getId());
    }
}
