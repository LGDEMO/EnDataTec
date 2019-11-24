package com.endata.springboot.service;

import com.endata.springboot.model.Soil;

import java.util.List;

/**
 * @author ligang
 * @create 2019-11-17 20:35
 */
public interface SoilService {

    public List<Soil> getSoilData(Integer cityCode);
    int insert(Soil soil);
    public Soil getNewSoilData(Integer cityCode);
    public List<Soil> getSoilEnvMapData();
    public Soil getNewSoilDataByName(String cityName);
}
