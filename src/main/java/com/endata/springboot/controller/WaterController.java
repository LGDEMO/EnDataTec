package com.endata.springboot.controller;


import com.endata.springboot.model.Water;

import com.endata.springboot.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-11-10 20:25
 * 经口摄入土壤中污染物的日均暴露量,mg/(kg.d)
 */
@RequestMapping
@Controller
public class WaterController {

    private WaterService waterService;
    @Autowired
    public void setService(WaterService waterService) {
        this.waterService = waterService;
    }

    @GetMapping("water/getWaterData")
    public Map getWaterData(){
        Map<String, List<Water>> map = new HashMap<String, List<Water>>();
        Map<String,String> resultMap =  new HashMap<String,String>();
        List<Water> waterList  =  waterService.getWaterData();
        if(waterList.isEmpty()){
            resultMap.put("code","404");
            resultMap.put("waterData","查询数据为空");
        }else{
            map.put("waterData",waterList);
            return map;
        }
        return map;
    }}
