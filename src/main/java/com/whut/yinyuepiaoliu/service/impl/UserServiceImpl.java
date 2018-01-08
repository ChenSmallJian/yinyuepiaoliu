package com.whut.yinyuepiaoliu.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.whut.yinyuepiaoliu.alimessage.SendSMS;
import com.whut.yinyuepiaoliu.common.Const;
import com.whut.yinyuepiaoliu.common.ResponseCode;
import com.whut.yinyuepiaoliu.common.ServerResponse;
import com.whut.yinyuepiaoliu.common.TokenCache;
import com.whut.yinyuepiaoliu.dao.*;
import com.whut.yinyuepiaoliu.pojo.*;
import com.whut.yinyuepiaoliu.pojo.util.YZMessage;
import com.whut.yinyuepiaoliu.service.IUserService;
import com.whut.yinyuepiaoliu.util.AddDateMinute;
import com.whut.yinyuepiaoliu.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpSession;
import java.util.*;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserBaseMapper userBaseMapper;

    @Autowired
    private UserAuthorize userAuthorize;

    @Autowired
    private UserBase userBase;

    @Autowired
    private UserAuthorizeMapper userAuthorizeMapper;

    @Autowired
    private PwdQuestionMapper pwdQuestionMapper;

    @Autowired
    private PwdAnswerMapper pwdAnswerMapper;

    @Autowired
    private SendSMS sendSMS;

    @Autowired
    private YZMessage yzMessage;

    // 验证手机号是否已经注册
    public ServerResponse<String> checkRegister(String phone) {
        // 检查手机号是否已经注册
        int resultCount = userBaseMapper.checkPhone(phone);
        if (resultCount == 0) {
            return ServerResponse.createBySuccessMessage(Const.Message.NOT_REGISTER); // 手机号尚未注册
        }
        return ServerResponse.createByErrorMessage(Const.Message.HAS_REGISTER); // 手机号已经注册
    }

    // 用户登录
    public ServerResponse<UserBase> login(String identifier, String credential,int identity_type) {
        switch(identity_type){
            // 如果是通过手机号登录
            case Const.Login_authorization.LOGIN_FROM_PHONE:
                // 检查手机号是否已经注册
                int resultCount = userBaseMapper.checkPhone(identifier);
                if (resultCount == 0) {
                    return ServerResponse.createByErrorMessage(Const.Message.NOT_REGISTER); // 手机号尚未注册
                }
                // 能到这里，说明手机号已经注册，接下来就是检查对应的密码是否正确
                // 密码MD5加密
                String md5Password = MD5Util.MD5EncodeUtf8(credential);
                userAuthorize = userAuthorizeMapper.checkLogin(identifier,md5Password);
                if (userAuthorize == null) {
                    return ServerResponse.createByErrorCodeMessage(Const.ErrorType.ERROR_2, Const.Message.ERROR_PASSWORD); // 密码错误
                }
                // 到此处，说明登录成功，需要将用户信息返回给前端
                return ServerResponse.createBySuccess(Const.Message.LOGIN_SUCCESS, userBaseMapper.selectByPrimaryKey(userAuthorize.getUserId()));
            // TODO: 1/5/18 第三方登录：在登录时查询，若不存在，则注册，否则就是已经注册
            case Const.Login_authorization.LOGIN_FROM_QQ:
                return null;
            case Const.Login_authorization.LOGIN_FROM_WEIBO:
                return null;
            case Const.Login_authorization.LOGIN_FROM_WEIXIN:
                return null;
            case Const.Login_authorization.LOGIN_FROM_BAIDU:
                return null;
            default:
                // 若到此则说明授权类型有误
                return ServerResponse.createByErrorMessage(Const.Message.IDENTITY_TYPE_ERROR);
        }
    }

    // 发送验证码
    public ServerResponse<String> getVerificationCode(String phone, int type, HttpSession session) {
        ServerResponse<String> validResponse = this.checkRegister(phone);
        switch (type) {
            case Const.VerificationCodeType.REGISTER: // 注册时，要求手机号必须没有注册，否则返回手机号已经被注册的错
                if (!validResponse.isSuccess())
                    return validResponse;
                break;
            case Const.VerificationCodeType.FIND_PASSWORD: // 找回密码时，要求手机号必须已经注册，否则返回手机号尚未注册的错
                if (validResponse.isSuccess())
                    return validResponse;
                break;
            default:
                break;
        }
        // 发送验证码
        yzMessage = sendSMS.sendMessage(phone);
        switch (yzMessage.getHzCode()) {
            case Const.VerificationCodeResultType.OK:
                // 验证码发送成功，将验证码信息存入session
                session.removeAttribute(Const.YZM_MESSAGE);
                session.setAttribute(Const.YZM_MESSAGE, yzMessage);
                // 获取发送时间后的2分钟
                Date date = AddDateMinute.addDateMinut(yzMessage.getMxSendDate(), Const.CODE_TIME_OUT);
                Assert.notNull(date);
                // 设置两分钟后删除验证码
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        session.removeAttribute(Const.YZM_MESSAGE);
                    }
                }, date);
                return ServerResponse.createBySuccessMessage(Const.Message.SEND_CODE_SUCCESS);
            case Const.VerificationCodeResultType.BUSINESS_LIMIT_CONTROL:
                return ServerResponse.createByErrorMessage(Const.Message.BUSINESS_LIMIT_CONTROL);
            default:
                return ServerResponse.createByErrorMessage(Const.Message.SEND_CODE_FAIL);
        }
    }

    // 验证验证码是否过期
    public ServerResponse<String> checkVerificationCode(String code, HttpSession session) {
        yzMessage = (YZMessage) session.getAttribute(Const.YZM_MESSAGE);
        if (yzMessage == null) {
            return ServerResponse.createByErrorMessage(Const.Message.CODE_EXPIRED);
        }
        if (yzMessage.getRandomNumber().equals(code)) {
            return ServerResponse.createBySuccessMessage(Const.Message.CODE_SUCCESS);
        } else {
            return ServerResponse.createByErrorCodeMessage(Const.ErrorType.ERROR_2,Const.Message.CODE_ERROR);
        }
    }

    // 用户注册
    public ServerResponse<String> register(String phone,String password) {
        int resultCount = userBaseMapper.checkPhone(phone);
        if(resultCount > 0){
            return ServerResponse.createByErrorMessage(Const.Message.HAS_REGISTER);
        }
        userBase.setPhone(phone);
        // 设置默认信息
        userBase.setNickname(Const.Default_info.DEFAULT_NICKNAME+phone);
        userBase.setAvatar(Const.Default_info.DEFAULT_ICON);
        userBase.setMotto(Const.Default_info.DEFAULT_MOTTO);
        // 设置用户角色
        userBase.setRole(Const.Role.ROLE_CUSTOMER);
        // 将注册信息插入数据库
        resultCount = userBaseMapper.insertAndGetId(userBase);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage(Const.Message.REGISTER_FAILED);
        }
        // 获取刚刚插入的记录的id
        int user_id = userBase.getId();
        // 组装用户授权信息表的信息
        userAuthorize.setUserId(user_id);
        userAuthorize.setIdentityType(Const.Login_authorization.LOGIN_FROM_PHONE);
        userAuthorize.setIdentifier(phone);
        // MD5加密
        userAuthorize.setCredential(MD5Util.MD5EncodeUtf8(password));
        // 将授权信息插入数据库
        resultCount = userAuthorizeMapper.insert(userAuthorize);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage(Const.Message.REGISTER_FAILED);
        }
        return ServerResponse.createBySuccessMessage(Const.Message.REGISTER_SUCCESS);
    }

    // 获取所有的密码提示问题
    public ServerResponse getAllQuestion() {
        List<PwdQuestion> pwdQuestionList = pwdQuestionMapper.getAllQuestion();
        return ServerResponse.createBySuccessMessage(pwdQuestionList);
    }

    // 验证用户是否已经设置密码提示问题
    public ServerResponse<String> checkSetQuestion(String userId) {
        int resultCount = pwdAnswerMapper.checkSetQuestion(userId);
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage(Const.Message.HAS_SET_QUESTION);
        }
        return ServerResponse.createBySuccessMessage(Const.Message.NOT_SET_QUESTION);
    }

    // 保存用户设置的密码答案
    public ServerResponse<String> saveAnswer(List<PwdAnswer> pwdAnswerList, int userId) {
        for (PwdAnswer pwdAnswer : pwdAnswerList) {
            if (userId != pwdAnswer.getUserId()) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
            }
            int resultCount = pwdAnswerMapper.insert(pwdAnswer);
            if (resultCount == 0) {
                return ServerResponse.createByErrorMessage(Const.Message.SAVE_ANSWER_FAIL);
            }
        }
        return ServerResponse.createBySuccessMessage(Const.Message.SAVE_ANSWER_SUCCESS);
    }

    // 获取密码提示问题
    public ServerResponse forgetGetQuestion(String phone) {
        ServerResponse<String> validResponse = this.checkRegister(phone);
        if (validResponse.isSuccess()) { // 手机号尚未注册
            return ServerResponse.createByErrorMessage(Const.Message.NOT_REGISTER);
        }
        // 获取用户的id
        int userId = this.getUserId(phone);
        List<Integer> questionNoList = pwdAnswerMapper.getQuestion(userId);  // 获取密码提示问题
        // 判断是否设置过密码问题(这里为什么不用上面写好的判断方法呢？因为上面仅仅是判断，这里还需要返回question_id)
        if (questionNoList.size() == 0) {
            return ServerResponse.createByErrorMessage(Const.Message.NOT_SET_QUESTION); // 尚未设置密码提示问题
        }
        List<PwdQuestion> pwdQuestionList = Lists.newArrayList();
        for (Integer questionId : questionNoList) {
            PwdQuestion pwdQuestion = pwdQuestionMapper.selectByPrimaryKey(questionId);
            pwdQuestionList.add(pwdQuestion);
        }
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("userId", userId);
        resultMap.put("questionList", pwdQuestionList);
        return ServerResponse.createBySuccessMessage(resultMap);
    }

    // 根据手机号获取用户的id
    private int getUserId(String phone) {
        // 该手机号必须是已经注册
        return userBaseMapper.getUserId(phone);
    }

    // 校验密码提示问题
    public ServerResponse forgetCheckAnswer(String phone,List<PwdAnswer> pwdAnswerList) {
        Map<Integer, Boolean> resultMap = Maps.newHashMap();
        Boolean flag = true;
        for (PwdAnswer pwdAnswer : pwdAnswerList) {
            int resultCount = pwdAnswerMapper.checkAnswer(pwdAnswer.getUserId(), pwdAnswer.getQuestionId(), pwdAnswer.getAnswer());
            if (resultCount > 0) {
                resultMap.put(pwdAnswer.getQuestionId(), true);
            } else {
                flag = false;
                resultMap.put(pwdAnswer.getQuestionId(), false);
            }
        }
        if (flag) {
            // 若用户的答案都是正确的，则生成Guava缓存
            String forgetToken = UUID.randomUUID().toString();

            TokenCache.setKey(TokenCache.TOKEN_PREFIX + phone, forgetToken);

            return ServerResponse.createBySuccessMessage(forgetToken);
        }
        return ServerResponse.createByErrorData(resultMap);
    }

    // 重置密码
    public ServerResponse<String> forgetResetPassword(String phone, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage(Const.Message.NEED_TOKEN);
        }
        ServerResponse<String> validResponse = this.checkRegister(phone);
        if (validResponse.isSuccess()) { // 手机号尚未注册
            return validResponse;
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + phone);
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage(Const.Message.TOKEN_EXPIRED);
        }
        if (StringUtils.equals(forgetToken, token)) {
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int resultCount = userAuthorizeMapper.updatePassword(phone, md5Password);
            if (resultCount > 0) {
                return ServerResponse.createBySuccessMessage(Const.Message.RESET_PASSWORD_SUCCESS);
            }
        } else {
            return ServerResponse.createByErrorMessage(Const.Message.TOKEN_ERROR);
        }
        return ServerResponse.createByErrorMessage(Const.Message.RESET_PASSWORD_FAIL);
    }

    // 更新用户信息
    public ServerResponse<UserBase> updateUserInformation(UserBase userBaseUpdate) {
        int resultCount = userBaseMapper.updateByPrimaryKeySelective(userBaseUpdate);
        if (resultCount > 0) {
            userBase = userBaseMapper.selectByPrimaryKey(userBaseUpdate.getId());
            return ServerResponse.createBySuccess(Const.Message.UPDATE_USER_SUCCESS, userBase);
        }
        return ServerResponse.createByErrorMessage(Const.Message.UPDATE_USER_FAIL);
    }

    // 获取用户个人信息
    public ServerResponse<UserBase> getUserInformation(Integer userId) {
        userBase = userBaseMapper.selectByPrimaryKey(userId);
        if (userBase == null) {
            return ServerResponse.createByErrorMessage(Const.Message.GET_USER_INFORMATION_FAIL);
        }
        return ServerResponse.createBySuccessMessage(userBase);
    }
}