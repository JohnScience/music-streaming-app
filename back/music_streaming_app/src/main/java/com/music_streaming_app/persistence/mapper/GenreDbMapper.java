package com.music_streaming_app.persistence.mapper;

import com.music_streaming_app.model.Genre;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GenreDbMapper {
    //language=sql
    @Insert("insert into genre (name) values #{name}")
    Long insert(@Param("value") String name);

    //language=sql
    @Results(id = "genre", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
    })
    @Select("select genre.id, genre.name from genre")
    List<Genre> selectAll();

    //language=sql
    @ResultMap("genre")
    @Select("select genre.id, genre.name from genre where id=#{id}")
    Genre selectById(@Param("id") Long id);

    //language=sql
    @Update("update genre set (name=#{genre.name}) where id = #{tag.id}")
    void update(@Param("genre") Genre genre);

    //language=sql
    @Delete("delete from genre where id=#{id}")
    void delete(@Param("id") Long id);
}
