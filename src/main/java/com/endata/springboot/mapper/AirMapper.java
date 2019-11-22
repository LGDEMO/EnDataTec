package com.endata.springboot.mapper;

import com.endata.springboot.model.Air;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AirMapper {
    int insert(Air record);

    int insertSelective(Air record);

    public List<Air> getAirData();
    public  Air getNewAirData();
}