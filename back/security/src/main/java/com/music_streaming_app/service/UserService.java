package com.music_streaming_app.service;

import com.music_streaming_app.dto.DtoRoleActionsRequest;
import com.music_streaming_app.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    UserDetails loadUserByUsername(String email);

    @Transactional
    void save(User user);

    boolean isUserExistsByEmail(String email);

    @Transactional
    ResponseEntity<?> addRole(DtoRoleActionsRequest request);

    @Transactional
    ResponseEntity<?> deleteRole(DtoRoleActionsRequest request);
}
