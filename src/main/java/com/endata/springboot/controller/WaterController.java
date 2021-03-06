package com.endata.springboot.controller;


import com.endata.springboot.model.Water;
import com.endata.springboot.service.WaterService;
import com.endata.springboot.util.NameByCode;
import com.endata.springboot.util.NewDate;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.NamedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-11-10 20:25
 * 经口摄入土壤中污染物的日均暴露量,mg/(kg.d)
 */
@RestController
@CrossOrigin
public class WaterController {
    private static final Logger LOGGER = LogManager.getLogger(WaterController.class);
    private WaterService waterService;
    @Autowired
    public void setService(WaterService waterService) {
        this.waterService = waterService;
    }

    @Autowired
    private NameByCode nameByCode;
    @GetMapping("water/getWaterData")
    public Map getWaterData(@RequestParam("city_code") Integer cityCode){
        Map<String, List<Water>> map = new HashMap<String, List<Water>>();
        Map<String,String> resultMap =  new HashMap<String,String>();
        if(cityCode != null) {
        List<Water> waterList  =  waterService.getWaterData(cityCode);
        if(waterList.isEmpty()){
            resultMap.put("code","404");
            resultMap.put("waterData","查询数据为空");
        }else{
            map.put("waterData",waterList);
            return map;
        }
        }else{
            Map<String,Integer>  cityMap = new Hashtable<>();
            cityMap.put("return_code",0);
            return  cityMap;
        }
        return map;
    }

   /* @CrossOrigin(origins="http://localhost:8080",maxAge = 3600)*/
    @PostMapping("water/calWaterData")
     public  Map calWaterData(@RequestBody Water water) throws ParseException {
        Map<String, Float> resultMap =  new HashMap<String,Float>();
        if(water.getCw()!=null){
            water.setCw(water.getCw());
        } if(water.getIrw()!=null){
            water.setIrw(water.getIrw());
        } if(water.getEf()!=null){
            water.setEf(water.getEf());
        } if(water.getEd()!=null){
            water.setEd(water.getEd());
        } if(water.getBw()!=null){
            water.setBw(water.getBw());
        } if(water.getAt()!=null){
            water.setAt(water.getAt());
        } if(water.getSaw()!=null){
            water.setSaw(water.getSaw());
        } if(water.getPc()!=null){
            water.setPc(water.getPc());
        } if(water.getCf()!=null){
            water.setCf(water.getCf());
        } if(water.getEt()!=null){
            water.setEt(water.getEt());
        }if(water.getCityCode() != null){
            water.setCityCode(water.getCityCode());
            String CityName  =  nameByCode.getCityName(water.getCityCode());
            water.setCityName(CityName);
        }else{
            Map<String,String> cityMap  = new HashMap<String,String>();
            cityMap.put("CityCode","城市代码错误！！！");
            return cityMap;
        }

        NewDate newDate  = new NewDate();
        water.setDate(newDate.getNewDate());
        Float ADDoral_water = water.getCw()*water.getIrw()*water.getEf()*water.getEd() /(water.getBw()*water.getAt());
        Float ADDdermal_water = water.getCw()*water.getSaw()*water.getPc()*water.getCf()*water.getEf()*water.getEt()*water.getEd()/(water.getBw()*water.getAt());
        water.setAddoralWater(ADDoral_water);
        water.setAdddermalWater(ADDdermal_water);
             //公式一
            resultMap.put("ADDoral_water",ADDoral_water);
             //公式二
            resultMap.put("ADDdermal_water", ADDdermal_water);
        /*水公式计算结果的插入*/
        int number_two = waterService.insert(water);
        Map<String, Map<String, Float>> map  = new HashMap<>();
        map.put("waterData",resultMap);
        return  map ;
    }
}
