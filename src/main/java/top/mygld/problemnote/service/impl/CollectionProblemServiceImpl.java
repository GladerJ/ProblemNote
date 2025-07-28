package com.example.problemnote.service.impl;

import com.example.problemnote.mapper.CollectionMapper;
import com.example.problemnote.mapper.CollectionProblemMapper;
import com.example.problemnote.pojo.Collection;
import com.example.problemnote.pojo.CollectionProblem;
import com.example.problemnote.service.CollectionProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CollectionProblemServiceImpl implements CollectionProblemService {

    @Autowired
    private CollectionProblemMapper collectionProblemMapper;

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public int addToCollection(Long collectionId, Long problemId) {
        if (collectionProblemMapper.exists(collectionId, problemId)) {
            return 0; // 已存在，无需重复添加
        }
        CollectionProblem cp = new CollectionProblem();
        cp.setCollectionId(collectionId);
        cp.setProblemId(problemId);
        cp.setAddTime(new Date());
        return collectionProblemMapper.insert(cp);
    }

    @Override
    public int removeFromCollection(Long collectionId, Long problemId) {
        return collectionProblemMapper.deleteByCollectionIdAndProblemId(collectionId, problemId);
    }

    @Override
    public List<Long> getProblemIdsByCollectionId(Long collectionId) {
        return collectionProblemMapper.selectProblemIdsByCollectionId(collectionId);
    }

    @Override
    public boolean isProblemInCollection(Long collectionId, Long problemId) {
        return collectionProblemMapper.exists(collectionId, problemId);
    }

    @Transactional
    @Override
    public int createCollectionWithProblems(String name, List<Long> problemIds, Long userId) {
        // 创建错题集
        Collection collection = new Collection();
        collection.setName(name);
        collection.setUserId(userId);
        collection.setCreateTime(new Date());
        collection.setUpdateTime(new Date());
        collectionMapper.insert(collection);

        // 批量添加题目到错题集
        for (Long problemId : problemIds) {
            CollectionProblem cp = new CollectionProblem();
            cp.setCollectionId(collection.getId());
            cp.setProblemId(problemId);
            cp.setAddTime(new Date());
            collectionProblemMapper.insert(cp);
        }

        return 1;
    }
}