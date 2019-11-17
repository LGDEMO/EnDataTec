package com.endata.springboot.controller;

import com.endata.springboot.model.Air;
import com.endata.springboot.model.Soil;
import com.endata.springboot.service.AirService;
import com.endata.springboot.service.SoilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-11-10 20:29
 * 皮肤接触土壤中污染物的日均暴露量 ,mg/(kg.d)
 */
@Controller

 public  class SoilController {

    private SoilService soilService;
    @Autowired
    public void setService(SoilService soilService) {
        this.soilService = soilService;
    }

    /*获取土壤数据*/
    @GetMapping("soil/getSoilData")
    public Map getSoilData(@RequestBody Soil soil){
        Map<String, List<Soil>> map = new HashMap<String, List<Soil>>();
        List<Soil> airList  = soilService.getSoilData();
        return  map;


    }

}
