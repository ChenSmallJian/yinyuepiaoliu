package com.whut.yinyuepiaoliu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DriftingMessage {
    private Integer id;

    private String themeIdentifier;

    private Integer userId;

    private Integer musicId;

    private Integer driftingType;

    private String driftingCondition;

    private Integer messageStatus;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public DriftingMessage(Integer id, String themeIdentifier, Integer userId, Integer musicId, Integer driftingType, String driftingCondition, Integer messageStatus, Date updateTime, Date createTime) {
        this.id = id;
        this.themeIdentifier = themeIdentifier;
        this.userId = userId;
        this.musicId = musicId;
        this.driftingType = driftingType;
        this.driftingCondition = driftingCondition;
        this.messageStatus = messageStatus;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public DriftingMessage() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getThemeIdentifier() {
        return themeIdentifier;
    }

    public void setThemeIdentifier(String themeIdentifier) {
        this.themeIdentifier = themeIdentifier == null ? null : themeIdentifier.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public Integer getDriftingType() {
        return driftingType;
    }

    public void setDriftingType(Integer driftingType) {
        this.driftingType = driftingType;
    }

    public String getDriftingCondition() {
        return driftingCondition;
    }

    public void setDriftingCondition(String driftingCondition) {
        this.driftingCondition = driftingCondition == null ? null : driftingCondition.trim();
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