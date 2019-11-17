package com.endata.springboot.mapper;

import com.endata.springboot.model.Water;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WaterMapper {
    int deleteByPrimaryKey(Integer waterId);

    int insert(Water record);

    int insertSelective(Water record);

    Water selectByPrimaryKey(Integer waterId);

    int updateByPrimaryKeySelective(Water record);

    int updateByPrimaryKey(Water record);
    public List<Water> getWaterData();
}