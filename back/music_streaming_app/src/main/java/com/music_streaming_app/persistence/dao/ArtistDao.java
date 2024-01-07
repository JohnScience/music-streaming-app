package com.music_streaming_app.persistence.dao;

import com.music_streaming_app.model.Artist;
import com.music_streaming_app.persistence.mapper.ArtistDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ArtistDao {
    private final ArtistDbMapper artistDbMapper;

    public Long createArtist(Artist artist) {
        return artistDbMapper.insert(artist);
    }

    public List<Artist> getAll() {
        return artistDbMapper.selectAll();
    }

    public Artist getOneById(Long id) {
        return artistDbMapper.selectById(id);
    }

    public void update(Artist artist) {
        artistDbMapper.update(artist);
    }

    public void delete(Long id) {
        artistDbMapper.delete(id);
    }
}
