package com.whut.yinyuepiaoliu.dao;

import com.whut.yinyuepiaoliu.pojo.PwdAnswer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PwdAnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PwdAnswer record);

    int insertSelective(PwdAnswer record);

    PwdAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PwdAnswer record);

    int updateByPrimaryKey(PwdAnswer record);

    int checkSetQuestion(@Param("userId")String userId);

    List<Integer> getQuestion(@Param("user_id") int user_id);

    int checkAnswer(@Param("user_id") int user_id, @Param("question_id") int question_id, @Param("answer") String answer);
}