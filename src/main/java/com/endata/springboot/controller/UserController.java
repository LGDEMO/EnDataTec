package com.endata.springboot.controller;


import com.endata.springboot.bean.ResponseBean;
import com.endata.springboot.model.User;
import com.endata.springboot.service.UserService;
import com.endata.springboot.util.JWTUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author ligang
 * @create 2019-11-08 9:41
 * 用户管理功能
 */
@RestController
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("user/login")
    public ResponseBean     login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        String userName  = user.getUserName();
        User   userList = userService.login(userName);
        request.setCharacterEncoding("utf-8");//对request传过来的参数设置编码格式，以免传入中文的时候出现问题，必须在request.getParameter之前设置
        String name= JWTUtil.sign(user.getUserName(), user.getPassword());
        Cookie cookie=new Cookie("name",name);
        response.addCookie(cookie);
        if (userList.getPassword().equals(user.getPassword())) {
            return new ResponseBean(200, "Login success", name);
        } else {
            throw new UnauthorizedException();
        }
    }



/*    @GetMapping("/user/getAllUser")
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
    }*/
}
