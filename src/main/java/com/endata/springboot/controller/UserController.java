package com.endata.springboot.controller;


import com.endata.springboot.model.User;
import com.endata.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-11-08 9:41
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("user/login")
   public Map  login(@RequestParam(value="userName") String userName,@RequestParam(value="password") String password){
        User user = new  User();
        Map<String,Object> resultMap = new HashMap<String,Object>();
        user.setUserName(userName);
        user.setPassword(password);
        User resultUser = (User) userService.login(user);
       if(resultUser == null){
           resultMap.put("resultUser",user);
           resultMap.put("errorMsg","用户名或则密码错误");
           return resultMap;
       }else{
           resultMap.put("resultUser",resultUser);
           resultMap.put("success","登录成功");
           return resultMap;
       }
   }
    @GetMapping("/user/Alluser")
    public  Object user(){
        return userService.getAllUser();
    }

    @GetMapping("/user/update")
    public  Object update() {
        return userService.Update();
    }

    @GetMapping("/user/delete")
    public  Object delete() {
        return userService.Update();
    }
}
