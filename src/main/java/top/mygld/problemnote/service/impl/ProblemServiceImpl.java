package top.mygld.problemnote.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mygld.problemnote.pojo.Problem;
import top.mygld.problemnote.service.ProblemService;
import top.mygld.problemnote.mapper.ProblemMapper;
import top.mygld.problemnote.common.PageResult;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemMapper problemMapper;
    
    @Override
    public Problem getProblemById(Integer id) {
        return problemMapper.findById(id);
    }
    
    @Override
    public List<Problem> getProblemsBySubjectAndTag(Integer subjectId, Integer tagId) {
        return problemMapper.findBySubjectAndTag(subjectId, tagId);
    }
    
    @Override
    public List<Problem> getProblemsByTagId(Integer tagId) { // 实现此方法
        return problemMapper.findByTagId(tagId);
    }
    
    @Override
    public PageResult<Problem> getProblemsByTagIdWithPage(Integer tagId, Integer pageNum, Integer pageSize) {
        // 使用PageHelper进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<Problem> problems = problemMapper.findByTagId(tagId);
        
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

    @Override
    public List<Problem> getFavoriteProblems() {
        // 临时返回空列表，避免数据库连接问题
        return new java.util.ArrayList<>();
    }

    @Override
    public PageResult<Problem> getFavoriteProblemsByPage(int pageNum, int pageSize) {
        // 临时返回空分页结果，避免数据库连接问题
        return new PageResult<>(new java.util.ArrayList<>(), 0, pageNum, pageSize);
    }

    @Override
    public int addProblem(Problem problem) {
        return problemMapper.insert(problem);
    }

    @Override
    public int updateProblem(Problem problem) {
        return problemMapper.update(problem);
    }

    @Override
    public int deleteProblem(Integer id) {
        return problemMapper.delete(id);
    }
}