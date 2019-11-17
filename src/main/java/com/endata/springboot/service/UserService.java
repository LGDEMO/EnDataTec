package com.endata.springboot.service;

import com.endata.springboot.model.User;

import java.util.List;


/**
 * @author ligang
 * @create 2019-11-08 9:44
 */

public interface UserService {
     public List<User> getAllUser();
     public  int  Update();
     User login(String userName);
}
