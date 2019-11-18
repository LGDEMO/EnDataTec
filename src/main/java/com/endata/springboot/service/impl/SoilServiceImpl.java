package com.endata.springboot.service.impl;

import com.endata.springboot.mapper.SoilMapper;
import com.endata.springboot.model.Soil;
import com.endata.springboot.service.SoilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ligang
 * @create 2019-11-17 20:38
 */
@Service
public class SoilServiceImpl implements SoilService {

  @Autowired
  private SoilMapper soilMapper;

    @Override
    public List<Soil> getSoilData() {
        return soilMapper.getSoilData();
    }

  @Override
  public int insert(Soil soil) {
    return soilMapper.insert(soil);
  }

  @Override
  public int insert_one(Soil soil) {
    return soilMapper.insert_one(soil);
  }

  @Override
  public int insert_two(Soil soil) {
    return soilMapper.insert_two(soil);
  }

  @Override
  public int insert_three(Soil soil) {
    return soilMapper.insert_three(soil);
  }
}
