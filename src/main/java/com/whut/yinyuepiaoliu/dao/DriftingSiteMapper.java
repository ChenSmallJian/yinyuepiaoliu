package com.whut.yinyuepiaoliu.dao;

import com.whut.yinyuepiaoliu.pojo.DriftingSite;
import org.springframework.stereotype.Component;

@Component(value = "driftingSiteMapper")
public interface DriftingSiteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DriftingSite record);

    int insertSelective(DriftingSite record);

    DriftingSite selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DriftingSite record);

    int updateByPrimaryKey(DriftingSite record);
}