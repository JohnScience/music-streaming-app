package com.music_streaming_app.persistence.dao;

import com.music_streaming_app.model.Tag;
import com.music_streaming_app.persistence.mapper.TagDbMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TagDao {
    private final TagDbMapper tagDbMapper;

    public Long create(String value){
        return tagDbMapper.insert(value);
    }

    public List<Tag> getAll(){
        return tagDbMapper.selectAll();
    }

    public Tag getById(Long id){
        return tagDbMapper.selectById(id);
    }

    public void update(Tag tag){
        tagDbMapper.update(tag);
    }

    public void delete(Long id){
        tagDbMapper.delete(id);
    }
}
