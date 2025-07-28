package top.mygld.problemnote.service;

import top.mygld.problemnote.pojo.Subject;
import top.mygld.problemnote.common.PageResult;
import java.util.List;

public interface SubjectService {
    Subject getSubjectById(Integer id);
    List<Subject> getAllSubjects();
    PageResult<Subject> getSubjectsByPage(int pageNum, int pageSize);
    int addSubject(Subject subject);
    int updateSubject(Subject subject);
    int deleteSubject(Integer id);
    Subject getSubjectByName(String name);
} 