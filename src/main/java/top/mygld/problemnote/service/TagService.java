package top.mygld.problemnote.service;

import top.mygld.problemnote.pojo.Tag;
import top.mygld.problemnote.common.PageResult;
import java.util.List;

public interface TagService {
    Tag getTagById(Integer id);
    List<Tag> getTagsBySubjectId(Integer subjectId);
    PageResult<Tag> getTagsBySubjectIdWithPage(Integer subjectId, int pageNum, int pageSize);
    int addTag(Tag tag);
    int updateTag(Tag tag);
    int deleteTag(Integer id);
}