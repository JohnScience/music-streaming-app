package com.music_streaming_app.service;

import com.music_streaming_app.model.Track;
import com.music_streaming_app.persistence.dao.TrackDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackService {
    private final TrackDao trackDao;

    public Long create(Track track){
        return trackDao.insert(track);
    }
    public List<Track> getAll(){
        return trackDao.getAll();
    }
}
