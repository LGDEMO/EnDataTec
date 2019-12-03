package com.endata.springboot.controller;


import com.endata.springboot.bean.ResponseBean;
import com.endata.springboot.model.User;
import com.endata.springboot.service.UserService;
import com.endata.springboot.util.JWTUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-11-08 9:41
 * 用户管理功能
 */
@RestController
@CrossOrigin
public class UserController {
    private static final Logger LOGGER = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins="http://localhost:8080",maxAge = 3600)
    @PostMapping("user/login")
    public  ResponseBean  login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");//对request传过来的参数设置编码格式，以免传入中文的时候出现问题，必须在request.getParameter之前设置
        String userName  = user.getUserName();
        User userList = userService.login(userName);
        if(userList!=null){
            if (userList.getPassword().equals(user.getPassword())) {
                String name= JWTUtil.sign(user.getUserName(), user.getPassword());
                Cookie cookie=new Cookie("name",name);
                cookie.setHttpOnly(true);
                cookie.setPath("/endata");
                response.addCookie(cookie);
                int city_code = userList.getCityCode();
                return new ResponseBean(200, "Login success", city_code);
            } else {
                return   new ResponseBean(404, "password entered is ERROR!!!", userName);
            }
        }else{
           return new ResponseBean(404, "The username and password entered is ERROR!!!", userName);
        }
    }

    @GetMapping("/user/getAllUser")
    public  Map getAllUser(){
        Map<String, List<User>> map = new HashMap<String, List<User>>();
        Map<String,String> resultMap =  new HashMap<String,String>();
        List<User> list = userService.getAllUser();
        if(list.isEmpty()){
            resultMap.put("return_code","404");
            resultMap.put("userData","查询数据为空");
        }else{
            map.put("userData",list);
            return map;
        }
        return map;
    }

    @PostMapping("/user/updateUserPw")
    public  Map  updateUserPw(@RequestBody User user) {
        Map<String,Integer> resultMap =  new HashMap<String,Integer>();
        if(user.getUserName().length()!=0){
            user.setUserName(user.getUserName());
        }
        if(user.getPassword().length()!=0){
            user.setPassword(user.getPassword());
        }if(user.getUserid() == null){
            User   userList = userService.login(user.getUserName());
            user.setUserid( userList.getUserid());
        }
       int number =  userService.Update(user);
        resultMap.put("return_code",200);
        resultMap.put("更新成功！！！",number);
        return resultMap;

    }


    /*支持采纳数的上传*/
    @RequestMapping(value = "/user/uploadFileAction", method = RequestMethod.POST)
    public ModelAndView uploadFileAction(@RequestParam("uploadFile") MultipartFile uploadFile, @RequestParam("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uploadAndDownload");
        InputStream fis = null;
        OutputStream outputStream = null;
        try {
            fis = uploadFile.getInputStream();
            outputStream = new FileOutputStream("G:\\uploadfile\\" + uploadFile.getOriginalFilename());
            IOUtils.copy(fis, outputStream);
            modelAndView.addObject("sucess", "上传成功");
            return modelAndView;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        modelAndView.addObject("sucess", "上传失败!");
        return modelAndView;
    }

    /*支持采纳数的下载*/
    @RequestMapping("downloadFileAction")
    public void downloadFileAction(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream fis = null;
        try {
            File file = new File("G:\\config.ini");
            fis = new FileInputStream(file);
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
