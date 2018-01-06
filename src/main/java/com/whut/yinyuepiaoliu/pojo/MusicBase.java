package com.whut.yinyuepiaoliu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MusicBase {
    private Integer id;

    private Integer sourceType;

    private Integer musicSourceId;

    private String musicAddress;

    private String musicName;

    private String musicPic;

    private String musicPicBig;

    private String musicLyrics;

    private String musicType;

    private String musicAuthor;

    private String musicAlbum;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") // 返回前端时，以这种格式返回，否则返回的是时间戳，也就是一串数字
    @DateTimeFormat(pattern="yyyy-MM-dd") //接收参数的时候，以这种格式接收
    private Date musicRelease;

    @JsonFormat(pattern="mm:ss",timezone = "GMT+8")
    private Date musicDuration;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public MusicBase(Integer id, Integer sourceType, Integer musicSourceId, String musicAddress, String musicName, String musicPic, String musicPicBig, String musicLyrics, String musicType, String musicAuthor, String musicAlbum, Date musicRelease, Date musicDuration, Date updateTime, Date createTime) {
        this.id = id;
        this.sourceType = sourceType;
        this.musicSourceId = musicSourceId;
        this.musicAddress = musicAddress;
        this.musicName = musicName;
        this.musicPic = musicPic;
        this.musicPicBig = musicPicBig;
        this.musicLyrics = musicLyrics;
        this.musicType = musicType;
        this.musicAuthor = musicAuthor;
        this.musicAlbum = musicAlbum;
        this.musicRelease = musicRelease;
        this.musicDuration = musicDuration;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public MusicBase() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getMusicSourceId() {
        return musicSourceId;
    }

    public void setMusicSourceId(Integer musicSourceId) {
        this.musicSourceId = musicSourceId;
    }

    public String getMusicAddress() {
        return musicAddress;
    }

    public void setMusicAddress(String musicAddress) {
        this.musicAddress = musicAddress == null ? null : musicAddress.trim();
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName == null ? null : musicName.trim();
    }

    public String getMusicPic() {
        return musicPic;
    }

    public void setMusicPic(String musicPic) {
        this.musicPic = musicPic == null ? null : musicPic.trim();
    }

    public String getMusicPicBig() {
        return musicPicBig;
    }

    public void setMusicPicBig(String musicPicBig) {
        this.musicPicBig = musicPicBig == null ? null : musicPicBig.trim();
    }

    public String getMusicLyrics() {
        return musicLyrics;
    }

    public void setMusicLyrics(String musicLyrics) {
        this.musicLyrics = musicLyrics == null ? null : musicLyrics.trim();
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType == null ? null : musicType.trim();
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor == null ? null : musicAuthor.trim();
    }

    public String getMusicAlbum() {
        return musicAlbum;
    }

    public void setMusicAlbum(String musicAlbum) {
        this.musicAlbum = musicAlbum == null ? null : musicAlbum.trim();
    }

    public Date getMusicRelease() {
        return musicRelease;
    }

    public void setMusicRelease(Date musicRelease) {
        this.musicRelease = musicRelease;
    }

    public Date getMusicDuration() {
        return musicDuration;
    }

    public void setMusicDuration(Date musicDuration) {
        this.musicDuration = musicDuration;
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