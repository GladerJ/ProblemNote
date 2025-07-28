package top.mygld.problemnote.pojo;

import lombok.Data;

@Data
public class CollectionProblem {
    private Long id;
    private Long collectionId;
    private Integer problemId;
    private Boolean marked;
    
    // 关联对象
    private Problem problem;
    private Collection collection;
}