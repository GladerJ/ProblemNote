package top.mygld.problemnote.mapper;

import org.apache.ibatis.annotations.*;
import top.mygld.problemnote.pojo.User;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Insert("INSERT INTO user(username, password) VALUES(#{username}, #{password})")
    int insert(User user);
} 