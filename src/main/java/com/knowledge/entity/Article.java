package com.knowledge.entity;
import lombok.Data;
import java.util.Date;
@Data
public class Article {
    private Integer articleId;
    private String title;
    private String content;
    private Date createTime;
    private Date updateTime;
    private Integer userId;
    private Integer categoryId;
    private String username;
    private String categoryName;
}
