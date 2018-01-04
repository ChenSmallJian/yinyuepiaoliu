package com.whut.yinyuepiaoliu.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component(value = "message")
public class Message {
    private Integer id;

    private Integer userId;

    private String messageType;

    private Integer messageId;

    private Integer messageStatus;

    private Date updateTime;

    private Date createTime;

    public Message(Integer id, Integer userId, String messageType, Integer messageId, Integer messageStatus, Date updateTime, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.messageType = messageType;
        this.messageId = messageId;
        this.messageStatus = messageStatus;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public Message() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType == null ? null : messageType.trim();
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Integer getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Integer messageStatus) {
        this.messageStatus = messageStatus;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}