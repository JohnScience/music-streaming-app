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
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @Override
    public String toString() {
        return this.name;
    }
}
