package top.mygld.problemnote.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mygld.problemnote.pojo.Subject;
import top.mygld.problemnote.service.SubjectService;
import top.mygld.problemnote.mapper.SubjectMapper;
import top.mygld.problemnote.common.PageResult;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectMapper subjectMapper;

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
        return subjectMapper.delete(id);
    }

    @Override
    public Subject getSubjectByName(String name) {
        return subjectMapper.findByName(name);
    }
} 