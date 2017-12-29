package com.whut.yinyuepiaoliu.dao;

import com.whut.yinyuepiaoliu.pojo.Answer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component(value = "answerMapper")
public interface AnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);

    List<Integer> getQuestion(@Param("user_id") int user_id);

    int checkAnswer(@Param("user_id") int user_id, @Param("question_id") int question_id, @Param("answer") String answer);

    int checkSetQuestion(@Param("userId")String userId);
}