package com.endata.springboot.service.impl;

import com.endata.springboot.mapper.EnvirMapper;
import com.endata.springboot.model.Envir;
import com.endata.springboot.service.EnvirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ligang
 * @create 2019-12-01 15:07
 */
@Service
public class EnvirServiceImpl  implements EnvirService {

   @Autowired
   private EnvirMapper envirMapper;

    /*# 环境监测点位获取(管理员)*/
    @Override
    public List<Envir> getEnvirData(Integer cityCode) {
        return envirMapper.getEnvirData(cityCode);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return envirMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Envir record) {
        return envirMapper.insertSelective(record);
    }

}
