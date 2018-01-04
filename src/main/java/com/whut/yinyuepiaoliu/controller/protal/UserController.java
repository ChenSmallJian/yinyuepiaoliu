package com.whut.yinyuepiaoliu.controller.protal;

import com.whut.yinyuepiaoliu.common.Const;
import com.whut.yinyuepiaoliu.common.ResponseCode;
import com.whut.yinyuepiaoliu.common.ServerResponse;
import com.whut.yinyuepiaoliu.pojo.PwdAnswer;
import com.whut.yinyuepiaoliu.pojo.UserBase;
import com.whut.yinyuepiaoliu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    private UserBase userBase;

    /**
     * 验证手机号是否已经注册
     *
     * @param phone
     * @return 尚未注册，返回成功
     * 已经注册，返回错误
     */
    @RequestMapping(value = "check_register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkRegister(String phone) {
        return iUserService.checkRegister(phone);
    }

    /**
     * 用户登录
     *
     * @param identifier    识别码
     * @param credential    凭据
     * @param identity_type 授权类型
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<UserBase> login(@RequestParam("identifier") String identifier,
                                      @RequestParam("credential") String credential,
                                      @RequestParam("identity_type") int identity_type,
                                      HttpSession session) {
        ServerResponse<UserBase> response = iUserService.login(identifier, credential, identity_type);
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
    @RequestMapping(value = "login_out.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> loginOut(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccessMessage(Const.Message.LOGIN_OUT_SUCCESS);
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @param type    // 0表示注册，1表示找回密码
     * @param session
     * @return
     */
    @RequestMapping(value = "get_verification_code.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> getVerificationCode(String phone, int type, HttpSession session) {
        return iUserService.getVerificationCode(phone, type, session);
    }

    /**
     * 校对验证码
     *
     * @param code
     * @param session
     * @return
     */
    @RequestMapping(value = "check_verification_code.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkVerificationCode(String code, HttpSession session) {
        return iUserService.checkVerificationCode(code, session);
    }

    /**
     * 用户注册
     *
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(String phone, String password) {
        return iUserService.register(phone, password);
    }

    /**
     * 获取用户信息
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<UserBase> getUserInfo(HttpSession session) {
        userBase = (UserBase) session.getAttribute(Const.CURRENT_USER);
        if (userBase == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return ServerResponse.createBySuccessMessage(userBase);
    }

    /**
     * 获取密码提示问题
     *
     * @return
     */
    @RequestMapping(value = "get_all_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getAllQuestion() {
        return iUserService.getAllQuestion();
    }

    /**
     * 查询用户是否设置密码提示问题
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "check_set_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkSetQuestion(String userId) {
        return iUserService.checkSetQuestion(userId);
    }

    /**
     * 保存密码提示问题的答案
     *
     * @param pwdAnswerList
     * @param session
     * @return
     */
    @RequestMapping(value = "save_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> saveAnswer(@RequestBody List<PwdAnswer> pwdAnswerList, HttpSession session) {
        userBase = (UserBase) session.getAttribute(Const.CURRENT_USER);
        if (userBase == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iUserService.saveAnswer(pwdAnswerList, userBase.getId());
    }

    /**
     * 获取用户密码提示问题
     *
     * @param phone
     * @return
     */
    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse forgetGetQuestion(String phone) {
        return iUserService.forgetGetQuestion(phone);
    }

    /**
     * 检查密码提示问题的答案
     *
     * @param pwdAnswerList
     * @param session
     * @return
     */
    @RequestMapping(value = "forget_check_answer.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse forgetCheckAnswer(@RequestBody List<PwdAnswer> pwdAnswerList, HttpSession session) {
        userBase = (UserBase) session.getAttribute(Const.CURRENT_USER);
        if (userBase == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iUserService.forgetCheckAnswer(userBase.getPhone(), pwdAnswerList);
    }

    /**
     * 重置密码
     *
     * @param phone
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @RequestMapping(value = "forget_reset_password.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetResetPassword(String phone, String passwordNew, String forgetToken) {
        return iUserService.forgetResetPassword(phone, passwordNew, forgetToken);
    }

    /**
     * 更新个人信息
     *
     * @param userBaseUpdate
     * @param session
     * @return
     */
    @RequestMapping(value = "update_user_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<UserBase> updateUserInformation(UserBase userBaseUpdate, HttpSession session) {
        userBase = (UserBase) session.getAttribute(Const.CURRENT_USER);
        if (userBase == null) {
            return ServerResponse.createByErrorMessage(Const.Message.NOT_LOGIN);
        }
        // 防止横向越权的问题
        userBaseUpdate.setId(userBase.getId());
        userBaseUpdate.setPhone(userBase.getPhone());
        ServerResponse<UserBase> response = iUserService.updateUserInformation(userBaseUpdate);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    /**
     * 获取用户信息
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "get_user_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<UserBase> getUserInformation(HttpSession session) {
        userBase = (UserBase) session.getAttribute(Const.CURRENT_USER);
        if (userBase == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), ResponseCode.NEED_LOGIN.getDesc());
        }
        return iUserService.getUserInformation(userBase.getId());
    }

    public ServerResponse upload(MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("upload");
        return null;
    }
}