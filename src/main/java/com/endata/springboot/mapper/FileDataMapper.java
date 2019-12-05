package com.endata.springboot.mapper;

import com.endata.springboot.model.FileData;
import org.apache.ibatis.annotations.Mapper;

import java.io.File;
import java.util.List;

@Mapper
public interface FileDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileData record);

    int insertSelective(FileData record);

    FileData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileData record);

    int updateByPrimaryKey(FileData record);
    List<FileData> getFileData(String type);
}