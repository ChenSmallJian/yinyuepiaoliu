package com.whut.yinyuepiaoliu.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component(value = "driftingSite")
public class DriftingSite {
    private Integer id;

    private String themeIdentifier;

    private Integer userId;

    private Integer musicId;

    private Integer sequenceNumber;

    private Date receiveTime;

    private Double evaluation;

    private String tags;

    private String comment;

    private Integer likeIt;

    private Integer collectIt;

    private Date updateTime;

    private Date createTime;

    public DriftingSite(Integer id, String themeIdentifier, Integer userId, Integer musicId, Integer sequenceNumber, Date receiveTime, Double evaluation, String tags, String comment, Integer likeIt, Integer collectIt, Date updateTime, Date createTime) {
        this.id = id;
        this.themeIdentifier = themeIdentifier;
        this.userId = userId;
        this.musicId = musicId;
        this.sequenceNumber = sequenceNumber;
        this.receiveTime = receiveTime;
        this.evaluation = evaluation;
        this.tags = tags;
        this.comment = comment;
        this.likeIt = likeIt;
        this.collectIt = collectIt;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public DriftingSite() {
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

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Double evaluation) {
        this.evaluation = evaluation;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Integer getLikeIt() {
        return likeIt;
    }

    public void setLikeIt(Integer likeIt) {
        this.likeIt = likeIt;
    }

    public Integer getCollectIt() {
        return collectIt;
    }

    public void setCollectIt(Integer collectIt) {
        this.collectIt = collectIt;
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