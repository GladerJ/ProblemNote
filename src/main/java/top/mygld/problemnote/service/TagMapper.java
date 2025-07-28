package top.mygld.problemnote.mapper;

import org.apache.ibatis.annotations.*;
import top.mygld.problemnote.pojo.Tag;
import java.util.List;

@Mapper
public interface TagMapper {

    @Select("SELECT * FROM tag WHERE subject_id = #{subjectId} ORDER BY id DESC")
    List<Tag> findBySubjectId(@Param("subjectId") Integer subjectId);

    @Select("SELECT COUNT(*) FROM tag WHERE subject_id = #{subjectId}")
    int countBySubjectId(@Param("subjectId") Integer subjectId);

    @Insert("INSERT INTO tag(name, subject_id) VALUES(#{name}, #{subjectId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Tag tag);

    @Update("UPDATE tag SET name = #{name} WHERE id = #{id}")
    int update(Tag tag);

    @Delete("DELETE FROM tag WHERE id = #{id}")
    int delete(@Param("id") Integer id);

    @Select("SELECT * FROM tag WHERE id = #{id}")
    Tag findById(@Param("id") Integer id);
}