package com.endata.springboot.controller;

import com.endata.springboot.model.Air;
import com.endata.springboot.model.Soil;
import com.endata.springboot.model.Water;
import com.endata.springboot.service.AirService;
import com.endata.springboot.service.SoilService;
import com.endata.springboot.service.UserService;
import com.endata.springboot.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.util.*;

import java.util.stream.Collectors;

/**
 * @author ligang
 * @create 2019-11-18 9:16
 * 主界面各种图的数据展示
 */
@RestController
@CrossOrigin
public class FigureController {
    @Autowired
    private WaterService waterService;

    @Autowired
   private   SoilService soilService;

    @Autowired
    private AirService airService;

    @Autowired
    private UserService userService;

     /*# 获取柱状图信息*/
    @GetMapping("/figure/getHistogram")
     public Map getHistogram(){
        Map<String, Object> resultMap  = new HashMap<String, Object>();
        Map<String,Float> map  = new HashMap<String,Float>();

        /*水*/
         Water waterList  =  waterService.getNewWaterData();
         Float AddoralWater = waterList.getAddoralWater();
         Float adddermalWater  = waterList.getAdddermalWater();
         map.put("AddoralWater", AddoralWater);
         map.put("adddermalWater", adddermalWater);

        /*土壤*/
         Soil soilList  = soilService.getNewSoilData();
         Float  addoralFood  = soilList.getAddoralFood();
         Float  addoralSoil  = soilList.getAddoralSoil();
         Float adddermalSoil  = soilList.getAdddermalSoil();
        map.put("addoralFood", addoralFood);
        map.put("addoralSoil", addoralSoil);
        map.put("adddermalSoil", adddermalSoil);

        /*空气*/
        Air airList =  airService.getNewAirData();
        Float addinh = airList.getAddinh();
        map.put("addinh",addinh);


        /* resultMap.put("cityName:"城市,)*/
        resultMap.put("return_code",1);
        resultMap.put("cityDta",map);
        return  resultMap;
     }


    /*获取环境点位图信息*/
    @GetMapping("/figure/getEnvMap")
     public Map getEnvMap(){
        Map resultMap = this.getCityData();
        return resultMap;

    }


     /*# 医院点位图*/
    @GetMapping("/figure/getHospitalMap")
   public  Map   hospitalData(){
          Map<String,String>  map = new HashMap<String,String>();
          return map;
     }


 /*    # 获取某个城市的所有参数*/
   @GetMapping("/figure/getCityData")
   public Map getCityData(){
       Map<String,String> map  = new HashMap<String,String>();
       return  map;
   }

}



