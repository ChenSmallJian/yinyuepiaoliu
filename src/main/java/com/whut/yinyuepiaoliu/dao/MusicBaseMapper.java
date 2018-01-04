package com.whut.yinyuepiaoliu.dao;

import com.whut.yinyuepiaoliu.pojo.MusicBase;
import org.springframework.stereotype.Component;

@Component(value = "musicBaseMapper")
public interface MusicBaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MusicBase record);

    int insertSelective(MusicBase record);

    MusicBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MusicBase record);

    int updateByPrimaryKey(MusicBase record);
}