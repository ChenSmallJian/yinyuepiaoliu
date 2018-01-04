package com.whut.yinyuepiaoliu.controller.backend;

import com.whut.yinyuepiaoliu.common.Const;
import com.whut.yinyuepiaoliu.common.ServerResponse;
import com.whut.yinyuepiaoliu.pojo.UserBase;
import com.whut.yinyuepiaoliu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/user")
public class UserManageController {
    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserBase userBase;

    /**
     * 管理员登录
     *
     * @param phone
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<UserBase> login(String phone, String password, HttpSession session) {
        ServerResponse<UserBase> response = iUserService.login(phone, password, Const.Login_authorization.LOGIN_FROM_PHONE);
        if (response.isSuccess()) {
            userBase = response.getData();
            if (userBase.getRole() == Const.Role.ROLE_ADMIN) {
                session.setAttribute(Const.CURRENT_USER, userBase);
                return response;
            } else {
                return ServerResponse.createByErrorMessage(Const.Message.NOT_ADMIN);
            }
        }
        return response;
    }
}