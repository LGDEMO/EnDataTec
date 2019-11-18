package com.endata.springboot.service;

import com.endata.springboot.model.Soil;

import java.util.List;

/**
 * @author ligang
 * @create 2019-11-17 20:35
 */
public interface SoilService {

    public List<Soil> getSoilData();
    int insert_one(Soil soil);
    int insert_two(Soil soil);
    int insert_three(Soil soil);
}
