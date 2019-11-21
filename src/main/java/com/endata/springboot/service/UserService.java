package com.endata.springboot.service;

import com.endata.springboot.model.User;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author ligang
 * @create 2019-11-08 9:44
 */
@Component
public interface UserService {
     public List<User> getAllUser();
     public  int  Update(User user);
     User login(String userName);
     String getCityName(int city_code);
}
