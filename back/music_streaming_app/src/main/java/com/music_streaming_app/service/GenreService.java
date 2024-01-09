package com.music_streaming_app.service;

import com.music_streaming_app.model.Genre;
import com.music_streaming_app.persistence.dao.GenreDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreDao genreDao;

    public Long create(String name) {
        return genreDao.create(name);
    }

    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    public Genre getById(Long id) {
        return genreDao.getById(id);
    }

    public void update(Genre genre) {
        genreDao.update(genre);
    }

    public void delete(Long id) {
        genreDao.delete(id);
    }
}
