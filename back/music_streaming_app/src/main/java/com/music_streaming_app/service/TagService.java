package com.music_streaming_app.service;

import com.music_streaming_app.model.Tag;
import com.music_streaming_app.persistence.dao.TagDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagDao tagDao;

    public Long create(String value){
        return tagDao.create(value);
    }

    public List<Tag> getAll(){
        return tagDao.getAll();
    }

    public Tag getById(Long id){
        return tagDao.getById(id);
    }

    public void update(Tag tag){
        tagDao.update(tag);
    }

    public void delete(Long id){
        tagDao.delete(id);
    }
}
