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
    public  List<Air>  getAirEnvMapData();
    public Air getNewAirDataByName(String cityName);//获取最新数据的空气参数
}