package com.example.problemnote.controller;

import top.mygld.problemnote.pojo.Collection;
import top.mygld.problemnote.pojo.Problem;
import top.mygld.problemnote.pojo.User;
import top.mygld.problemnote.service.CollectionProblemService;
import top.mygld.problemnote.service.CollectionService;
import top.mygld.problemnote.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CollectionProblemService collectionProblemService;

    @Autowired
    private ProblemService problemService;

    // 错题集列表
    @GetMapping("/list")
    public String list(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Collection> collections = collectionService.getUserCollections(user.getId());
        model.addAttribute("collections", collections);
        return "collection/list";
    }

    // 错题集题目列表
    @GetMapping("/problem/list/{collectionId}")
    public String problemList(@PathVariable Long collectionId, Model model) {
        Collection collection = collectionService.getCollectionById(collectionId);
        List<Long> problemIds = collectionProblemService.getProblemIdsByCollectionId(collectionId);
        List<Problem> problems = problemIds.stream()
                .map(problemService::getProblemById)
                .collect(Collectors.toList());

        model.addAttribute("collection", collection);
        model.addAttribute("problems", problems);
        return "problem/collection_problem_list";
    }

    // 添加题目到错题集
    @PostMapping("/problem/add")
    @ResponseBody
    public String addProblemToCollection(Long collectionId, Long problemId) {
        int result = collectionProblemService.addToCollection(collectionId, problemId);
        return result > 0 ? "success" : "already exists";
    }

    // 从错题集移除题目
    @PostMapping("/problem/remove")
    @ResponseBody
    public String removeProblemFromCollection(Long collectionId, Long problemId) {
        int result = collectionProblemService.removeFromCollection(collectionId, problemId);
        return result > 0 ? "success" : "error";
    }

    // 导出错题
    @PostMapping("/export")
    public String exportProblems(String collectionName, @RequestParam List<Long> problemIds, HttpSession session) {
        User user = (User) session.getAttribute("user");
        collectionProblemService.createCollectionWithProblems(collectionName, problemIds, user.getId());
        return "redirect:/collection/list";
    }

    // 创建错题集页面
    @GetMapping("/form")
    public String form() {
        return "collection/form";
    }

    // 保存错题集
    @PostMapping("/save")
    public String save(Collection collection, HttpSession session) {
        User user = (User) session.getAttribute("user");
        collection.setUserId(user.getId());
        collectionService.createCollection(collection);
        return "redirect:/collection/list";
    }

    // 删除错题集
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        collectionService.deleteCollection(id);
        return "redirect:/collection/list";
    }
}