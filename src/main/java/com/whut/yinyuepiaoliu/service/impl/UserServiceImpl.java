package com.whut.yinyuepiaoliu.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.whut.yinyuepiaoliu.alimessage.SendSMS;
import com.whut.yinyuepiaoliu.common.Const;
import com.whut.yinyuepiaoliu.common.ResponseCode;
import com.whut.yinyuepiaoliu.common.ServerResponse;
import com.whut.yinyuepiaoliu.common.TokenCache;
import com.whut.yinyuepiaoliu.dao.AnswerMapper;
import com.whut.yinyuepiaoliu.dao.QuestionMapper;
import com.whut.yinyuepiaoliu.dao.UserMapper;
import com.whut.yinyuepiaoliu.pojo.Answer;
import com.whut.yinyuepiaoliu.pojo.Question;
import com.whut.yinyuepiaoliu.pojo.User;
import com.whut.yinyuepiaoliu.pojo.util.Message;
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
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private User user;

    @Autowired
    private SendSMS sendSMS;

    @Autowired
    private Message message;

    // 验证手机号是否已经注册
    public ServerResponse<String> checkRegister(String phone) {
        // 检查手机号是否已经注册
        int resultCount = userMapper.checkPhone(phone);
        if (resultCount == 0) {
            return ServerResponse.createBySuccessMessage(Const.Message.NOT_REGISTER); // 手机号尚未注册
        }
        return ServerResponse.createByErrorMessage(Const.Message.HAS_REGISTER); // 手机号已经注册
    }

    // 用户登录
    public ServerResponse<User> login(String phone, String password) {
        // 检查手机号是否已经注册
        int resultCount = userMapper.checkPhone(phone);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage(Const.Message.NOT_REGISTER); // 手机号尚未注册
        }
        // 能到这里，说明手机号已经注册，接下来就是检查对应的密码是否正确
        // 密码MD5加密
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        user = userMapper.checkLogin(phone, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(Const.ErrorType.ERROR_2, Const.Message.ERROR_PASSWORD); // 密码错误
        }
        // 将密码置为空，不返回给前端
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(Const.Message.LOGIN_SUCCESS, user);
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
        message = sendSMS.sendMessage(phone);
        switch (message.getHzCode()) {
            case Const.VerificationCodeResultType.OK:
                // 验证码发送成功，将验证码信息存入session
                session.removeAttribute("message");
                session.setAttribute("message", message);
                // 获取发送时间后的2分钟
                Date date = AddDateMinute.addDateMinut(message.getMxSendDate(), Const.CODE_TIME_OUT);
                Assert.notNull(date);
                // 设置两分钟后删除验证码
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        session.removeAttribute("message");
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
        message = (Message) session.getAttribute("message");
        if (message == null) {
            return ServerResponse.createByErrorMessage(Const.Message.CODE_EXPIRED);
        }
        if (message.getRandomNumber().equals(code)) {
            return ServerResponse.createBySuccessMessage(Const.Message.CODE_SUCCESS);
        } else {
            return ServerResponse.createByErrorMessage(Const.Message.CODE_ERROR);
        }
    }

    // 用户注册

    /**
     * 因为注册方式是通过手机号注册，所以在获取验证码的时候就会检查手机号是否已经注册
     * 所以在注册信息的时候，就不需要再检查
     */
    public ServerResponse<String> register(User user) {
//        int resultCount = userMapper.checkPhone(user.getPhone());
//        if(resultCount > 0){
//            return ServerResponse.createByErrorMessage(Const.Message.HAS_REGISTER);
//        }
        // 设置用户角色
        user.setRole(Const.Role.ROLE_CUSTOMER);
        // MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        // 设置默认头像
        if (StringUtils.isBlank(user.getImage())) {
            user.setImage(Const.Default_info.DEFAULT_ICON);
        }
        // 设置默认签名
        if (StringUtils.isBlank(user.getMotto())) {
            user.setMotto(Const.Default_info.DEFAULT_MOTTO);
        }

        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage(Const.Message.REGISTER_FAILED);
        }
        return ServerResponse.createBySuccessMessage(Const.Message.REGISTER_SUCCESS);
    }

    // 获取所有的密码提示问题
    public ServerResponse getAllQuestion() {
        List<Question> questionList = questionMapper.getAllQuestion();
        return ServerResponse.createBySuccessMessage(questionList);
    }

    // 验证用户是否已经设置密码提示问题
    public ServerResponse<String> checkSetQuestion(String userId) {
        int resultCount = answerMapper.checkSetQuestion(userId);
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage(Const.Message.HAS_SET_QUESTION);
        }
        return ServerResponse.createBySuccessMessage(Const.Message.NOT_SET_QUESTION);
    }

    // 保存用户设置的密码答案
    public ServerResponse<String> saveAnswer(List<Answer> answerList, int userId) {
        for (Answer answer : answerList) {
            if (userId != answer.getUserId()) {
                return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), ResponseCode.ILLEGAL_ARGUMENT.getDesc());
            }
            int resultCount = answerMapper.insert(answer);
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
        List<Integer> questionNoList = Lists.newArrayList();
        questionNoList = answerMapper.getQuestion(userId);  // 获取密码提示问题
        // 判断是否设置过密码问题
        if (questionNoList.size() == 0) {
            return ServerResponse.createByErrorMessage(Const.Message.NOT_SET_QUESTION); // 尚未设置密码提示问题
        }
        List<Question> questionList = Lists.newArrayList();
        for (Integer questionId : questionNoList) {
            Question question = questionMapper.selectByPrimaryKey(questionId);
            questionList.add(question);
        }
        Map<String, Object> resultMap = Maps.newHashMap();
        resultMap.put("userId", userId);
        resultMap.put("questionList", questionList);
        return ServerResponse.createBySuccessMessage(resultMap);
    }

    // 根据手机号获取用户的id
    private int getUserId(String phone) {
        // 该手机号必须是已经注册
        return userMapper.getUserId(phone);
    }

    // 校验密码提示问题
    public ServerResponse forgetCheckAnswer(String phone, List<Answer> answerList) {
        Map<Integer, Boolean> resultMap = Maps.newHashMap();
        Boolean flag = true;
        for (Answer answer : answerList) {
            int resultCount = answerMapper.checkAnswer(answer.getUserId(), answer.getQuestionId(), answer.getAnswer());
            if (resultCount > 0) {
                resultMap.put(answer.getQuestionId(), true);
            } else {
                flag = false;
                resultMap.put(answer.getQuestionId(), false);
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
            int resultCount = userMapper.updatePassword(phone, md5Password);
            if (resultCount > 0) {
                return ServerResponse.createBySuccessMessage(Const.Message.RESET_PASSWORD_SUCCESS);
            }
        } else {
            return ServerResponse.createByErrorMessage(Const.Message.TOKEN_ERROR);
        }
        return ServerResponse.createByErrorMessage(Const.Message.RESET_PASSWORD_FAIL);
    }

    // 更新用户信息
    public ServerResponse<User> updateUserInformation(User userUpdate) {
        int resultCount = userMapper.updateByPrimaryKeySelective(userUpdate);
        if (resultCount > 0) {
            user = userMapper.selectByPrimaryKey(userUpdate.getId());
            user.setPassword(StringUtils.EMPTY);
            return ServerResponse.createBySuccess(Const.Message.UPDATE_USER_SUCCESS, user);
        }
        return ServerResponse.createByErrorMessage(Const.Message.UPDATE_USER_FAIL);
    }

    // 获取用户个人信息
    public ServerResponse<User> getUserInformation(Integer userId) {
        user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ServerResponse.createByErrorMessage(Const.Message.GET_USER_INFORMATION_FAIL);
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccessMessage(user);
    }
}