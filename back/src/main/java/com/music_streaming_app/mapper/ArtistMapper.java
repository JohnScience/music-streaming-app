package com.music_streaming_app.mapper;

import com.music_streaming_app.dto.ArtistDto;
import com.music_streaming_app.entity.Artist;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    ArtistDto toDto(Artist artist);

    Artist toEntity(ArtistDto artistDto);

    List<ArtistDto> toDtoList(List<Artist> artists);

    List<Artist> toEntityList(List<ArtistDto> artistDtoList);
}
