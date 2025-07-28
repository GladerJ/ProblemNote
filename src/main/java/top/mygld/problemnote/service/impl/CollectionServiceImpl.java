package com.example.problemnote.service.impl;

import com.example.problemnote.mapper.CollectionMapper;
import com.example.problemnote.pojo.Collection;
import com.example.problemnote.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public int createCollection(Collection collection) {
        return collectionMapper.insert(collection);
    }

    @Override
    public int updateCollection(Collection collection) {
        return collectionMapper.update(collection);
    }

    @Override
    public int deleteCollection(Long id) {
        return collectionMapper.deleteById(id);
    }

    @Override
    public Collection getCollectionById(Long id) {
        return collectionMapper.selectById(id);
    }

    @Override
    public List<Collection> getUserCollections(Long userId) {
        return collectionMapper.selectByUserId(userId);
    }
}