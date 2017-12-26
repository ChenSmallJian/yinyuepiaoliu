package com.whut.yinyuepiaoliu.service;

import com.whut.yinyuepiaoliu.common.ServerResponse;
import com.whut.yinyuepiaoliu.pojo.Answer;
import com.whut.yinyuepiaoliu.pojo.Question;
import com.whut.yinyuepiaoliu.pojo.User;

import java.util.List;

/**
 * created by chenjian
 */
public interface IUserService {
    ServerResponse<User> login(String phone, String password);

    public ServerResponse<String> register(User user);

    public ServerResponse forgetGetQuestion(String phone);

    public ServerResponse forgetCheckAnswer(List<Answer> answerList);
}