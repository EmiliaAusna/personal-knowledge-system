package com.knowledge.dao;
import com.knowledge.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
public interface UserDao {
    @Select("select * from `user` where username=#{username} and password=#{password}")
    User login(@Param("username") String username, @Param("password") String password);

    @Insert("insert into `user`(username,password,create_time) values(#{username},#{password},now())")
    int register(User user);
}
