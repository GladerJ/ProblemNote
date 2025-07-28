package com.example.problemnote.pojo;

import lombok.Data;
import java.util.Date;

@Data
public class Collection {
    private Long id;
    private String name;
    private String description;
    private Date createTime;
    private Date updateTime;
    private Long userId;
}