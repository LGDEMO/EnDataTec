package com.endata.springboot.service.impl;

import com.endata.springboot.mapper.FileDataMapper;
import com.endata.springboot.model.FileData;
import com.endata.springboot.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author ligang
 * @create 2019-12-04 16:56
 */
@Service
public class FileServiceImpl implements FileService {

     @Autowired
     private FileDataMapper fileDataMapper;

    @Override
    public List<FileData> getFileData(String type) {
        return fileDataMapper.getFileData(type);
    }

    @Override
    public int insertSelective(FileData record) {
        return fileDataMapper.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return fileDataMapper.deleteByPrimaryKey(id);
    }
}
