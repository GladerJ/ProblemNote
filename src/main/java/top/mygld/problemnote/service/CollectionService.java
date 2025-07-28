package top.mygld.problemnote.service;

import top.mygld.problemnote.pojo.Collection;
import top.mygld.problemnote.common.PageResult;
import java.util.List;

public interface CollectionService {
    
    List<Collection> getAllCollections();
    
    PageResult<Collection> getAllCollectionsWithPage(Integer pageNum, Integer pageSize);
    
    Collection getCollectionById(Long id);
    
    void addCollection(Collection collection);
    
    void updateCollection(Collection collection);
    
    void deleteCollection(Long id);
    
    // 创建默认错题集
    Collection createDefaultCollection();
    
    // 获取或创建默认错题集
    Collection getOrCreateDefaultCollection();
}
