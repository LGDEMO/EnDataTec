package com.endata.springboot.controller;

import com.endata.springboot.model.Air;
import com.endata.springboot.model.MapperResult;
import com.endata.springboot.model.Soil;
import com.endata.springboot.model.Water;
import com.endata.springboot.service.AirService;
import com.endata.springboot.service.SoilService;
import com.endata.springboot.service.UserService;
import com.endata.springboot.service.WaterService;

import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


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
    private java.lang.Object JSONObject;

    /*# 获取柱状图信息*/
    @GetMapping("/figure/getHistogram")
     public Map getHistogram(){
        Map resultMap  = new HashMap<>();
        Map map  = new HashMap<>();

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

        resultMap.put("return_code",1);
        resultMap.put("cityDta",map);
        return  resultMap;
     }


    /*获取环境点位图信息*/
    @GetMapping("/figure/getEnvMap")
     public Map getEnvMap(){

        Map  resultEnvMap = new LinkedHashMap();
        /*空气所有数据*/
        List<Air>  airList = airService.getAirEnvMapData();
        List<Water> waterList = waterService.getWaterEnvMapData();
        List<Soil> soilList  = soilService.getSoilEnvMapData();
        Map<Integer, MapperResult> map = new HashMap<>();
        airList.forEach(item -> {
            final Integer cityCode = item.getCityCode();
            MapperResult result = map.get(cityCode);
            if (null == result) {
                result = new MapperResult(cityCode, item.getCityName());
            }
            result.setAddinh(item.getAddinh());
            map.put(cityCode, result);
        });
        soilList.forEach(item -> {
            final Integer cityCode = item.getCityCode();
            MapperResult result = map.get(cityCode);
            if (null == result) {
                result = new MapperResult(cityCode, item.getCityName());
            }
            result.setAddoralFood(item.getAddoralFood());
            result.setAddoralSoil(item.getAddoralSoil());
            result.setAdddermalSoil(item.getAdddermalSoil());
            map.put(cityCode, result);
        });
        waterList.forEach(item -> {
            final Integer cityCode = item.getCityCode();
            MapperResult result = map.get(cityCode);
            if (null == result) {
                result = new MapperResult(cityCode, item.getCityName());
            }
            result.setAddoralWater(item.getAddoralWater());
            result.setAdddermalWater(item.getAdddermalWater());
            map.put(cityCode, result);
        });
        Collection<MapperResult> collection = map.values();
        resultEnvMap.put("return_code",1);
        resultEnvMap.put("data",collection);
        return resultEnvMap;
    }

     /*# 医院点位图*/
    @GetMapping("/figure/getHospitalMap")
   public  Map   hospitalData(){
          Map<String, Object>  map = new HashMap<String,Object>();
          return map;
     }

    /*    # 获取某个城市的各项计算结果*/
    @GetMapping("/figure/getCityResult")
    public Map getCityResult(@RequestParam(name = "cityName") String cityName){
        Air airResultList =  airService.getNewAirDataByName(cityName);
        Soil soilList  = soilService.getNewSoilDataByName(cityName);
        Water waterList  =  waterService.getNewWaterDataByName(cityName);
        /*空气*/
        Map resultDataMap  = new HashMap<>();
        Map  sMap  = new HashMap<>();
        Map<String,Float> airMap  = new HashMap<String,Float>();
        airMap.put("Addinh",airResultList.getAddinh());
        resultDataMap.put("airData",airMap);
        /*土壤*/
        Map<String,Float> soilMap  = new HashMap<String,Float>();
        soilMap.put("AddoralSoil",soilList.getAddoralSoil());
        soilMap.put("AddoralFood",soilList.getAddoralFood());
        soilMap.put("AdddermalSoil",soilList.getAdddermalSoil());
        resultDataMap.put("soilData",soilMap);
        /*水*/
        Map<String,Float> waterMap  = new HashMap<String,Float>();
        waterMap.put("AdddermalWater",waterList.getAdddermalWater());
        waterMap.put("AddoralWater",waterList.getAddoralWater());
        resultDataMap.put("waterData",waterMap);
        sMap.put("retuen_code",1);
        sMap.put("data",resultDataMap);
        return  sMap;

    }

 /*    # 获取某个城市的所有参数*/
   @GetMapping("/figure/getCityParam")
   public Map getCityParam(@RequestParam(name = "cityName") String cityName){
       Air airList =  airService.getNewAirDataByName(cityName);
       Soil soilList  = soilService.getNewSoilDataByName(cityName);
       Water waterList  =  waterService.getNewWaterDataByName(cityName);
       /*空气*/
       Map resultMap  = new HashMap<>();
       Map<String,Float> airMap  = new HashMap<String,Float>();
       airMap.put("ca",airList.getCa());
       airMap.put("ir",airList.getIr());
       airMap.put("et",airList.getEt());
       airMap.put("ef",airList.getEf());
       airMap.put("ed",airList.getEd());
       airMap.put("bw",airList.getBw());
       airMap.put("at",airList.getAt());
       resultMap.put("airData",airMap);
      /*土壤*/
       Map<String,Float> soilMap  = new HashMap<String,Float>();
       soilMap.put("cf",soilList.getCf());
       soilMap.put("irf",soilList.getIrf());
       soilMap.put("eff",soilList.getEff());
       soilMap.put("ed",soilList.getEd());
       soilMap.put("bw",soilList.getBw());
       soilMap.put("at",soilList.getAt());
       soilMap.put("cso",soilList.getCso());
       soilMap.put("irs",soilList.getIrs());
       soilMap.put("cf1",soilList.getCf1());
       soilMap.put("cs",soilList.getCs());
       soilMap.put("af",soilList.getAf());
       soilMap.put("absd",soilList.getAbsd());
       resultMap.put("soilData",soilMap);
       /*水*/
       Map<String,Float> waterMap  = new HashMap<String,Float>();
       waterMap.put("cw",waterList.getCw());
       waterMap.put("irw",waterList.getIrw());
       waterMap.put("ed",waterList.getEd());
       waterMap.put("ef",waterList.getEf());
       waterMap.put("bw",waterList.getBw());
       waterMap.put("at",waterList.getAt());
       waterMap.put("saw",waterList.getSaw());
       waterMap.put("pc",waterList.getPc());
       waterMap.put("cf",waterList.getCf());
       waterMap.put("et",waterList.getEt());
       resultMap.put("waterData",waterMap);
       resultMap.put("retuen_code",1);
       return  resultMap;
   }
}



