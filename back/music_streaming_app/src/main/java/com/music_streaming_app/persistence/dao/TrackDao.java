package com.music_streaming_app.persistence.dao;

import com.music_streaming_app.model.Track;
import com.music_streaming_app.persistence.mapper.TrackDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TrackDao {
    private final TrackDbMapper trackDbMapper;

    public Long insert(Track track){
        return trackDbMapper.insert(track);
    }

    public List<Track> getAll(){
        return trackDbMapper.selectAll();
    }

    public Track getById(Long id){
        return trackDbMapper.selectById(id);
    }

    public void update(Track track){
        trackDbMapper.update(track);
    }

    public void delete(Long id){
        trackDbMapper.delete(id);
    }
}
