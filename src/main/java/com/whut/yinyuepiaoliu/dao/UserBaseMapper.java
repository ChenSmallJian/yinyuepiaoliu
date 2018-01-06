package com.whut.yinyuepiaoliu.dao;

import com.whut.yinyuepiaoliu.pojo.UserBase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBase record);

    int insertAndGetId(UserBase record);

    int insertSelective(UserBase record);

    UserBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBase record);

    int updateByPrimaryKey(UserBase record);

    // 检查手机号是否注册
    int checkPhone(@Param("phone")String phone);

    // 根据手机号获取用户id
    int getUserId(@Param("phone")String phone);
}