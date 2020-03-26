package com.endata.springboot.controller;

import com.endata.springboot.model.Soil;
import com.endata.springboot.service.SoilService;
import com.endata.springboot.util.NameByCode;
import com.endata.springboot.util.NewDate;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Hashtable;
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
    @Autowired
    private NameByCode nameByCode;
    /*获取土壤数据*/
   /* @CrossOrigin(origins="http://localhost:8080",maxAge = 3600)*/
    @GetMapping("/soil/getSoilData")
    public Map getSoilData(@RequestParam("city_code") Integer cityCode){
        Map<String, List<Soil>> map = new HashMap<String, List<Soil>>();
        Map<String,String> resultMap =  new HashMap<String,String>();
        if(cityCode != null) {
        List<Soil> soilList  = soilService.getSoilData(cityCode);
        if(soilList.isEmpty()){
            resultMap.put("code","404");
            resultMap.put("SoilData","查询数据为空");
        }else{
            map.put("SoilData",soilList);
            return map;
        }
        }else{
            Map<String,Integer>  cityMap = new Hashtable<>();
            cityMap.put("return_code",0);
            return  cityMap;
        }
        return map;
    }

    /**
     * 计算土壤数据
     */
    @PostMapping("soil/calSoilData")
    public Map calSoilData(@RequestBody Soil soil) throws ParseException {
        Map<String, Float> map = new HashMap<String,Float>();
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
        }if(soil.getCityCode()!= null){
            soil.setCityCode(soil.getCityCode());
            String CityName  =  nameByCode.getCityName(soil.getCityCode());
            soil.setCityName(CityName);
        }else{
            Map<String,String> cityMap  = new HashMap<String,String>();
            cityMap.put("CityCode","城市代码错误！！！");
            return cityMap;
        }
        NewDate newDate  = new NewDate();
        soil.setDate(newDate.getNewDate());

        /*土壤公式一计算*/
        Float ADDoral_food = soil.getCf()*soil.getIrf()*soil.getEff()*soil.getEd()/(soil.getBw()*soil.getAt());
        soil.setAddoralFood(ADDoral_food);

        /*土壤公式二计算*/
        Float ADDoral_soil = soil.getCso()*soil.getIrs()*soil.getCf1()*soil.getEf()*soil.getEd()/(soil.getBw()*soil.getAt());
        soil.setAddoralSoil(ADDoral_soil);

        /*土壤公式三计算*/
        Float ADDdermal_soil = soil.getCs()*soil.getCf1()*soil.getSas()*soil.getAf()*soil.getAbsd()*soil.getEf()*soil.getEd()/(soil.getBw()*soil.getAt());
        soil.setAdddermalSoil(ADDdermal_soil);
         int number = soilService.insert(soil);
         Map<String,Map<String,Float>> resultMap = new HashMap<String,Map<String,Float>>();
         map.put("ADDoral_food",ADDoral_food);
         map.put("ADDoral_soil",ADDoral_soil);
         map.put("ADDdermal_soil",ADDdermal_soil);
         resultMap.put("SoilData",map);
            return  map;
    }

}
