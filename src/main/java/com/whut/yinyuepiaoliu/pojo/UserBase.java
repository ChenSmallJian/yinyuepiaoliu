package com.whut.yinyuepiaoliu.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserBase {
    private Integer id;

    private String nickname;

    private String avatar;

    private String motto;

    private String phone;

    private Integer sex;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  // 返回前端时，以这种格式返回，否则返回的是时间戳，也就是一串数字
    @DateTimeFormat(pattern="yyyy-MM-dd") //接收参数的时候，以这种格式接收
    private Date birth;

    private String constellation;

    private String email;

    private String province;

    private String city;

    private String area;

    private Integer role;

    private String qq;

    private String weixin;

    private String weibo;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    public UserBase(Integer id, String nickname, String avatar, String motto, String phone, Integer sex, Date birth, String constellation, String email, String province, String city, String area, Integer role, String qq, String weixin, String weibo, Date updateTime, Date createTime) {
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
        this.motto = motto;
        this.phone = phone;
        this.sex = sex;
        this.birth = birth;
        this.constellation = constellation;
        this.email = email;
        this.province = province;
        this.city = city;
        this.area = area;
        this.role = role;
        this.qq = qq;
        this.weixin = weixin;
        this.weibo = weibo;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public UserBase() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getMotto() {
        return motto;
    }

    public void setMotto(String motto) {
        this.motto = motto == null ? null : motto.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation == null ? null : constellation.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo == null ? null : weibo.trim();
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

    public String toString() {
        return "UserBase{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", motto='" + motto + '\'' +
                ", phone='" + phone + '\'' +
                ", sex=" + sex +
                ", birth=" + birth +
                ", constellation='" + constellation + '\'' +
                ", email='" + email + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", role=" + role +
                ", qq='" + qq + '\'' +
                ", weixin='" + weixin + '\'' +
                ", weibo='" + weibo + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}