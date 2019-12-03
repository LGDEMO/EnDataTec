package com.endata.springboot;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.endata.springboot.model.CityParam;
import com.endata.springboot.model.Envir;
import com.endata.springboot.service.EnvirService;
import org.apache.shiro.crypto.hash.Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author ligang
 * @create 2019-12-03 20:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CityTest {

    @Autowired
    private EnvirService envirService;

    @Test
    public void test() {
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

        System.out.println(JSONObject.toJSONString(envirSet, SerializerFeature.DisableCircularReferenceDetect));
    }
}
