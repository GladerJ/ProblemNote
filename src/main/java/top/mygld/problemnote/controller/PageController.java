package top.mygld.problemnote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.mygld.problemnote.pojo.Problem;
import top.mygld.problemnote.service.ProblemService;

import java.util.List;

@Controller
public class PageController {
    
    @Autowired
    private ProblemService problemService;

    /**
     * 题目收藏页面
     */
    @GetMapping("/problem/collection")
    public String problemCollection(Model model) {
        // 获取收藏的题目列表
        List<Problem> problems = problemService.getFavoriteProblems();
        model.addAttribute("problems", problems);
        return "problem_collection";
    }

    /**
     * 添加题目页面
     */
    @GetMapping("/problem/add")
    public String problemAdd() {
        return "problem_form";
    }

    /**
     * 编辑题目页面
     */
    @GetMapping("/problem/edit/{id}")
    public String problemEdit(Integer id, Model model) {
        Problem problem = problemService.getProblemById(id);
        model.addAttribute("problem", problem);
        return "problem_form";
    }

    /**
     * 查看题目页面
     */
    @GetMapping("/problem/view/{id}")
    public String problemView(Integer id, Model model) {
        Problem problem = problemService.getProblemById(id);
        model.addAttribute("problem", problem);
        return "problem_view";
    }

    /**
     * 搜索题目页面
     */
    @GetMapping("/problem/search")
    public String problemSearch() {
        return "problem_search";
    }
} 