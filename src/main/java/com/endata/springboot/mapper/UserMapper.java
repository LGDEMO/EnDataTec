package com.endata.springboot.mapper;

import com.endata.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User login(String userName);//用户登录
    public List<User> getAllUser();//获取所有用户列表
    String getCityName( int city_code);//根据城市代码查询出城市中文名字
}