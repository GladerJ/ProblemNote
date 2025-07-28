package top.mygld.problemnote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.mygld.problemnote.pojo.Subject;
import top.mygld.problemnote.service.SubjectService;
import top.mygld.problemnote.common.PageResult;

@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    // 科目列表
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int pageNum, Model model) {
        int pageSize = 10; // 每页10条记录
        PageResult<Subject> pageResult = subjectService.getSubjectsByPage(pageNum, pageSize);
        model.addAttribute("subjects", pageResult.getList());
        model.addAttribute("pageResult", pageResult);
        return "subject_list";
    }

    // 新增页面
    @GetMapping("/add")
    public String addPage() {
        return "subject_form";
    }

    // 新增处理
    @PostMapping("/add")
    public String add(@RequestParam String name) {
        Subject subject = new Subject();
        subject.setName(name);
        subjectService.addSubject(subject);
        return "redirect:/subject/list";
    }

    // 编辑页面
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Integer id, Model model) {
        model.addAttribute("subject", subjectService.getSubjectById(id));
        return "subject_form";
    }

    // 编辑处理
    @PostMapping("/edit")
    public String edit(@RequestParam Integer id, @RequestParam String name) {
        Subject subject = new Subject();
        subject.setId(id);
        subject.setName(name);
        subjectService.updateSubject(subject);
        return "redirect:/subject/list";
    }

    // 删除
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        subjectService.deleteSubject(id);
        return "redirect:/subject/list";
    }
} 