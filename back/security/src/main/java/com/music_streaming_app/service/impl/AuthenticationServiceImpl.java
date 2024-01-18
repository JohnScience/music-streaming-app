package com.music_streaming_app.service.impl;

import com.music_streaming_app.converter.UserConverter;
import com.music_streaming_app.dto.DtoJwtResponse;
import com.music_streaming_app.dto.DtoUserCredentialsRequest;
import com.music_streaming_app.entity.RefreshToken;
import com.music_streaming_app.entity.User;
import com.music_streaming_app.exception.UnauthorizedException;
import com.music_streaming_app.exception.UserAlreadyExistsException;
import com.music_streaming_app.service.AuthenticationService;
import com.music_streaming_app.service.RefreshTokenService;
import com.music_streaming_app.service.UserService;
import com.music_streaming_app.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final UserConverter userConverter;
    private final RefreshTokenService refreshTokenService;

    @Override
    public ResponseEntity<?> register(DtoUserCredentialsRequest registerRequest) {
        if (userService.isUserExistsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistsException(registerRequest.getEmail());
        }
        User user = userConverter.toUser(registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()), registerRequest.getRoles());
        userService.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<DtoJwtResponse> authenticate(DtoUserCredentialsRequest authRequest) {
        User user = (User) userService.loadUserByUsername(authRequest.getEmail());
        if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequest.getEmail());
            String jwtToken = jwtUtils.generateToken(user);
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + jwtToken)
                    .body(new DtoJwtResponse(jwtToken, refreshToken.getToken()));
        } else {
            throw new UnauthorizedException("Invalid credentials");
        }
    }
}
