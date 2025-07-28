package top.mygld.problemnote.mapper;

import org.apache.ibatis.annotations.*;
import top.mygld.problemnote.pojo.Collection;
import java.util.List;

@Mapper
public interface CollectionMapper {
    
    @Select("SELECT * FROM collection ORDER BY id DESC")
    List<Collection> findAll();
    
    @Select("SELECT * FROM collection WHERE id = #{id}")
    Collection findById(Long id);
    
    @Insert("INSERT INTO collection(name) VALUES(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Collection collection);
    
    @Update("UPDATE collection SET name = #{name} WHERE id = #{id}")
    int update(Collection collection);
    
    @Delete("DELETE FROM collection WHERE id = #{id}")
    int deleteById(Long id);
}
