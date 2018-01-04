package com.whut.yinyuepiaoliu.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component(value = "pwdQuestion")
public class PwdQuestion {
    private Integer id;

    private String question;

    private Date updateTime;

    private Date createTime;

    public PwdQuestion(Integer id, String question, Date updateTime, Date createTime) {
        this.id = id;
        this.question = question;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public PwdQuestion() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
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