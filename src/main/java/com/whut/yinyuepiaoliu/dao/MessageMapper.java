package com.whut.yinyuepiaoliu.dao;

import com.whut.yinyuepiaoliu.pojo.Message;
import org.springframework.stereotype.Component;

@Component(value = "messageMapper")
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
}