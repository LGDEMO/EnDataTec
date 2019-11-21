package com.endata.springboot.service.impl;

import com.endata.springboot.mapper.WaterMapper;
import com.endata.springboot.model.Water;
import com.endata.springboot.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ligang
 * @create 2019-11-17 20:24
 */
@Service
public class WaterServiceImpl implements WaterService {

    @Autowired
    private WaterMapper waterMapper;

    @Override
    public List<Water> getWaterData() {
        return waterMapper.getWaterData();
    }


    @Override
    public int insert(Water water) {
        return waterMapper.insert(water);
    }

    @Override
    public Water getNewWaterData() {
        return waterMapper.getNewWaterData();
    }

}
