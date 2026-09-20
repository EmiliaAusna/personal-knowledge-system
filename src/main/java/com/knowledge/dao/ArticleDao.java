package com.knowledge.dao;
import com.knowledge.entity.Article;
import org.apache.ibatis.annotations.*;
import java.util.List;
public interface ArticleDao {
    @Select("select a.*,u.username,c.category_name from article a left join `user` u on a.user_id=u.user_id left join category c on a.category_id=c.category_id order by a.create_time desc")
    List<Article> findAll();

    @Select("select a.*,u.username,c.category_name from article a left join `user` u on a.user_id=u.user_id left join category c on a.category_id=c.category_id where a.category_id=#{categoryId} order by a.create_time desc")
    List<Article> findByCategoryId(@Param("categoryId") Integer categoryId);

    @Select("select a.*,u.username,c.category_name from article a left join `user` u on a.user_id=u.user_id left join category c on a.category_id=c.category_id where a.article_id=#{articleId}")
    Article findById(@Param("articleId") Integer articleId);

    @Insert("insert into article(title,content,create_time,update_time,user_id,category_id) values(#{title},#{content},now(),now(),#{userId},#{categoryId})")
    int add(Article article);

    @Update("update article set title=#{title},content=#{content},update_time=now(),category_id=#{categoryId} where article_id=#{articleId}")
    int update(Article article);

    @Delete("delete from article where article_id=#{articleId}")
    int delete(@Param("articleId") Integer articleId);
}
