package com.music_streaming_app.controller;

import com.music_streaming_app.model.Tag;
import com.music_streaming_app.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @PostMapping
    public Long create(@RequestBody Tag tag){
        return tagService.create(tag.getValue());
    }

    @GetMapping("/all")
    public List<Tag> getAll(){
        return tagService.getAll();
    }

    @GetMapping("/{id}")
    public Tag getById(@PathVariable("id") Long id){
        return tagService.getById(id);
    }

    @PutMapping
    public void update(@RequestBody Tag tag){
        tagService.update(tag);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        tagService.delete(id);
    }
}
