package com.ligang.springboot.service.impl;

import com.ligang.springboot.mapper.UserMapper;
import com.ligang.springboot.model.User;
import com.ligang.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-11-08 9:49
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public int Update() {
        User user  = new  User();
        user.setUserid(3);
        user.setUserName("zyc");
        user.setPassword("123456");
        int number =   userMapper.updateByPrimaryKeySelective(user);
        System.out.println("更新的结果："+number);
        return  number;

    }

    @Override
    public User login(User  user) {
        User  list =  userMapper.login(user);
        return list;
    }
}
