package top.mygld.problemnote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mygld.problemnote.mapper.CollectionMapper;
import top.mygld.problemnote.pojo.Collection;
import top.mygld.problemnote.service.CollectionService;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    
    @Autowired
    private CollectionMapper collectionMapper;
    
    @Override
    public List<Collection> getAllCollections() {
        return collectionMapper.findAll();
    }
    
    @Override
    public Collection getCollectionById(Long id) {
        return collectionMapper.findById(id);
    }
    
    @Override
    public void addCollection(Collection collection) {
        collectionMapper.insert(collection);
    }
    
    @Override
    public void updateCollection(Collection collection) {
        collectionMapper.update(collection);
    }
    
    @Override
    public void deleteCollection(Long id) {
        collectionMapper.deleteById(id);
    }
    
    @Override
    public Collection createDefaultCollection() {
        // 检查是否已存在默认错题集
        List<Collection> collections = getAllCollections();
        if (!collections.isEmpty()) {
            return collections.get(0); // 返回第一个错题集作为默认
        }
        
        // 创建默认错题集
        Collection defaultCollection = new Collection();
        defaultCollection.setName("默认错题集");
        addCollection(defaultCollection);
        return defaultCollection;
    }
    
    // 获取或创建默认错题集
    public Collection getOrCreateDefaultCollection() {
        List<Collection> collections = getAllCollections();
        if (collections.isEmpty()) {
            return createDefaultCollection();
        }
        return collections.get(0);
    }
}
