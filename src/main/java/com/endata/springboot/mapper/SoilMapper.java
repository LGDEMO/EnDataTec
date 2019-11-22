package com.endata.springboot.mapper;

import com.endata.springboot.model.Soil;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SoilMapper {
    int insert(Soil record);

    int insertSelective(Soil record);
    List<Soil> getSoilData();
    public Soil getNewSoilData();
    public List<Soil> getSoilEnvMapData();
    public Soil getNewSoilDataByName(String cityName);
}