package net.perry.forum.domain;

import java.time.LocalDateTime;

/**
 * 开发者论坛 category类
 * 
 * @author Perry
 */

public class Category {
    private int id;
    private String name;
    private int weight;
    private LocalDateTime createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", weight=" + weight + ", createTime=" + createTime + "]";
    }

}
