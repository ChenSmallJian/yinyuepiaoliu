package com.whut.yinyuepiaoliu.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component(value = "userAuthorize")
public class UserAuthorize {
    private Integer id;

    private Integer userId;

    private Integer identityType;

    private String identifier;

    private String credential;

    private Date updateTime;

    private Date createTime;

    public UserAuthorize(Integer id, Integer userId, Integer identityType, String identifier, String credential, Date updateTime, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.identityType = identityType;
        this.identifier = identifier;
        this.credential = credential;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public UserAuthorize() {
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

    public Integer getIdentityType() {
        return identityType;
    }

    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier == null ? null : identifier.trim();
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential == null ? null : credential.trim();
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