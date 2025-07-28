package top.mygld.problemnote.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mygld.problemnote.mapper.CollectionMapper;
import top.mygld.problemnote.pojo.Collection;
import top.mygld.problemnote.service.CollectionProblemService;
import top.mygld.problemnote.service.CollectionService;
import top.mygld.problemnote.common.PageResult;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    
    @Autowired
    private CollectionMapper collectionMapper;
    
    @Autowired
    private CollectionProblemService collectionProblemService;
    
    @Override
    public List<Collection> getAllCollections() {
        return collectionMapper.findAll();
    }
    
    @Override
    public PageResult<Collection> getAllCollectionsWithPage(Integer pageNum, Integer pageSize) {
        // 使用PageHelper进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<Collection> collections = collectionMapper.findAll();
        
        // 获取分页信息
        PageInfo<Collection> pageInfo = new PageInfo<>(collections);
        
        // 构建PageResult
        PageResult<Collection> pageResult = new PageResult<>();
        pageResult.setList(collections);
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setPages(pageInfo.getPages());
        
        return pageResult;
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
        // 先删除关联的错题记录
        collectionProblemService.deleteByCollectionId(id);
        // 再删除错题集
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
