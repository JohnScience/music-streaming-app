package com.music_streaming_app.service.impl;

import com.music_streaming_app.dto.DtoRoleActionsRequest;
import com.music_streaming_app.entity.Role;
import com.music_streaming_app.entity.User;
import com.music_streaming_app.repository.UserRepository;
import com.music_streaming_app.service.RoleService;
import com.music_streaming_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.orElseThrow(() -> new UsernameNotFoundException("User with email = " + email + " not found"));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean isUserExistsByEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.isPresent();
    }

    @Override
    public ResponseEntity<?> addRole(DtoRoleActionsRequest request) {
        User user = (User) loadUserByUsername(request.getEmail());
        Role role = new Role(request.getRole());
        roleService.save(role);
        user.addRole(role);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<?> deleteRole(DtoRoleActionsRequest request) {
        User user = (User) loadUserByUsername(request.getEmail());
        Role role = roleService.findByName(request.getRole());
        user.deleteRole(role);
        roleService.delete(role);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
