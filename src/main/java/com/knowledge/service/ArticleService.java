package com.knowledge.service;
import com.knowledge.dao.ArticleDao;
import com.knowledge.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    public List<Article> findAll(){
        return articleDao.findAll();
    }
    public List<Article> findByCategoryId(Integer categoryId){
        return articleDao.findByCategoryId(categoryId);
    }
    public Article findById(Integer articleId){
        return articleDao.findById(articleId);
    }
    public List<java.util.Map<String,Object>> countByCategory(){
        return articleDao.countByCategory();
    }
    public boolean add(Article article){
        return articleDao.add(article) > 0;
    }
    public boolean update(Article article){
        return articleDao.update(article) > 0;
    }
    public boolean delete(Integer articleId){
        return articleDao.delete(articleId) > 0;
    }
}
