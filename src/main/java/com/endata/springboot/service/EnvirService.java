package com.endata.springboot.service;

import com.endata.springboot.model.Envir;

import java.util.List;

/**
 * @author ligang
 * @create 2019-12-01 15:07
 */

public interface EnvirService {
    public List<Envir> getEnvirData(Integer cityCode); /*# 环境监测点位获取(管理员)*/
    List<Envir> getEnvirMapData();//所有的环境监测点位获取(管理员)

    int deleteByPrimaryKey(Integer id);//删除数据

    int insertSelective(Envir record);//增加数据
}
