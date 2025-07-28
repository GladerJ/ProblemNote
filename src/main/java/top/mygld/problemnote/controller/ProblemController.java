package top.mygld.problemnote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.mygld.problemnote.pojo.Problem;
import top.mygld.problemnote.pojo.Subject;
import top.mygld.problemnote.pojo.Tag;
import top.mygld.problemnote.service.ProblemService;
import top.mygld.problemnote.common.Result;
import top.mygld.problemnote.service.SubjectService;
import top.mygld.problemnote.service.TagService;
import top.mygld.problemnote.service.CollectionProblemService;
import top.mygld.problemnote.service.CollectionService;
import top.mygld.problemnote.pojo.Collection;

import java.util.List;

@Controller
@RequestMapping("/problem")
public class ProblemController {
    @Autowired
    private ProblemService problemService;
    
    @Autowired
    private TagService tagService;
    
    @Autowired
    private SubjectService subjectService;
    
    @Autowired
    private CollectionProblemService collectionProblemService;
    
    @Autowired
    private CollectionService collectionService;
    
    // 题目列表页面
    @GetMapping("/list")
    public String list(@RequestParam Integer tagId, @RequestParam Integer subjectId, Model model) {
        List<Problem> problems = problemService.getProblemsByTagId(tagId);
        Tag tag = tagService.getTagById(tagId);
        Subject subject = subjectService.getSubjectById(subjectId);
        
        // 确保有默认错题集
        Collection defaultCollection = collectionService.getOrCreateDefaultCollection();
        List<Collection> collections = collectionService.getAllCollections();
        
        model.addAttribute("problems", problems);
        model.addAttribute("tag", tag);
        model.addAttribute("subject", subject);
        model.addAttribute("collections", collections);
        model.addAttribute("defaultCollection", defaultCollection);
        return "problem_list";
    }
    
    // 添加题目页面
    @GetMapping("/manage/add")
    public String addPage(@RequestParam Integer tagId, @RequestParam Integer subjectId, Model model) {
        model.addAttribute("tagId", tagId);
        model.addAttribute("subjectId", subjectId);
        return "problem_form";
    }
    
    // 保存题目
    @PostMapping("/save")
    public String save(Problem problem) {
        if (problem.getId() == null) {
            problemService.addProblem(problem);
        } else {
            problemService.updateProblem(problem);
        }
        return "redirect:/problem/list?tagId=" + problem.getTagId() + "&subjectId=" + problem.getSubjectId();
    }
    
    // 编辑题目
    @GetMapping("/manage/edit/{id}")
    public String editPage(@PathVariable Integer id, @RequestParam Integer tagId, @RequestParam Integer subjectId, Model model) {
        Problem problem = problemService.getProblemById(id);
        model.addAttribute("problem", problem);
        model.addAttribute("tagId", tagId);
        model.addAttribute("subjectId", subjectId);
        return "problem_form";
    }
    
    // 删除题目
    @GetMapping("/manage/delete/{id}")
    public String delete(@PathVariable Integer id, @RequestParam Integer tagId, @RequestParam Integer subjectId) {
        problemService.deleteProblem(id);
        return "redirect:/problem/list?tagId=" + tagId + "&subjectId=" + subjectId;
    }
    
    // 导出标记的错题
    @PostMapping("/export-marked")
    @ResponseBody
    public String exportMarkedProblems(@RequestBody ExportRequest request) {
        try {
            System.out.println("导出标记的错题: collectionId=" + request.getCollectionId() + ", problemIds=" + request.getProblemIds());
            collectionProblemService.addProblemsToCollection(request.getCollectionId(), request.getProblemIds());
            return "success";
        } catch (Exception e) {
            System.err.println("导出错题失败: " + e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }
    
    // 请求体类
    public static class ExportRequest {
        private Long collectionId;
        private List<Integer> problemIds;
        
        public Long getCollectionId() {
            return collectionId;
        }
        
        public void setCollectionId(Long collectionId) {
            this.collectionId = collectionId;
        }
        
        public List<Integer> getProblemIds() {
            return problemIds;
        }
        
        public void setProblemIds(List<Integer> problemIds) {
            this.problemIds = problemIds;
        }
    }
}