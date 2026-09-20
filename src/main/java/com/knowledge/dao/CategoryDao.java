package com.knowledge.dao;
import com.knowledge.entity.Category;
import org.apache.ibatis.annotations.Select;
import java.util.List;
public interface CategoryDao {
    @Select("select * from category")
    List<Category> findAll();
}
