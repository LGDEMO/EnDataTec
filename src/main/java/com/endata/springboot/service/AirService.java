package com.endata.springboot.service;
import com.endata.springboot.model.Air;


import java.util.List;

/**
 * @author ligang
 * @create 2019-11-17 19:38
 */
public interface AirService {
    public List<Air> getAirData(Integer cityCode);
    int insert(Air air);
    public Air getNewAirData(Integer cityCode);
    public List<Air>  getAirEnvMapData();
    public Air  getNewAirDataByName(String cityName);

}
