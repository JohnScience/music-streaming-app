package com.music_streaming_app.persistence.mapper;

import com.music_streaming_app.model.Artist;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArtistDbMapper {
    //language=sql
    @Insert("insert into artist (name, description, since) " +
            "values (#{artist.name}, #{artist.description}, #{artist.since})")
    @Options(useGeneratedKeys = true, keyProperty = "artist.id", keyColumn = "id")
    Long insert(@Param("artist") Artist artist);

    //language=sql
    @Results(id = "artist", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "description", column = "description"),
            @Result(property = "since", column = "since")
    })
    @Select("select artist.id, artist.name, artist.description, artist.since " +
            "from artist")
    List<Artist> selectAll();

    //language=sql
    @Select("select artist.id, artist.name, artist.description, artist.since " +
            "from artist " +
            "where artist.id=#{id}")
    Artist selectById(@Param("id") Long id);

    //language=sql
    @Update("update artist set (" +
            " artist.name = #{artist.name}," +
            " artist.description = #{artist.description}," +
            " artist.since=#{artist.since}" +
            ") where artist.id = #{artist.id}")
    void update(@Param("artist") Artist artist);

    //language=sql
    @Delete("delete from artist where artist.id = #{id}")
    void delete(@Param("id") Long id);
}
