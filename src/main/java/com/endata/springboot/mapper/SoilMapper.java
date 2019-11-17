package com.endata.springboot.mapper;

import com.endata.springboot.model.Soil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SoilMapper {
    int deleteByPrimaryKey(Integer soilId);

    int insert(Soil record);

    int insertSelective(Soil record);

    Soil selectByPrimaryKey(Integer soilId);

    int updateByPrimaryKeySelective(Soil record);

    int updateByPrimaryKey(Soil record);
    public List<Soil> getSoilData();
}