package com.example.problemnote.service;

import java.util.List;

public interface CollectionProblemService {
    int addToCollection(Long collectionId, Long problemId);
    int removeFromCollection(Long collectionId, Long problemId);
    List<Long> getProblemIdsByCollectionId(Long collectionId);
    boolean isProblemInCollection(Long collectionId, Long problemId);
    int createCollectionWithProblems(String name, List<Long> problemIds, Long userId);
}