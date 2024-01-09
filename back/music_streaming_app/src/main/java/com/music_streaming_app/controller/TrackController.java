package com.music_streaming_app.controller;

import com.music_streaming_app.model.Track;
import com.music_streaming_app.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/track")
@RequiredArgsConstructor
public class TrackController {
    private final TrackService trackService;
    @PostMapping
    public Long create(@RequestBody Track track){
        return trackService.create(track);
    };

    @GetMapping("/all")
    public List<Track> getAll(){
        return trackService.getAll();
    }
}
