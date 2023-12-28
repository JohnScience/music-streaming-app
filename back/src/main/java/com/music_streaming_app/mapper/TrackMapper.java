package com.music_streaming_app.mapper;

import com.music_streaming_app.dto.TrackDto;
import com.music_streaming_app.entity.Track;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackMapper {

    TrackDto toDto(Track track);

    Track toEntity(TrackDto trackDto);

    List<TrackDto> toDtoList(List<Track> tracks);

    List<Track> toEntityList(List<TrackDto> trackDtoList);
}