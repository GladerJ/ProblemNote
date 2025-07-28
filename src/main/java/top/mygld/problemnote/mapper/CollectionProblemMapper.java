package top.mygld.problemnote.mapper;

import org.apache.ibatis.annotations.*;
import top.mygld.problemnote.pojo.CollectionProblem;
import java.util.List;

@Mapper
public interface CollectionProblemMapper {
    
    @Select("SELECT * FROM collection_problem WHERE collection_id = #{collectionId}")
    List<CollectionProblem> findByCollectionId(Long collectionId);
    
    @Select("SELECT * FROM collection_problem WHERE problem_id = #{problemId}")
    List<CollectionProblem> findByProblemId(Integer problemId);
    
    @Select("SELECT * FROM collection_problem WHERE collection_id = #{collectionId} AND problem_id = #{problemId}")
    CollectionProblem findByCollectionAndProblem(@Param("collectionId") Long collectionId, @Param("problemId") Integer problemId);
    
    @Select("SELECT COUNT(*) FROM collection_problem WHERE problem_id = #{problemId}")
    int countByProblemId(Integer problemId);
    
    @Insert("INSERT INTO collection_problem(collection_id, problem_id) VALUES(#{collectionId}, #{problemId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(CollectionProblem collectionProblem);
    
    @Delete("DELETE FROM collection_problem WHERE collection_id = #{collectionId} AND problem_id = #{problemId}")
    int deleteByCollectionAndProblem(@Param("collectionId") Long collectionId, @Param("problemId") Integer problemId);
    
    @Delete("DELETE FROM collection_problem WHERE collection_id = #{collectionId}")
    int deleteByCollectionId(Long collectionId);
    
    @Delete("DELETE FROM collection_problem WHERE problem_id = #{problemId}")
    int deleteByProblemId(Integer problemId);
}
