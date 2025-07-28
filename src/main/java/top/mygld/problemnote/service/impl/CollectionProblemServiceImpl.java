package top.mygld.problemnote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mygld.problemnote.mapper.CollectionProblemMapper;
import top.mygld.problemnote.mapper.ProblemMapper;
import top.mygld.problemnote.pojo.CollectionProblem;
import top.mygld.problemnote.pojo.Problem;
import top.mygld.problemnote.service.CollectionProblemService;
import top.mygld.problemnote.common.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

@Service
public class CollectionProblemServiceImpl implements CollectionProblemService {
    
    @Autowired
    private CollectionProblemMapper collectionProblemMapper;
    
    @Autowired
    private ProblemMapper problemMapper;
    
    @Override
    public void addProblemToCollection(Long collectionId, Integer problemId) {
        System.out.println("添加题目到错题集: collectionId=" + collectionId + ", problemId=" + problemId);
        // 检查是否已存在
        if (!isProblemInCollection(collectionId, problemId)) {
            CollectionProblem collectionProblem = new CollectionProblem();
            collectionProblem.setCollectionId(collectionId);
            collectionProblem.setProblemId(problemId);
            int result = collectionProblemMapper.insert(collectionProblem);
            System.out.println("插入结果: " + result);
        } else {
            System.out.println("题目已在错题集中");
        }
    }
    
    @Override
    public void removeProblemFromCollection(Long collectionId, Integer problemId) {
        collectionProblemMapper.deleteByCollectionAndProblem(collectionId, problemId);
    }
    
    @Override
    public void deleteByCollectionId(Long collectionId) {
        collectionProblemMapper.deleteByCollectionId(collectionId);
    }
    
    @Override
    public void deleteByProblemId(Integer problemId) {
        collectionProblemMapper.deleteByProblemId(problemId);
    }
    
    @Override
    public List<Problem> getProblemsByCollectionId(Long collectionId) {
        return problemMapper.findByCollectionId(collectionId);
    }
    
    @Override
    public boolean isProblemInCollection(Long collectionId, Integer problemId) {
        CollectionProblem cp = collectionProblemMapper.findByCollectionAndProblem(collectionId, problemId);
        return cp != null;
    }
    
    @Override
    public List<Problem> getAllMarkedProblems() {
        return problemMapper.findMarkedProblems();
    }
    
    @Override
    public List<Problem> getMarkedProblemsByTag(Integer tagId) {
        return problemMapper.findMarkedProblemsByTag(tagId);
    }
    
    @Override
    public void exportMarkedProblems(Long collectionId, Integer tagId) {
        // 获取指定标签下的所有错题
        List<Problem> markedProblems = getMarkedProblemsByTag(tagId);
        
        // 将错题添加到指定错题集
        for (Problem problem : markedProblems) {
            addProblemToCollection(collectionId, problem.getId());
        }
    }
    
    @Override
    public void addProblemsToCollection(Long collectionId, List<Integer> problemIds) {
        for (Integer problemId : problemIds) {
            addProblemToCollection(collectionId, problemId);
        }
    }
    
    @Override
    public PageResult<Problem> getProblemsByCollectionIdWithPage(Long collectionId, Integer pageNum, Integer pageSize) {
        // 使用PageHelper进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<Problem> problems = problemMapper.findByCollectionId(collectionId);
        
        // 获取分页信息
        PageInfo<Problem> pageInfo = new PageInfo<>(problems);
        
        // 构建PageResult
        PageResult<Problem> pageResult = new PageResult<>();
        pageResult.setList(problems);
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setPages(pageInfo.getPages());
        
        return pageResult;
    }
}
