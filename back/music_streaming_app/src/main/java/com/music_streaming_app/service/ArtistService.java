package com.music_streaming_app.service;

import com.music_streaming_app.model.Artist;
import com.music_streaming_app.persistence.dao.ArtistDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistDao artistDao;

    public Long createArtist(Artist artist) {
        return artistDao.createArtist(artist);
    }

    public List<Artist> getAll() {
        return artistDao.getAll();
    }

    public Artist getOneById(Long id) {
        return artistDao.getOneById(id);
    }

    public void update(Artist artist) {
        artistDao.update(artist);
    }

    public void delete(Long id) {
        artistDao.delete(id);
    }
}
