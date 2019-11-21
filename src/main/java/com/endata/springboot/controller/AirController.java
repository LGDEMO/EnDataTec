package com.endata.springboot.controller;

import com.endata.springboot.model.Air;
import com.endata.springboot.service.AirService;
import com.endata.springboot.util.NameByCode;
import com.endata.springboot.util.NewDate;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-11-10 20:28
 * 经口摄入食物的日均暴露量,mg/(kg.d)
 */
@RestController
@CrossOrigin
public class AirController {
    private static final Logger LOGGER = LogManager.getLogger(AirController.class);
    private AirService airService;
    @Autowired
    public void setService(AirService airService) {
        this.airService = airService;
    }

    @Autowired
    private NameByCode nameByCode;


/*获取空气数据*/
@CrossOrigin(origins="http://localhost:8080",maxAge = 3600)
     @GetMapping("air/getAirData")
      public Map getAirData(){
         Map<String, List<Air>> map = new HashMap<String, List<Air>>();
         Map<String,String> resultMap =  new HashMap<String,String>();
         List<Air> airList  =  airService.getAirData();
         if(airList.isEmpty()){
             resultMap.put("code","404");
             resultMap.put("AirData","查询数据为空");
         }else{
             map.put("AirData",airList);
             return map;
         }
         return map;
    }

     /*计算空气数据，并保存到数据库*/
    @PostMapping("air/calAirData")
    public Map calAirData(@RequestBody Air air) throws Exception{
        Map<String,Float> resultMap =  new HashMap<String,Float>();
        if(air.getCa()!=null){
            air.setCa(air.getCa());
        } if(air.getIr()!=null){
            air.setIr(air.getIr());
        } if(air.getEt()!=null){
            air.setEt(air.getEt());
        }if(air.getEd()!=null){
            air.setEd(air.getEd());
        }if(air.getBw()!=null){
            air.setBw(air.getBw());
        }if(air.getAt()!=null){
            air.setAt(air.getAt());
        }if(air.getCityCode() != null){
            air.setCityCode(air.getCityCode());
            String CityName  =  nameByCode.getCityName(air.getCityCode());
            air.setCityName(CityName);
        }else{
            Map<String,String> cityMap  = new HashMap<String,String>();
            cityMap.put("CityCode","城市代码错误！！！");
            return cityMap;
        }
        NewDate newDate  = new NewDate();
        air.setDate(newDate.getNewDate());
        float ADDinh  = air.getCa()*air.getIr()*air.getEt()*air.getEf()*air.getEd()/(air.getBw()*air.getAt());
        air.setAddinh(ADDinh);
        int number = airService.insert(air);
        resultMap.put("ADDinh",ADDinh);
        Map<String,Map<String,Float>> map  = new HashMap<String,Map<String,Float>>();
        map.put("AirData",resultMap);
        return  map;
    }
}
