package com.knowledge.service;
import com.knowledge.dao.CategoryDao;
import com.knowledge.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;
    public List<Category> findAll(){
        return categoryDao.findAll();
    }
}
