package com.ligang.springboot.service.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ligang.springboot.mapper.UserMapper;
import com.ligang.springboot.model.User;
import com.ligang.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public int Update() {
        User user  = new  User();
        user.setUserid(1);
        user.setUserName("entreated");
        user.setPassword("123");
       int number =   userMapper.updateByPrimaryKeySelective(user);
        System.out.println("更新的结果："+number);

     /*除数不能为零，所以会抛出一个运行时的异常，有异常更新就会回滚*/
     /*   int a  = 10 / 0;*/
        return  number;

    }
}
