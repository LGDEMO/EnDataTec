package com.endata.springboot.mapper;

import com.endata.springboot.model.Air;

public interface AirMapper {
    int deleteByPrimaryKey(Integer addinhId);

    int insert(Air record);

    int insertSelective(Air record);

    Air selectByPrimaryKey(Integer addinhId);

    int updateByPrimaryKeySelective(Air record);

    int updateByPrimaryKey(Air record);
}