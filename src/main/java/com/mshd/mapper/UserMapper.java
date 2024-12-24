package com.mshd.mapper;


import com.mshd.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO User ( username, password) VALUES ( #{username}, #{password})")
    boolean insert( @Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM user WHERE id = #{uid}")
    User findByUsername(@Param("username") String username);

    @Select("SELECT password FROM User WHERE username=#{username} LIMIT 1")
    String getPassword(@Param("username") String username);

    @Delete("DELETE FROM user WHERE id = #{uid}")
    boolean delete(@Param("uid") String uid);
}
