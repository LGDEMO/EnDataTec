package com.endata.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.endata.springboot.model.CityParam;
import com.endata.springboot.model.Envir;
import com.endata.springboot.model.EnvirMap;
import com.endata.springboot.model.MapperResult;
import com.endata.springboot.service.EnvirService;
import com.endata.springboot.util.NameByCode;
import com.endata.springboot.util.NewDate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;

/**
 * @author ligang
 * @create 2019-12-01 15:11
 */
@RestController
@CrossOrigin
public class EnvirController {

    @Autowired
    private EnvirService envirService;

    @Autowired
    private NameByCode nameByCode;

    /*# 环境监测点位获取(管理员)*/
    @GetMapping("/envir/getEnvirData")
    public Map getEnvirData(@RequestParam("cityCode") Integer cityCode) {
        Map resultMap = new HashMap<>();
        if (cityCode != null) {
            List<Envir> envirList = envirService.getEnvirData(cityCode);
            if (envirList != null) {
                resultMap.put("return_code", 200);
                resultMap.put("return_data", envirList);
            } else {
                resultMap.put("return_code", 404);
                resultMap.put("message", "cityCode错误！！！");
            }
        } else {
            resultMap.put("return_code", 404);
            resultMap.put("message", "cityCode为空！！！");
        }
        return resultMap;
    }

    /*  # 环境监测点位删除(管理员)*/
    @GetMapping("/envir/deleteEnvirData")
    public Map deleteEnvirData(@RequestParam("cityCode") Integer cityCode, @RequestParam("id") Integer id) {
        Map resultMap = new HashMap<>();
        if (id != null) {
            int number = envirService.deleteByPrimaryKey(id);
            resultMap.put("return_code", 200);
            resultMap.put("message", "删除了" + number + "条数据");
        } else {
            resultMap.put("return_code", 404);
            resultMap.put("message", "id为空！！！");
        }
        return resultMap;
    }


    /*# 环境监测点位添加(管理员)*/
    @PostMapping("/envir/addEviorData")
    public Map addEviorData(@RequestBody Envir envir) throws ParseException {
        Map resultMap = new HashMap<>();
        Integer cityCode = envir.getCityCode();
        if (cityCode != null) {
            envir.setCityCode(cityCode);
            String CityName = nameByCode.getCityName(cityCode);
            envir.setCityName(CityName);
        }

        String dotName = envir.getDotName();
        if (dotName != null) {
            envir.setDotName(dotName);
        }
        Float so2 = envir.getSo2();
        if (so2 != null) {
            envir.setSo2(so2);
        }
        Float no2 = envir.getNo2();
        if (no2 != null) {
            envir.setNo2(no2);
        }
        Float co = envir.getCo();
        if (co != null) {
            envir.setCo(co);
        }
        Float pm25 = envir.getPm25();
        if (pm25 != null) {
            envir.setPm25(pm25);
        }
        Float o3 = envir.getO3();
        if (o3 != null) {
            envir.setO3(o3);
        }
        Date date = envir.getDate();
        if (date != null) {
            envir.setDate(date);
        } else {
            NewDate newDate = new NewDate();
            envir.setDate(newDate.getNewDate());
        }
        int number = envirService.insertSelective(envir);
        resultMap.put("return_code", 200);
        resultMap.put("message", "增加了" + number + "条数据");
        return resultMap;
    }

    /* # 环境监测点位图获取(所有用户)*/
    @GetMapping("/envir/getEnvirMapData")
    public Map getEnvirMapData() {
        Map resultMap = new HashMap<>();

        Map<Integer, List<CityParam>> cityMap = new HashMap<>();
        Set<Envir> envirSet = new HashSet<>();
        List<Envir> envirList = envirService.getEnvirMapData();
        envirList.forEach(item -> {
            final Integer cityCode = item.getCityCode();
            final String cityName = item.getCityName();
            List<CityParam> ls = cityMap.get(cityCode);
            if (null == ls || ls.isEmpty()) {
                ls = new ArrayList<>();
            }
            CityParam cityParam = new CityParam();
            BeanUtils.copyProperties(item, cityParam);
            ls.add(cityParam);
            cityMap.put(cityCode, ls);
            Envir envir = new Envir(cityCode, cityName, ls);
            envirSet.add(envir);
        });

        resultMap.put("return_code", 200);
        resultMap.put("return_data", envirSet);
        return resultMap;
    }

}
