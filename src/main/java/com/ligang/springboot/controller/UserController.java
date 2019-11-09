package com.ligang.springboot.controller;


import com.ligang.springboot.model.User;
import com.ligang.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-11-08 9:41
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("boot/login")
   public User  login(@RequestParam(value="userName") String userName,@RequestParam(value="password") String password){
        User resultUser = (User) userService.login(userName,password);
        User user = new  User();
       if(resultUser == null){
       /*    request.setAttribute("user",user);
           request.setAttribute("errorMsg","用户名或则密码错误");*/
           return user;
       }else{
         /*  HttpSession session =  request.getSession();
           session.setAttribute("currentUser",resultUser);*/
           return user;

       }

   }
    @GetMapping("/boot/user")
    public  Object user(){
        return userService.getAllUser();
    }

    @GetMapping("/boot/update")
    public  Object update() {
        return userService.Update();
    }

    @GetMapping("/boot/delete")
    public  Object delete() {
        return userService.Update();
    }
}
