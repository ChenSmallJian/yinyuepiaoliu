package com.whut.yinyuepiaoliu.dao;

import com.whut.yinyuepiaoliu.pojo.Answer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);

    List<Integer> getQuestion(String phone);

    String getAnswer(@Param("user_id") int user_id,@Param("question_id")  int question_id);
}