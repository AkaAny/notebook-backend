package com.pvdnc.notebook.mapper;

import com.pvdnc.notebook.entity.Note;
import com.pvdnc.notebook.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("select * from tb_note where isPrivate=0")
    List<Note> getNoteForGuest();

    @Select("select * from tb_note where fromUser=#{name} or isPrivate=0")
    List<Note> getNoteList(@Param("name") String name);

    @Select("select * from tb_note where id=#{id}")
    Note getNoteById(@Param("id") Long id);

    @Select("select * from tb_note where fromUser=#{name}")
    List<Note> getUserNote(@Param("name") String name);

    @Insert("insert into tb_note(fromUser,isPrivate,createdDate,title,content) values(#{fromUser},#{isPrivate},now(),#{title},#{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    Integer addNote(Note note);

    @Delete("delete from tb_note where id=#{id}")
    Integer deleteNote(Long id);

    @Update("update tb_note set isPrivate=#{isPrivate},createdDate=now(),title=#{title},content=#{content} where id=#{id}")
    Integer updateNote(Note note);
}
