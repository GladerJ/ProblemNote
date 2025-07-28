package top.mygld.problemnote.mapper;

import org.apache.ibatis.annotations.*;
import top.mygld.problemnote.pojo.Subject;
import java.util.List;

@Mapper
public interface SubjectMapper {
    @Select("SELECT * FROM subject")
    List<Subject> findAll();

    @Select("SELECT * FROM subject WHERE id = #{id}")
    Subject findById(@Param("id") Integer id);

    @Insert("INSERT INTO subject(name) VALUES(#{name})")
    int insert(Subject subject);

    @Update("UPDATE subject SET name = #{name} WHERE id = #{id}")
    int update(Subject subject);

    @Delete("DELETE FROM subject WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT * FROM subject WHERE name = #{name}")
    Subject findByName(@Param("name") String name);
} 