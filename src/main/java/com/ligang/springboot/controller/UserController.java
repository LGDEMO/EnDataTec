package com.ligang.springboot.controller;


import com.ligang.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ligang
 * @create 2019-11-08 9:41
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/boot/user")
    public  Object user(){
        return userService.getAllUser();
    }
}
