package com.whut.yinyuepiaoliu.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String YZM_MESSAGE = "yzm_message";

    // 验证码过期时间，单位是分钟
    public static final int CODE_TIME_OUT =  2;

    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_asc", "price_desc");
    }

    // 登录时的授权类型：手机，qq，微信，微博，百度
    public interface Login_authorization{
        int LOGIN_FROM_PHONE = 0;
        int LOGIN_FROM_QQ = 1;
        int LOGIN_FROM_WEIXIN = 2;
        int LOGIN_FROM_WEIBO = 3;
        int LOGIN_FROM_BAIDU = 4;
    }

    // 默认头像，默认签名，默认昵称
    public interface Default_info{
        String DEFAULT_ICON = "default icon";
        String DEFAULT_MOTTO = "暂时没有签名";
        String DEFAULT_NICKNAME = "用户";
    }

    // 发送验证码的类型：注册和找回密码
    public interface VerificationCodeType{
        int REGISTER = 0;
        int FIND_PASSWORD = 1;
    }

    // 验证码返回的类型：OK表示发送成功，isv.BUSINESS_LIMIT_CONTROL表示发送次数过多
    public interface VerificationCodeResultType{
        String BUSINESS_LIMIT_CONTROL = "isv.BUSINESS_LIMIT_CONTROL";
        String OK = "OK";
    }

    // 用户角色
    public interface Role {
        int ROLE_CUSTOMER = 0; // 普通用户
        int ROLE_ADMIN = 1; // 管理员
    }

    // 返回的错误码类型
    public interface ErrorType{
        int ERROR_2 = 2;
    }

    public enum ProductStatusEnum {
        ON_SALE("在线", 1);
        private String value;
        private int code;

        ProductStatusEnum(String value, int code) {
            this.value = value;
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum OrderStatusEnum {
        CANCELED("已取消", 0),
        NO_PAY("未支付", 10),
        PAID("已付款", 20),
        SHIPPED("已发货", 40),
        ORDER_SUCCESS("订单完成", 50),
        ORDER_CLOSE("订单关闭", 60);

        OrderStatusEnum(String value, int code) {
            this.value = value;
            this.code = code;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static OrderStatusEnum codeOf(int code){
            for(OrderStatusEnum orderStatusEnum : values()){
                if(orderStatusEnum.getCode() == code){
                    return orderStatusEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }
    public interface AlipayCallback{
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }

    public enum PayPlatformEnum{
        ALIPAY("支付宝",1);
        PayPlatformEnum(String value, int code) {
            this.value = value;
            this.code = code;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }

    public enum PaymentTypeEnum{
        ONLINE_PAY("在线支付",1)
        ;
        PaymentTypeEnum(String value, int code) {
            this.value = value;
            this.code = code;
        }

        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }

        public static PaymentTypeEnum codeOf(int code){
            for(PaymentTypeEnum paymentTypeEnum : values()){
                if(paymentTypeEnum.getCode() == code){
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("没有找到对应的枚举");
        }
    }

    public interface Message{
        String NOT_REGISTER = "该手机号尚未注册";
        String ERROR_PASSWORD = "密码错误";
        String LOGIN_SUCCESS = "登录成功";
        String LOGIN_OUT_SUCCESS = "登出成功";
        String HAS_REGISTER = "该手机号已经注册";
        String REGISTER_FAILED = "注册失败";
        String REGISTER_SUCCESS = "注册成功";

        String BUSINESS_LIMIT_CONTROL = "距离上一次发送验证码时间过短";
        String SEND_CODE_SUCCESS = "发送验证码成功";
        String SEND_CODE_FAIL = "发送验证码失败";
        String CODE_EXPIRED = "验证码已经过期，请重新获取";
        String CODE_ERROR = "验证码错误";
        String CODE_SUCCESS = "验证码正确";

        String NEED_TOKEN = "参数错误，需要提交token";
        String TOKEN_EXPIRED = "token无效或者过期";
        String TOKEN_ERROR = "token错误，请重新获取重置密码的token";

        String RESET_PASSWORD_SUCCESS = "修改密码成功";
        String RESET_PASSWORD_FAIL = "修改密码失败";

        String NOT_LOGIN = "用户未登录";

        String UPDATE_USER_SUCCESS = "更新个人信息成功";
        String UPDATE_USER_FAIL = "更新个人信息失败";

        String GET_USER_INFORMATION_FAIL = "找不到当前的用户信息";

        String NOT_ADMIN = "不是管理员，无法登录";

        String HAS_SET_QUESTION = "已经设置密码提示问题";
        String NOT_SET_QUESTION = "没有设置密码提示问题";

        String SAVE_ANSWER_FAIL = "保存密码提示问题答案失败";
        String SAVE_ANSWER_SUCCESS = "保存密码提示问题成功";

        String IDENTITY_TYPE_ERROR = "授权类型有误";
    }
}