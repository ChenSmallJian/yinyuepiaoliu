package com.whut.yinyuepiaoliu.dao;

import com.whut.yinyuepiaoliu.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component(value = "userMapper")
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    // 检查手机号是否注册
    int checkPhone(@Param("phone")String phone);

    int getUserId(@Param("phone")String phone);

    // 检查密码是否正确，正确则返回用户的信息
    User checkLogin(@Param("phone") String phone, @Param("password")String password);

    int updatePassword(@Param("phone")String phone, @Param("newPassword")String newPassword);
}