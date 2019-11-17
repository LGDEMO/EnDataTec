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
  private SoilMapper  soilMapper;

    @Override
    public List<Soil> getSoilData() {
        return soilMapper.getSoilData();
    }
}
