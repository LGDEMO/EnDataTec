package com.endata.springboot.controller;

import com.endata.springboot.model.Air;
import com.endata.springboot.model.User;
import com.endata.springboot.service.AirService;
import com.endata.springboot.service.UserService;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ligang
 * @create 2019-11-10 20:28
 * 经口摄入食物的日均暴露量,mg/(kg.d)
 */
@Controller
@RequestMapping
public class AirController {

 private AirService airService;
    @Autowired
    public void setService(AirService airService) {
        this.airService = airService;
    }

/*获取空气数据*/
     @GetMapping("water/getAirData")
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
    @PostMapping
    public Map calAirData(@RequestBody Air air) throws Exception{
        Map<String,String> resultMap =  new HashMap<String,String>();
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
        }
        long ADDinh  = air.getCa()*air.getIr()*air.getEt()*air.getEf()*air.getEd()/(air.getBw()*air.getAt());
        air.setAddinh(ADDinh);
        int number = airService.insert(air);
        resultMap.put("计算并插入数据成功","resultCode："+number);
        resultMap.put("AirData","计算结果："+ADDinh);
        return  resultMap;
    }
}
