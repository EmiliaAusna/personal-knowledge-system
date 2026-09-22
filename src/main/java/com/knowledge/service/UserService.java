package com.knowledge.service;
import com.knowledge.dao.UserDao;
import com.knowledge.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public User login(String username,String password){
        return userDao.login(username,password);
    }
    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }
    public boolean register(User user){
        return userDao.register(user) > 0;
    }
}
