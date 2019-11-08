package com.ligang.springboot.service.impl;

import com.ligang.springboot.mapper.UserMapper;
import com.ligang.springboot.model.User;
import com.ligang.springboot.service.UserService;
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
}
