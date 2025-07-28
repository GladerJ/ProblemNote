package top.mygld.problemnote.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mygld.problemnote.mapper.TagMapper;
import top.mygld.problemnote.pojo.Tag;
import top.mygld.problemnote.service.TagService;
import top.mygld.problemnote.common.PageResult;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public Tag getTagById(Integer id) {
        return tagMapper.findById(id);
    }

    @Override
    public List<Tag> getTagsBySubjectId(Integer subjectId) {
        return tagMapper.findBySubjectId(subjectId);
    }

    @Override
    public PageResult<Tag> getTagsBySubjectIdWithPage(Integer subjectId, int pageNum, int pageSize) {
        // 使用PageHelper进行分页
        PageHelper.startPage(pageNum, pageSize);
        List<Tag> tags = tagMapper.findBySubjectId(subjectId);
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);

        // 构建分页结果
        PageResult<Tag> pageResult = new PageResult<>();
        pageResult.setList(pageInfo.getList());
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setPages(pageInfo.getPages());
        pageResult.setHasPreviousPage(pageInfo.isHasPreviousPage());
        pageResult.setHasNextPage(pageInfo.isHasNextPage());

        return pageResult;
    }

    @Override
    public int addTag(Tag tag) {
        return tagMapper.insert(tag);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagMapper.update(tag);
    }

    @Override
    public int deleteTag(Integer id) {
        return tagMapper.delete(id);
    }
}