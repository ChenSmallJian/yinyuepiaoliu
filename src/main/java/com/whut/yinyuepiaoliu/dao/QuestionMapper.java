package com.whut.yinyuepiaoliu.dao;

import com.whut.yinyuepiaoliu.pojo.Question;
import org.springframework.stereotype.Component;

import java.util.List;
@Component(value = "questionMapper")
public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    List<Question> getAllQuestion();
}