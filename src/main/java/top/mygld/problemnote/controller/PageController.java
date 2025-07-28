package top.mygld.problemnote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.mygld.problemnote.pojo.Problem;
import top.mygld.problemnote.service.ProblemService;
import top.mygld.problemnote.common.PageResult;

import java.util.List;

@Controller
public class PageController {
    
    @Autowired
    private ProblemService problemService;

    /**
     * 题目标记页面
     */
    @GetMapping("/problem/collection")
    public String problemCollection(@RequestParam(defaultValue = "1") int pageNum, Model model) {
        try {
            // 获取标记的题目列表（分页）
            int pageSize = 10; // 每页10条记录
            PageResult<Problem> pageResult = problemService.getFavoriteProblemsByPage(pageNum, pageSize);
            model.addAttribute("problems", pageResult.getList());
            model.addAttribute("pageResult", pageResult);
        } catch (Exception e) {
            // 如果出现异常，返回空列表
            model.addAttribute("problems", new java.util.ArrayList<>());
            model.addAttribute("pageResult", new PageResult<>(new java.util.ArrayList<>(), 0, pageNum, 10));
        }
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
        try {
            Problem problem = problemService.getProblemById(id);
            model.addAttribute("problem", problem);
        } catch (Exception e) {
            // 如果出现异常，返回空对象
            model.addAttribute("problem", new Problem());
        }
        return "problem_form";
    }

    /**
     * 查看题目页面
     */
    @GetMapping("/problem/view/{id}")
    public String problemView(Integer id, Model model) {
        try {
            Problem problem = problemService.getProblemById(id);
            model.addAttribute("problem", problem);
        } catch (Exception e) {
            // 如果出现异常，返回空对象
            model.addAttribute("problem", new Problem());
        }
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