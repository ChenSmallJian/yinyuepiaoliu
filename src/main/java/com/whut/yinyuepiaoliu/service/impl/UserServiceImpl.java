package com.whut.yinyuepiaoliu.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.whut.yinyuepiaoliu.common.Const;
import com.whut.yinyuepiaoliu.common.ServerResponse;
import com.whut.yinyuepiaoliu.dao.AnswerMapper;
import com.whut.yinyuepiaoliu.dao.QuestionMapper;
import com.whut.yinyuepiaoliu.dao.UserMapper;
import com.whut.yinyuepiaoliu.pojo.Answer;
import com.whut.yinyuepiaoliu.pojo.Question;
import com.whut.yinyuepiaoliu.pojo.User;
import com.whut.yinyuepiaoliu.service.IUserService;
import com.whut.yinyuepiaoliu.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("iUserService")
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private User user;

    public ServerResponse<User> login(String phone, String password) {
        int resultCount = userMapper.checkPhone(phone);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage(Const.Message.NOT_REGISTER);
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        user = userMapper.checklogin(phone,md5Password);
        if(user == null){
            return ServerResponse.createByErrorMessage(Const.Message.ERROR_PASSWORD);
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(Const.Message.LOGIN_SUCCESS,user);
    }

    public ServerResponse<String> register(User user){
        int resultCount = userMapper.checkPhone(user.getPhone());
        if(resultCount > 0){
            return ServerResponse.createByErrorMessage(Const.Message.HAS_REGISTER);
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        // MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        // 设置默认头像
        if(user.getImage().equals(StringUtils.EMPTY)){
            user.setImage(Const.Default_info.DEFAULT_ICON);
        }
        // 设置默认签名
        if(user.getMotto().equals(StringUtils.EMPTY)){
            user.setMotto(Const.Default_info.DEFAULT_MOTTO);
        }

        resultCount = userMapper.insertSelective(user);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage(Const.Message.REGISTER_FAILED);
        }
        return ServerResponse.createBySuccessMessage(Const.Message.REGISTER_SUCCESS);
    }

    public ServerResponse forgetGetQuestion(String phone){
        int resultCount = userMapper.checkPhone(phone);
        if(resultCount == 0){
            return ServerResponse.createByErrorMessage(Const.Message.NOT_REGISTER);
        }
        List<Integer> questionNoList = Lists.newArrayList();
        questionNoList = answerMapper.getQuestion(phone);
        // 判断是否设置过密码问题
        if(questionNoList.size() == 0){
            return ServerResponse.createByErrorMessage(Const.Message.NOT_SET_QUESTION);
        }
        List<Question> questionList = Lists.newArrayList();
        for(Integer questionId : questionNoList){
            Question question = questionMapper.selectByPrimaryKey(questionId);
            questionList.add(question);
        }
        return ServerResponse.createBySuccessMessage(questionList);
    }

    public ServerResponse forgetCheckAnswer(List<Answer> answerList){
        Map<Integer,Boolean> resultMap = Maps.newHashMap();
        for(Answer answer : answerList){
            String ans = answerMapper.getAnswer(answer.getUserId(),answer.getQuestionId());
            if(ans.equals(answer.getAnswer())){
                resultMap.put(answer.getQuestionId(),true);
            }else{
                resultMap.put(answer.getQuestionId(),false);
            }
        }
        return ServerResponse.createBySuccessMessage(resultMap);
    }
}