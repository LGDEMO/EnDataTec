package com.endata.springboot.mapper;

import com.endata.springboot.model.Envir;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnvirMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Envir record);

    int insertSelective(Envir record);

    Envir selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Envir record);

    int updateByPrimaryKey(Envir record);
    public List<Envir> getEnvirData(Integer cityCode);
}