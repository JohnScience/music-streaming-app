package com.music_streaming_app.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Entity
@Table(name = "token_blacklist")
public class BlockedTokens {
    @Id
    @GeneratedValue
    private long id;
    @Column(length = 500)
    @NonNull
    private String token;
}
