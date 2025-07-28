package top.mygld.problemnote.service;

import top.mygld.problemnote.pojo.CollectionProblem;
import top.mygld.problemnote.pojo.Problem;
import java.util.List;

public interface CollectionProblemService {
    
    // 添加题目到错题集
    void addProblemToCollection(Long collectionId, Integer problemId);
    
    // 从错题集移除题目
    void removeProblemFromCollection(Long collectionId, Integer problemId);
    
    // 获取错题集中的所有题目
    List<Problem> getProblemsByCollectionId(Long collectionId);
    
    // 检查题目是否在错题集中
    boolean isProblemInCollection(Long collectionId, Integer problemId);
    
    // 获取所有错题
    List<Problem> getAllMarkedProblems();
    
    // 根据标签获取错题
    List<Problem> getMarkedProblemsByTag(Integer tagId);
    
    // 导出错题到指定错题集
    void exportMarkedProblems(Long collectionId, Integer tagId);
    
    // 批量添加题目到错题集
    void addProblemsToCollection(Long collectionId, List<Integer> problemIds);
}
