package com.whut.yinyuepiaoliu.service;

import com.whut.yinyuepiaoliu.common.ServerResponse;
import com.whut.yinyuepiaoliu.pojo.Answer;
import com.whut.yinyuepiaoliu.pojo.Question;
import com.whut.yinyuepiaoliu.pojo.User;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * created by chenjian
 */
public interface IUserService {
    ServerResponse<String> checkRegister(String phone);

    ServerResponse<User> login(String phone, String password);

    ServerResponse<String> getVerificationCode(String phone,int type, HttpSession session);

    ServerResponse<String> checkVerificationCode(String code, HttpSession session);

    ServerResponse<String> register(User user);

    ServerResponse forgetGetQuestion(String phone);

    ServerResponse forgetCheckAnswer(String phone,List<Answer> answerList);

    ServerResponse<String> forgetResetPassword(String phone, String passwordNew, String forgetToken);

    ServerResponse<User> updateUserInformation(User userUpdate);

    ServerResponse<User> getUserInformation(Integer userId);

    ServerResponse getAllQuestion();

    ServerResponse<String> checkSetQuestion(String userId);

    ServerResponse<String> saveAnswer(List<Answer> answerList, int userId);
}