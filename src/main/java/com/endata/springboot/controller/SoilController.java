package com.endata.springboot.controller;

import com.endata.springboot.model.Soil;
import com.endata.springboot.service.SoilService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-11-10 20:29
 * 皮肤接触土壤中污染物的日均暴露量 ,mg/(kg.d)
 */
@RestController
@CrossOrigin
 public  class SoilController {
    private static final Logger LOGGER = LogManager.getLogger(SoilController.class);


    private SoilService soilService;
    @Autowired
    public void setService(SoilService soilService) {
        this.soilService = soilService;
    }

    /*获取土壤数据*/
    @GetMapping("/soil/getSoilData")
    public Map getSoilData(){
        Map<String, List<Soil>> map = new HashMap<String, List<Soil>>();
        Map<String,String> resultMap =  new HashMap<String,String>();
        List<Soil> soilList  = soilService.getSoilData();
        if(soilList.isEmpty()){
            resultMap.put("code","404");
            resultMap.put("SoilData","查询数据为空");
        }else{
            map.put("SoilData",soilList);
            return map;
        }
        return map;
    }

    /**
     * 计算土壤数据
     */
    @CrossOrigin(origins="http://localhost:8080",maxAge = 3600)
    @PostMapping("soil/calSoilData")
    public Map calSoilData(@RequestBody Soil soil){
        Map<String, String> map = new HashMap<>();
        if(soil.getCf()!=null){
           soil.setCf(soil.getCf());
        } if(soil.getIrf()!=null){
            soil.setIrf(soil.getIrf());
        } if(soil.getEff()!=null){
            soil.setEff(soil.getEff());
        } if(soil.getEd()!=null){
            soil.setEd(soil.getEd());
        } if(soil.getBw()!=null){
            soil.setBw(soil.getBw());
        } if(soil.getAt()!=null){
            soil.setAt(soil.getAt());
        } if(soil.getCso()!=null){
            soil.setCso(soil.getCso());
        } if(soil.getIrs()!=null){
            soil.setIrs(soil.getIrs());
        } if(soil.getEf()!=null){
            soil.setEf(soil.getEf());
        } if(soil.getCs()!=null){
            soil.setCs(soil.getCs());
        } if(soil.getCf1()!=null){
            soil.setCf1(soil.getCf1());
        } if(soil.getSas()!=null){
            soil.setSas(soil.getSas());
        } if(soil.getAf()!=null){
            soil.setAf(soil.getAf());
        } if(soil.getAbsd()!=null){
            soil.setAbsd(soil.getAbsd());
        }
        /*土壤公式一计算*/
       long ADDoral_food = soil.getCf()*soil.getIrf()*soil.getEff()*soil.getEd()/(soil.getBw()*soil.getAt());
       if(ADDoral_food !=0){
           soil.setAddoralFood(ADDoral_food);
           int number_one = soilService.insert_one(soil);
       }
        /*土壤公式二计算*/
       long ADDoral_soil = soil.getCso()*soil.getIrs()*soil.getCf1()*soil.getEf()*soil.getEd()/(soil.getBw()*soil.getAt());
       if(ADDoral_soil !=0){
           soil.setAddoralSoil(ADDoral_soil);
           int number_two = soilService.insert_two(soil);
       }
        /*土壤公式三计算*/
       long ADDdermal_soil = soil.getCs()*soil.getCf()*soil.getSas()*soil.getAf()*soil.getAbsd()*soil.getEf()*soil.getEd()/(soil.getBw()*soil.getAt());
     if(ADDdermal_soil !=0){
         soil.setAdddermalSoil(ADDdermal_soil);
         int number_three = soilService.insert_three(soil);
     }
     map.put("ADDoral_food","土壤第一个公式计算结果如下"+ADDoral_food);
     map.put("ADDoral_soil","土壤第二个公式计算结果如下："+ADDoral_soil);
     map.put("ADDdermal_soil","土壤第三个公式计算结果如下："+ADDdermal_soil);
        return  map;
    }

}
