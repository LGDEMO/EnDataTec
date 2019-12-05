package com.endata.springboot.service;

import com.endata.springboot.model.FileData;

import java.io.File;
import java.util.List;

/**
 * @author ligang
 * @create 2019-12-04 16:55
 */
public interface FileService {

    List<FileData>  getFileData(String type);//获取文件数据
    int insertSelective(FileData record);//插入数据
    int deleteByPrimaryKey(Integer id);//删除数据
}
