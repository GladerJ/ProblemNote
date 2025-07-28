package com.example.problemnote.mapper;

import com.example.problemnote.pojo.Collection;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CollectionMapper {
    int insert(Collection collection);
    int update(Collection collection);
    int deleteById(Long id);
    Collection selectById(Long id);
    List<Collection> selectByUserId(Long userId);
}