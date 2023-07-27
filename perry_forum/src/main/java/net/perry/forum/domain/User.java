package net.perry.forum.domain;

import java.time.LocalDateTime;

/**
 * 开发者论坛 user类
 * 
 * @author Perry
 */

public class User {
    private int id;
    private String phone;
    private String pwd;
    private int sex;
    private String img;
    private LocalDateTime createTime;
    private int role;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", phone=" + phone + ", pwd=" + pwd + ", sex=" + sex + ", img=" + img
                + ", createTime=" + createTime + ", role=" + role + ", username=" + username + "]";
    }

}
