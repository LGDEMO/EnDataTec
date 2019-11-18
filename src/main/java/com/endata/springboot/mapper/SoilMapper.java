package com.endata.springboot.mapper;

import com.endata.springboot.model.Soil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SoilMapper {
    int deleteByPrimaryKey(Integer soilId);

    int insert(Soil record);
    int insert_one(Soil soil);
    int insert_two(Soil soil);
    int insert_three(Soil soil);

    int insertSelective(Soil record);

    Soil selectByPrimaryKey(Integer soilId);

    int updateByPrimaryKeySelective(Soil record);

    int updateByPrimaryKey(Soil record);
     List<Soil> getSoilData();
}