package com.endata.springboot.mapper;

import com.endata.springboot.model.Water;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WaterMapper {
    int insert(Water record);

    int insertSelective(Water record);
    public List<Water> getWaterData();//获取全部数据
    public Water getNewWaterData();//获取最新饿一条数据

}