package com.example.problemnote.service;

import com.example.problemnote.pojo.Collection;
import java.util.List;

public interface CollectionService {
    int createCollection(Collection collection);
    int updateCollection(Collection collection);
    int deleteCollection(Long id);
    Collection getCollectionById(Long id);
    List<Collection> getUserCollections(Long userId);
}