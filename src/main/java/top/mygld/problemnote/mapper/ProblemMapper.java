package top.mygld.problemnote.mapper;

import org.apache.ibatis.annotations.*;
import top.mygld.problemnote.pojo.Problem;
import java.util.List;

@Mapper
public interface ProblemMapper {
    @Select("SELECT * FROM problem WHERE id = #{id}")
    Problem findById(Integer id);

    @Select("SELECT * FROM problem WHERE subject_id = #{subjectId} AND tag_id = #{tagId}")
    List<Problem> findBySubjectAndTag(@Param("subjectId") Integer subjectId, @Param("tagId") Integer tagId);

    @Select("SELECT * FROM problem WHERE tag_id = #{tagId} ORDER BY id ASC")
    List<Problem> findByTagId(Integer tagId);

    // 根据错题集ID查询题目
    @Select("SELECT p.* FROM problem p " +
            "INNER JOIN collection_problem cp ON p.id = cp.problem_id " +
            "WHERE cp.collection_id = #{collectionId} " +
            "ORDER BY cp.id DESC")
    List<Problem> findByCollectionId(Long collectionId);

    // 查询错题标记状态
    @Select("SELECT COUNT(*) FROM collection_problem WHERE problem_id = #{problemId}")
    int countMarkedProblems(@Param("problemId") Integer problemId);
    
    // 查询所有错题
    @Select("SELECT p.* FROM problem p " +
            "INNER JOIN collection_problem cp ON p.id = cp.problem_id " +
            "ORDER BY cp.id DESC")
    List<Problem> findMarkedProblems();
    
    // 根据标签查询错题
    @Select("SELECT p.* FROM problem p " +
            "INNER JOIN collection_problem cp ON p.id = cp.problem_id " +
            "WHERE p.tag_id = #{tagId} " +
            "ORDER BY cp.id DESC")
    List<Problem> findMarkedProblemsByTag(@Param("tagId") Integer tagId);

    @Insert("INSERT INTO problem(content, answer, subject_id, tag_id) VALUES(#{content}, #{answer}, #{subjectId}, #{tagId})")
    int insert(Problem problem);

    @Update("UPDATE problem SET content = #{content}, answer = #{answer}, tag_id = #{tagId} WHERE id = #{id}")
    int update(Problem problem);

    @Delete("DELETE FROM problem WHERE id = #{id}")
    int delete(@Param("id") Integer id);
}