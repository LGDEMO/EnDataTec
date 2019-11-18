package com.endata.springboot.service;

import com.endata.springboot.model.Water;

import java.util.List;

/**
 * @author ligang
 * @create 2019-11-17 20:21
 */
public interface WaterService {
    public List<Water>  getWaterData();
    int insert_one(Water water);
    int insert_two(Water water);
    int insert(Water water);
}
