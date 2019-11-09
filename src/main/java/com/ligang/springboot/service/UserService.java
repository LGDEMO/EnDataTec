package com.ligang.springboot.service;

import com.ligang.springboot.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-11-08 9:44
 */

public interface UserService {
    public List<User>  getAllUser();
    public  int  Update();
    public User login(@Param("userName") String userName, @Param("password") String  password);
}
