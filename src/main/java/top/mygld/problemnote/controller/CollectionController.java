package top.mygld.problemnote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.mygld.problemnote.pojo.Collection;
import top.mygld.problemnote.pojo.Problem;
import top.mygld.problemnote.service.CollectionProblemService;
import top.mygld.problemnote.service.CollectionService;
import top.mygld.problemnote.common.PageResult;

import java.util.List;

@Controller
@RequestMapping("/collection")
public class CollectionController {
    
    @Autowired
    private CollectionService collectionService;
    
    @Autowired
    private CollectionProblemService collectionProblemService;
    
    // 错题集列表页面
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") Integer pageNum,
                      @RequestParam(defaultValue = "10") Integer pageSize,
                      Model model) {
        PageResult<Collection> pageResult = collectionService.getAllCollectionsWithPage(pageNum, pageSize);
        model.addAttribute("collections", pageResult.getList());
        model.addAttribute("pageResult", pageResult);
        return "collection/list";
    }
    
    // 错题集详情页面
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, 
                      @RequestParam(defaultValue = "1") Integer pageNum,
                      @RequestParam(defaultValue = "10") Integer pageSize,
                      Model model) {
        Collection collection = collectionService.getCollectionById(id);
        PageResult<Problem> pageResult = collectionProblemService.getProblemsByCollectionIdWithPage(id, pageNum, pageSize);
        
        model.addAttribute("collection", collection);
        model.addAttribute("problems", pageResult.getList());
        model.addAttribute("pageResult", pageResult);
        return "collection/view";
    }
    
    // 添加错题集页面
    @GetMapping("/add")
    public String addPage(@RequestParam(defaultValue = "1") Integer pageNum,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         Model model) {
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        return "collection/form";
    }
    
    // 保存错题集
    @PostMapping("/save")
    public String save(Collection collection,
                      @RequestParam(defaultValue = "1") Integer pageNum,
                      @RequestParam(defaultValue = "10") Integer pageSize) {
        if (collection.getId() == null) {
            collectionService.addCollection(collection);
        } else {
            collectionService.updateCollection(collection);
        }
        return "redirect:/collection/list?pageNum=" + pageNum + "&pageSize=" + pageSize;
    }
    
    // 编辑错题集页面
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id,
                          @RequestParam(defaultValue = "1") Integer pageNum,
                          @RequestParam(defaultValue = "10") Integer pageSize,
                          Model model) {
        Collection collection = collectionService.getCollectionById(id);
        model.addAttribute("collection", collection);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("pageSize", pageSize);
        return "collection/form";
    }
    
    // 删除错题集
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                        @RequestParam(defaultValue = "1") Integer pageNum,
                        @RequestParam(defaultValue = "10") Integer pageSize) {
        collectionService.deleteCollection(id);
        return "redirect:/collection/list?pageNum=" + pageNum + "&pageSize=" + pageSize;
    }
    
    // 添加题目到错题集
    @PostMapping("/add-problem")
    @ResponseBody
    public String addProblem(@RequestParam Long collectionId, @RequestParam Integer problemId) {
        try {
            collectionProblemService.addProblemToCollection(collectionId, problemId);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
    
    // 从错题集移除题目
    @PostMapping("/remove-problem")
    @ResponseBody
    public String removeProblem(@RequestParam Long collectionId, @RequestParam Integer problemId) {
        try {
            collectionProblemService.removeProblemFromCollection(collectionId, problemId);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
    
    // 导出错题
    @PostMapping("/export")
    @ResponseBody
    public String exportProblems(@RequestParam Long collectionId, @RequestParam Integer tagId) {
        try {
            collectionProblemService.exportMarkedProblems(collectionId, tagId);
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }
}
