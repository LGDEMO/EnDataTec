package com.endata.springboot.service.impl;

import com.endata.springboot.mapper.AirMapper;
import com.endata.springboot.mapper.UserMapper;
import com.endata.springboot.model.Air;
import com.endata.springboot.service.AirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ligang
 * @create 2019-11-17 19:39
 */
@Service
public class AirServiceImpl implements AirService {

    @Autowired
    private AirMapper airMapper;
    @Override
    public List<Air> getAirData() {
        return airMapper.getAirData();
    }

    @Override
    public int insert(Air air) {
        return airMapper.insert(air);
    }

    @Override
    public Air getNewAirData() {
        return airMapper.getNewAirData();
    }
}
