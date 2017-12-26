package com.whut.yinyuepiaoliu.controller.protal;

import com.whut.yinyuepiaoliu.common.Const;
import com.whut.yinyuepiaoliu.common.ResponseCode;
import com.whut.yinyuepiaoliu.common.ServerResponse;
import com.whut.yinyuepiaoliu.pojo.Answer;
import com.whut.yinyuepiaoliu.pojo.User;
import com.whut.yinyuepiaoliu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * created by chenjian
 */
@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private User user;

    /**
     * 用户登录
     *
     * @param phone
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> login(String phone, String password, HttpSession session) {
        ServerResponse<User> response = iUserService.login(phone, password);
        if (response.isSuccess()) {
            // 登录成功，则将用户信息放入session
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "login_out.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<String> loginOut(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccessMessage(Const.Message.LOGIN_OUT_SUCCESS);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(User user) {
        return iUserService.register(user);
    }

    /**
     * 获取用户信息
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getUserInfo(HttpSession session) {
        user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccessMessage(user);
        }
        return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
    }

    /**
     * 获取密码提示问题
     * @param phone
     * @return
     */
    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse forgetGetQuestion(String phone) {
        return iUserService.forgetGetQuestion(phone);
    }

    /**
     * 获取密码提示问题的答案
     * @param answerList
     * @return
     */
    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse forgetCheckAnswer(List<Answer> answerList){
        return iUserService.forgetCheckAnswer(answerList);
    }
}