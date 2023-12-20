package com.music_streaming_app.mapper;

import com.music_streaming_app.dto.GenreDto;
import com.music_streaming_app.entity.Genre;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {

    GenreDto toDto(Genre genre);

    Genre toEntity(GenreDto genreDto);

    List<GenreDto> toDtoList(List<Genre> genres);

    List<Genre> toEntityList(List<GenreDto> genreDtoList);
}