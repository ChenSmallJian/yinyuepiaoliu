package com.whut.yinyuepiaoliu.dao;

import com.whut.yinyuepiaoliu.pojo.DriftingTheme;
import org.springframework.stereotype.Component;

@Component(value = "driftingThemeMapper")
public interface DriftingThemeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DriftingTheme record);

    int insertSelective(DriftingTheme record);

    DriftingTheme selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DriftingTheme record);

    int updateByPrimaryKey(DriftingTheme record);
}