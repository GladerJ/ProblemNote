package com.example.problemnote.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class CollectionProblem {
    private Long id;
    private Long collectionId;
    private Long problemId;
    private Date addTime;
}