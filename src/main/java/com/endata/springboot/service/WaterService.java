package com.endata.springboot.service;

import com.endata.springboot.model.Water;

import java.util.List;

/**
 * @author ligang
 * @create 2019-11-17 20:21
 */
public interface WaterService {
    public List<Water>  getWaterData();
    int insert(Water water);
    public Water getNewWaterData();
    public List<Water> getWaterEnvMapData();
    public Water  getNewWaterDataByName(String cityName);
}
