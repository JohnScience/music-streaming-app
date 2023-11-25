package com.music_streaming_app.repository;

import com.music_streaming_app.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RepositoryAuthors extends JpaRepository<Author, Long> {
}
