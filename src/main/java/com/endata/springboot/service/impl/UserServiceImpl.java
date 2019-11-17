package com.endata.springboot.service.impl;

import com.endata.springboot.mapper.UserMapper;
import com.endata.springboot.model.User;
import com.endata.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public int Update(User user ) {
        int number =   userMapper.updateByPrimaryKeySelective(user);
        System.out.println("更新的结果："+number);
        return  number;
    }

    @Override
    public User login(String userName) {
        User  list =  userMapper.login(userName);
        return list;
    }
}
