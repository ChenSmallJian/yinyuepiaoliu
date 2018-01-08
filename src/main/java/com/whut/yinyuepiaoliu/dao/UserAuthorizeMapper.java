package com.whut.yinyuepiaoliu.dao;

import com.whut.yinyuepiaoliu.pojo.UserAuthorize;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorizeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuthorize record);

    int insertSelective(UserAuthorize record);

    UserAuthorize selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAuthorize record);

    int updateByPrimaryKey(UserAuthorize record);

    // 检查密码是否正确，正确则返回用户的信息
    UserAuthorize checkLogin(@Param("identifier") String identifier, @Param("credential")String credential);

    // 修改密码
    int updatePassword(@Param("phone")String phone, @Param("newPassword")String newPassword);

    // 获取密码
    UserAuthorize getPassword(@Param("phone")String phone);
}