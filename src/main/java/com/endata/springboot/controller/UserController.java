package com.endata.springboot.controller;


import com.endata.springboot.model.User;
import com.endata.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Retention;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-11-08 9:41
 * 用户管理功能
 */
@Controller
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("user/login")
   public Map  login( @RequestBody User user){
        Map<String,Object> resultMap = new HashMap<String,Object>();
        String    userName =  user.getUserName();
        String    password  =user.getPassword();
        if(userName == null || userName.equals(" ")){
            resultMap.put("errorMsg","用户名不能为空！！！");//用户名不能为空
            return resultMap;
         }else{
            user.setUserName(userName);
        }
        if(password == null || password.equals(" ")){//密码不能为空
            resultMap.put("errorMsg","密码不能为空！！！");
            return resultMap;
        }else{
            user.setPassword(password);
        }
        User resultUser = (User) userService.login(user);//查询数据库对应的用户名和密码
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
    @GetMapping("/user/getAllUser")
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
