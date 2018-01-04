package com.whut.yinyuepiaoliu.pojo.util;

import org.springframework.stereotype.Component;

@Component(value = "yzMessage")
public class YZMessage {
    private String randomNumber;

    // 发送短信的回执信息
    private String hzCode;

    // 短信明细信息
    private String mxCode;

    private String mxMessage;

    private String mxPhone;

    private String mxReceiveDate;

    private String mxSendDate;

    private Long mxSendStatus;

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getHzCode() {
        return hzCode;
    }

    public void setHzCode(String hzCode) {
        this.hzCode = hzCode;
    }

    public String getMxCode() {
        return mxCode;
    }

    public void setMxCode(String mxCode) {
        this.mxCode = mxCode;
    }

    public String getMxMessage() {
        return mxMessage;
    }

    public void setMxMessage(String mxMessage) {
        this.mxMessage = mxMessage;
    }

    public String getMxPhone() {
        return mxPhone;
    }

    public void setMxPhone(String mxPhone) {
        this.mxPhone = mxPhone;
    }

    public String getMxReceiveDate() {
        return mxReceiveDate;
    }

    public void setMxReceiveDate(String mxReceiveDate) {
        this.mxReceiveDate = mxReceiveDate;
    }

    public String getMxSendDate() {
        return mxSendDate;
    }

    public void setMxSendDate(String mxSendDate) {
        this.mxSendDate = mxSendDate;
    }

    public Long getMxSendStatus() {
        return mxSendStatus;
    }

    public void setMxSendStatus(Long mxSendStatus) {
        this.mxSendStatus = mxSendStatus;
    }
}