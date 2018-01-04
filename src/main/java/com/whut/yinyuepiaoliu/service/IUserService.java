package com.whut.yinyuepiaoliu.service;

import com.whut.yinyuepiaoliu.common.ServerResponse;
import com.whut.yinyuepiaoliu.pojo.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * created by chenjian
 */
public interface IUserService {
    ServerResponse<String> checkRegister(String phone);

    ServerResponse<UserBase> login(String identifier, String credential,int identity_type);

    ServerResponse<String> getVerificationCode(String phone,int type, HttpSession session);

    ServerResponse<String> checkVerificationCode(String code, HttpSession session);

    ServerResponse<String> register(String phone, String password);

    ServerResponse forgetGetQuestion(String phone);

    ServerResponse forgetCheckAnswer(String phone,List<PwdAnswer> pwdAnswerList);

    ServerResponse<String> forgetResetPassword(String phone, String passwordNew, String forgetToken);

    ServerResponse<UserBase> updateUserInformation(UserBase userBaseUpdate);

    ServerResponse<UserBase> getUserInformation(Integer userId);

    ServerResponse getAllQuestion();

    ServerResponse<String> checkSetQuestion(String userId);

    ServerResponse<String> saveAnswer(List<PwdAnswer> pwdAnswerList, int userId);
}