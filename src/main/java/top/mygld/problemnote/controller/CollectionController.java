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
import java.util.Map;

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

        // 🔧 修复：获取所有错题集用于导出功能的下拉列表
        List<Collection> allCollections = collectionService.getAllCollections();

        model.addAttribute("collection", collection);
        model.addAttribute("problems", pageResult.getList());
        model.addAttribute("pageResult", pageResult);
        // 🔧 修复：添加所有错题集到模型中
        model.addAttribute("collections", allCollections);

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

    // 🆕 新增：获取错题集列表的API接口（可选，用于前端动态加载）
    @GetMapping("/api/list")
    @ResponseBody
    public List<Collection> getCollectionsList() {
        return collectionService.getAllCollections();
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

    // 新建错题集并导出
    @PostMapping("/create-and-export")
    @ResponseBody
    public String createCollectionAndExport(@RequestBody Map<String, Object> requestData) {
        try {
            String collectionName = (String) requestData.get("name");
            List<String> problemIds = (List<String>) requestData.get("problemIds");

            // 创建新错题集
            Collection collection = new Collection();
            collection.setName(collectionName);
            collectionService.addCollection(collection);

            // 获取新创建的错题集ID
            Long collectionId = collection.getId();

            // 将题目添加到错题集
            for (String problemIdStr : problemIds) {
                Integer problemId = Integer.valueOf(problemIdStr);
                collectionProblemService.addProblemToCollection(collectionId, problemId);
            }

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}