package net.perry.forum.domain;

import java.time.LocalDateTime;

/**
 * 开发者论坛 reply类
 * 
 * @author Perry
 */

public class Reply {
    private int id;
    private int tId;
    private int floor;
    private String content;
    private int userId;
    private String username;
    private String userImg;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private int delete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "Reply [id=" + id + ", tId=" + tId + ", floor=" + floor + ", content=" + content + ", userId=" + userId
                + ", username=" + username + ", userImg=" + userImg + ", createTime=" + createTime + ", updateTime="
                + updateTime + ", delete=" + delete + "]";
    }

}
