package com.whut.yinyuepiaoliu.pojo;

import java.util.Date;

public class Question {
    private Integer id;

    private String question;

    private Date createTime;

    private Date updateTime;

    public Question(Integer id, String question, Date createTime, Date updateTime) {
        this.id = id;
        this.question = question;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Question() {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}