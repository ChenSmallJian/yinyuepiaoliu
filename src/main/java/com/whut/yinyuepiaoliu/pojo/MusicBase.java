package com.whut.yinyuepiaoliu.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component(value = "musicBase")
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

    private Date musicRelease;

    private Date musicDuration;

    private Date updateTime;

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