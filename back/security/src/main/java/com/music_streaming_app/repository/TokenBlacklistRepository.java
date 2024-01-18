package com.music_streaming_app.repository;

import com.music_streaming_app.entity.BlockedTokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenBlacklistRepository extends JpaRepository<BlockedTokens, Long> {
    Optional<BlockedTokens> findByToken(String token);
}
