package com.music_streaming_app.persistence.dao;

import com.music_streaming_app.model.Genre;
import com.music_streaming_app.persistence.mapper.GenreDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreDao {
    private final GenreDbMapper genreDbMapper;

    public Long create(String name) {
        return genreDbMapper.insert(name);
    }

    public List<Genre> getAll() {
        return genreDbMapper.selectAll();
    }

    public Genre getById(Long id) {
        return genreDbMapper.selectById(id);
    }

    public void update(Genre genre) {
        genreDbMapper.update(genre);
    }

    public void delete(Long id) {
        genreDbMapper.delete(id);
    }
}
