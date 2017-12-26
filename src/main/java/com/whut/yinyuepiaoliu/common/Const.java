package com.whut.yinyuepiaoliu.common;

import com.google.common.collect.Sets;

import java.util.Set;

public class Const {
    public static final String CURRENT_USER = "currentUser";

    public interface ProductListOrderBy {
        Set<String> PRICE_ASC_DESC = Sets.newHashSet("price_asc", "price_desc");
    }

    public interface Default_info{
        String DEFAULT_ICON = "default icon";
        String DEFAULT_MOTTO = "暂时没有签名";
    }

    public interface Role {
        int ROLE_CUSTOMER = 0; // 普通用户
        int ROLE_ADMIN = 1; // 管理员
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
        String NOT_SET_QUESTION = "没有设置密码问题";
    }
}