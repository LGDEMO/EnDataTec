package com.endata.springboot.controller;

import com.endata.springboot.model.Air;
import com.endata.springboot.model.Soil;
import com.endata.springboot.model.Water;
import com.endata.springboot.service.AirService;
import com.endata.springboot.service.SoilService;
import com.endata.springboot.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author ligang
 * @create 2019-11-18 9:16
 * 主界面各种图的数据展示
 */
@Controller
public class FigureController {

    /*获取柱状图信息*/
    @GetMapping("figure/getCityData")
     public Map getCityData(){
         Map<String, Map<String,List<Object>>> resultMap  = new HashMap<String, Map<String,List<Object>>>();
         Map<String,List<Object>> map  = new HashMap<String,List<Object>>();
        WaterService waterService = null;
        List<Water> waterList  = (List<Water>) waterService.getWaterData(); /*水*/
        map.put("waterData", Collections.singletonList(waterList));
        SoilService soilService = null;
        List<Soil> soilList  = soilService.getSoilData();/*土壤*/
        map.put("soilData", Collections.singletonList(soilList));
        AirService airService = null;
        List<Air> airList =  airService.getAirData();/*空气*/
        map.put("airData", Collections.singletonList(airList));
        resultMap.put("cityDta",map);
         return  resultMap;
     }
/*
   public  Map   MapData(){
          Map<String,String>  map = new HashMap<String,string>();
          return map;
     }*/



}



