package top.mygld.problemnote.service;

import top.mygld.problemnote.pojo.Problem;
import top.mygld.problemnote.common.PageResult;

import java.util.List;

public interface ProblemService {
    Problem getProblemById(Integer id);

    List<Problem> getProblemsBySubjectAndTag(Integer subjectId, Integer tagId);

    List<Problem> getProblemsByTagId(Integer tagId); // 添加此方法

    int addProblem(Problem problem);

    int updateProblem(Problem problem);

    int deleteProblem(Integer id);


    List<Problem> getFavoriteProblems();

    PageResult<Problem> getFavoriteProblemsByPage(int pageNum, int pageSize);

}