package com.endata.springboot.util;
import com.endata.springboot.service.UserService;
import com.endata.springboot.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @author ligang
 * @create 2019-11-20 19:27
 * 根据城市代码在用户表中查找出对应的中文名字
 */
@Component
public class NameByCode {
    @Autowired
    private UserService userService;

    public  String getCityName(int city_code){
            return userService.getCityName(city_code);
        }
    }


