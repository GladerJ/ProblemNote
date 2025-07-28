package top.mygld.problemnote.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mygld.problemnote.pojo.Problem;
import top.mygld.problemnote.pojo.Subject;
import top.mygld.problemnote.pojo.Tag;
import top.mygld.problemnote.service.CollectionProblemService;
import top.mygld.problemnote.service.ProblemService;
import top.mygld.problemnote.service.SubjectService;
import top.mygld.problemnote.mapper.SubjectMapper;
import top.mygld.problemnote.common.PageResult;
import top.mygld.problemnote.service.TagService;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private CollectionProblemService collectionProblemService;

    @Override
    public Subject getSubjectById(Integer id) {
        return subjectMapper.findById(id);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectMapper.findAll();
    }

    @Override
    public PageResult<Subject> getSubjectsByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Subject> subjects = subjectMapper.findAll();
        PageInfo<Subject> pageInfo = new PageInfo<>(subjects);
        return new PageResult<>(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPageNum(), pageInfo.getPageSize());
    }

    @Override
    public int addSubject(Subject subject) {
        return subjectMapper.insert(subject);
    }

    @Override
    public int updateSubject(Subject subject) {
        return subjectMapper.update(subject);
    }

    @Override
    public int deleteSubject(Integer id) {
        // 获取该科目下所有知识点
        List<Tag> tags = tagService.getTagsBySubjectId(id);
        for (Tag tag : tags) {
            // 获取知识点下所有题目
            List<Problem> problems = problemService.getProblemsByTagId(tag.getId());
            for (Problem problem : problems) {
                // 删除题目与错题集的关联
                collectionProblemService.deleteByProblemId(problem.getId());
                // 删除题目
                problemService.deleteProblem(problem.getId());
            }
            // 删除知识点
            tagService.deleteTag(tag.getId());
        }
        // 最后删除科目
        return subjectMapper.delete(id);
    }

    @Override
    public Subject getSubjectByName(String name) {
        return subjectMapper.findByName(name);
    }
}