package com.pvdnc.notebook.mapper;

import com.pvdnc.notebook.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from tb_user")
    List<User> findAll();

    @Select("select * from tb_user where name=#{name}")
    User getUser(@Param("name") String name);

    @Select("select password from tb_user where name=#{name}")
    String getPassword(@Param("name") String name);

    @Insert("insert into tb_user(name, password) values(#{name}, #{password})")
    Integer addUser(User user);

    @Delete("delete from tb_user where name = #{name}")
    Integer deletePerson(@Param("name") String name);
}
