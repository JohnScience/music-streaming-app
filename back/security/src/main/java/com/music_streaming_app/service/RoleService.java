package com.music_streaming_app.service;

import com.music_streaming_app.entity.Role;
import org.springframework.transaction.annotation.Transactional;

public interface RoleService {
    Role findByName(String name);

    @Transactional
    void save(Role role);

    @Transactional
    void delete(Role role);
}
