package com.example.problemnote.mapper;

import com.example.problemnote.pojo.CollectionProblem;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CollectionProblemMapper {
    int insert(CollectionProblem collectionProblem);
    int deleteByCollectionIdAndProblemId(Long collectionId, Long problemId);
    List<Long> selectProblemIdsByCollectionId(Long collectionId);
    boolean exists(Long collectionId, Long problemId);
}