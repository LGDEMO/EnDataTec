package com.endata.springboot.service;
import com.endata.springboot.model.Air;


import java.util.List;

/**
 * @author ligang
 * @create 2019-11-17 19:38
 */
public interface AirService {
    public List<Air> getAirData();
    int insert(Air air);
}