package com.whut.yinyuepiaoliu.dao;

import com.whut.yinyuepiaoliu.pojo.DriftingMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface DriftingMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DriftingMessage record);

    int insertSelective(DriftingMessage record);

    DriftingMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DriftingMessage record);

    int updateByPrimaryKey(DriftingMessage record);
}