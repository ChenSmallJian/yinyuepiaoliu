package com.whut.yinyuepiaoliu.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component(value = "driftingTheme")
public class DriftingTheme {
    private Integer id;

    private String themeIdentifier;

    private Integer musicId;

    private Integer userId;

    private String driftingTheme;

    private String driftingPic;

    private String driftingPicBig;

    private Integer usersLimit;

    private Integer currentUsersNum;

    private Date departureTime;

    private Integer driftingStatus;

    private Integer praiseNum;

    private Integer readNum;

    private Integer collectNum;

    private Integer commentNum;

    private Integer forwardNum;

    private Date updateTime;

    private Date createTime;

    public DriftingTheme(Integer id, String themeIdentifier, Integer musicId, Integer userId, String driftingTheme, String driftingPic, String driftingPicBig, Integer usersLimit, Integer currentUsersNum, Date departureTime, Integer driftingStatus, Integer praiseNum, Integer readNum, Integer collectNum, Integer commentNum, Integer forwardNum, Date updateTime, Date createTime) {
        this.id = id;
        this.themeIdentifier = themeIdentifier;
        this.musicId = musicId;
        this.userId = userId;
        this.driftingTheme = driftingTheme;
        this.driftingPic = driftingPic;
        this.driftingPicBig = driftingPicBig;
        this.usersLimit = usersLimit;
        this.currentUsersNum = currentUsersNum;
        this.departureTime = departureTime;
        this.driftingStatus = driftingStatus;
        this.praiseNum = praiseNum;
        this.readNum = readNum;
        this.collectNum = collectNum;
        this.commentNum = commentNum;
        this.forwardNum = forwardNum;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public DriftingTheme() {
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

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDriftingTheme() {
        return driftingTheme;
    }

    public void setDriftingTheme(String driftingTheme) {
        this.driftingTheme = driftingTheme == null ? null : driftingTheme.trim();
    }

    public String getDriftingPic() {
        return driftingPic;
    }

    public void setDriftingPic(String driftingPic) {
        this.driftingPic = driftingPic == null ? null : driftingPic.trim();
    }

    public String getDriftingPicBig() {
        return driftingPicBig;
    }

    public void setDriftingPicBig(String driftingPicBig) {
        this.driftingPicBig = driftingPicBig == null ? null : driftingPicBig.trim();
    }

    public Integer getUsersLimit() {
        return usersLimit;
    }

    public void setUsersLimit(Integer usersLimit) {
        this.usersLimit = usersLimit;
    }

    public Integer getCurrentUsersNum() {
        return currentUsersNum;
    }

    public void setCurrentUsersNum(Integer currentUsersNum) {
        this.currentUsersNum = currentUsersNum;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getDriftingStatus() {
        return driftingStatus;
    }

    public void setDriftingStatus(Integer driftingStatus) {
        this.driftingStatus = driftingStatus;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(Integer forwardNum) {
        this.forwardNum = forwardNum;
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