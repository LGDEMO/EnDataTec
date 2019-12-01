package com.endata.springboot.controller;

import com.endata.springboot.model.Envir;
import com.endata.springboot.service.EnvirService;
import com.endata.springboot.util.NewDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-12-01 15:11
 */
@RestController
@CrossOrigin
public class EnvirController {

    @Autowired
    private EnvirService envirService;

     /*# 环境监测点位获取(管理员)*/
    @PostMapping("/envir/getEnvirData")
    public Map  getEnvirData(@RequestParam("cityCode")  Integer cityCode){
        Map resultMap  = new HashMap<>();
        if(cityCode !=null) {
            List<Envir> envirList = envirService.getEnvirData(cityCode);
            if (envirList != null) {
                resultMap.put("return_code",200);
                resultMap.put("return_data",envirList);
            }else{
                resultMap.put("return_code",404);
                resultMap.put("message","cityCode错误！！！");
            }
        }else{
            resultMap.put("return_code",404);
            resultMap.put("message","cityCode为空！！！");
        }
        return resultMap;
    }

  /*  # 环境监测点位删除(管理员)*/
    @PostMapping("/envir/deleteEnvirData")
    public Map deleteEnvirData(@RequestParam("cityCode") Integer cityCode,@RequestParam("id") Integer id){
        Map resultMap  = new HashMap<>();
        if(id !=null) {
            int number = envirService.deleteByPrimaryKey(id);
            resultMap.put("return_code", 200);
            resultMap.put("message", "删除了" + number + "条数据");
        }else{
            resultMap.put("return_code", 404);
            resultMap.put("message","id为空！！！");
        }
        return  resultMap;
    }


    /*# 环境监测点位添加(管理员)*/
    @PostMapping("/envir/addEviorData")
    public  Map addEviorData(@RequestBody Envir envir) throws ParseException {
        Map resultMap  = new HashMap<>();
        Integer cityCode  = envir.getCityCode();
        if(cityCode!=null){
            envir.setCityCode(cityCode);
        }
        String cityName  = envir.getCityName();
        if(cityName != null){
            envir.setCityName(cityName);
        }
        String dotName = envir.getDotName();
        if(dotName !=null){
            envir.setDotName(dotName);
        }
        Float so2  =  envir.getSo2();
        if(so2 !=null){
            envir.setSo2(so2);
        }
        Float no2  = envir.getNo2();
        if(no2 !=null){
            envir.setNo2(no2);
        }
        Float co = envir.getCo();
        if(co !=null){
            envir.setCo(co);
        }
        Float pm25 = envir.getPm25();
        if(pm25 !=null){
            envir.setPm25(pm25);
        }
        Float o3  =  envir.getO3();
        if(o3 !=null){
            envir.setO3(o3);
        }
        Date date = envir.getDate();
        if(date !=null){
            envir.setDate(date);
        }else{
            NewDate newDate  = new NewDate();
            envir.setDate(newDate.getNewDate());
        }
         int number  =  envirService.insertSelective(envir);
        resultMap.put("return_code", 200);
        resultMap.put("message", "增加了" + number + "条数据");
        return  resultMap;
    }

   /* # 环境监测点位图获取(所有用户)*/
    @GetMapping("/envir/getEnvirMapData")
    public Map  getEnvirMapData(){
        Map resultMap  = new HashMap<>();


        return resultMap;

  }



}
