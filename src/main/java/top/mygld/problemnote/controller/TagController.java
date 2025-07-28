package top.mygld.problemnote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.mygld.problemnote.pojo.Tag;
import top.mygld.problemnote.pojo.Subject;
import top.mygld.problemnote.service.TagService;
import top.mygld.problemnote.service.SubjectService;
import top.mygld.problemnote.common.PageResult;

@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private SubjectService subjectService;

    // 知识点列表
    @GetMapping("/list")
    public String list(@RequestParam Integer subjectId,
                       @RequestParam(defaultValue = "1") int pageNum,
                       Model model) {
        int pageSize = 10; // 每页10条记录

        // 获取科目信息
        Subject subject = subjectService.getSubjectById(subjectId);
        model.addAttribute("subject", subject);

        // 获取分页数据
        PageResult<Tag> pageResult = tagService.getTagsBySubjectIdWithPage(subjectId, pageNum, pageSize);
        model.addAttribute("tags", pageResult.getList());
        model.addAttribute("pageResult", pageResult);

        return "tag_list";
    }

    // 新增页面
    @GetMapping("/add")
    public String addPage(@RequestParam Integer subjectId, Model model) {
        Subject subject = subjectService.getSubjectById(subjectId);
        model.addAttribute("subject", subject);
        return "tag_form";
    }

    // 新增处理
    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam Integer subjectId) {
        Tag tag = new Tag();
        tag.setName(name);
        tag.setSubjectId(subjectId);
        tagService.addTag(tag);
        return "redirect:/tag/list?subjectId=" + subjectId;
    }

    // 编辑页面
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, @RequestParam Integer subjectId, Model model) {
        Tag tag = tagService.getTagById(id);
        Subject subject = subjectService.getSubjectById(subjectId);
        model.addAttribute("tag", tag);
        model.addAttribute("subject", subject);
        return "tag_form";
    }

    // 编辑处理
    @PostMapping("/edit")
    public String edit(@RequestParam Integer id,
                       @RequestParam String name,
                       @RequestParam Integer subjectId) {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);
        tag.setSubjectId(subjectId);
        tagService.updateTag(tag);
        return "redirect:/tag/list?subjectId=" + subjectId;
    }

    // 删除
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, @RequestParam Integer subjectId) {
        tagService.deleteTag(id);
        return "redirect:/tag/list?subjectId=" + subjectId;
    }
}