package com.whut.yinyuepiaoliu.dao;

import com.whut.yinyuepiaoliu.pojo.PwdQuestion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PwdQuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PwdQuestion record);

    int insertSelective(PwdQuestion record);

    PwdQuestion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PwdQuestion record);

    int updateByPrimaryKey(PwdQuestion record);

    List<PwdQuestion> getAllQuestion();
}