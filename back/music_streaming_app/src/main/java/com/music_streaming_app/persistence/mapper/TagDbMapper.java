package com.music_streaming_app.persistence.mapper;

import com.music_streaming_app.model.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TagDbMapper {
    //language=sql
    @Insert("insert into tag (value) values #{value}")
    Long insert(@Param("value") String value);

    //language=sql
    @Results(id = "tag", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "value", column = "value"),
    })
    @Select("select tag.id, tag.value from tag")
    List<Tag> selectAll();

    //language=sql
    @ResultMap("tag")
    @Select("select tag.id, tag.value from tag where id=#{id}")
    Tag selectById(@Param("id") Long id);

    //language=sql
    @Update("update tag set (value=#{tag.value}) where id = #{tag.id}")
    void update(@Param("tag") Tag tag);

    //language=sql
    @Delete("delete from tag where id=#{id}")
    void delete(@Param("id") Long id);
}
