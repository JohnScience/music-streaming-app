package com.music_streaming_app.persistence.mapper;

import com.music_streaming_app.model.Track;
import com.music_streaming_app.persistence.handler.SetStringTypeHandler;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TrackDbMapper {
    //language=sql
    @Insert("insert into track (title, created, genre, duration, description, artist, tags) " +
    "values(#{track.title}, " +
            "#{track.created}, " +
            "(select genre.id from genre where #{track.genre} = genre.name), " +
            "#{track.duration}, " +
            "#{track.description}," +
            "(select artist.id from artist where artist.name = #{track.artist})," +
            "(select json_agg(id) from tag where value = any(#{track.tags, typeHandler=com.music_streaming_app.persistence.handler.SetStringTypeHandler})))")
    Long insert(@Param("track") Track track);

    @Results(id="track", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "sourceUrl", column = "source_url"),
            @Result(property = "created", column = "created"),
            @Result(property = "duration", column = "duration"),
            @Result(property = "thumbnail", column = "thumbnail"),
            @Result(property = "description", column = "description"),
            @Result(property = "artist", column = "artist"),
            @Result(property = "genre", column = "genre"),
            @Result(property = "tags", column = "tags", typeHandler = SetStringTypeHandler.class),
    })
    //language=sql
    @Select("select track.id, " +
            "track.title, " +
            "track.source_url, " +
            "track.created, " +
            "track.duration, " +
            "track.thumbnail, " +
            "track.description, " +
            "genre.name genre, " +
            "artist.name artist, " +
            "array_agg(tag.value) tags " +
            "from track " +
            "left join genre on track.genre=genre.id " +
            "left join artist on track.artist=artist.id " +
            "left join tag on tag.id in (select jsonb_array_elements(tags)::numeric) " +
            "group by track.id, track.title, track.source_url, track.created, track.duration, track.thumbnail, track.description, genre.name, artist.name")
    List<Track> selectAll();

    //language=sql
    @ResultMap("track")
    @Select("select track.id, " +
            "track.title, " +
            "track.source_url, " +
            "track.created, " +
            "track.duration, " +
            "track.thumbnail, " +
            "track.description, " +
            "genre.name genre, " +
            "artist.name artist, " +
            "array_agg(tag.value) tags " +
            "from track " +
            "left join genre on track.genre=genre.id " +
            "left join artist on track.artist=artist.id " +
            "left join tag on tag.id in (select jsonb_array_elements(tags)::numeric) " +
            "where track.id=#{id} " +
            "group by track.id, track.title, track.source_url, track.created, track.duration, track.thumbnail, track.description, genre.name, artist.name")
    Track selectById(Long id);

    //language=sql
    @Update("update track set " +
            "title=#{track.title}, " +
            "source_url=#{track.sourceUrl}, " +
            "created=#{track.created}, " +
            "duration=#{track.duration}, " +
            "thumbnail=#{track.thumbnail}, " +
            "description=#{track.title}, " +
            "genre= (select id from genre where name=#{track.genre}), " +
            "artist= (select id from artist where name = #{track.artist}), " +
            "tags= (select json_agg(id) from tag where value = any(#{track.tags, typeHandler=com.music_streaming_app.persistence.handler.SetStringTypeHandler})) " +
            "where track.id=#{track.id}")
    void update(@Param("track")Track track);

    //language=sql
    @Delete("delete from track where id=#{id}")
    void delete(@Param("id") Long id);
}
