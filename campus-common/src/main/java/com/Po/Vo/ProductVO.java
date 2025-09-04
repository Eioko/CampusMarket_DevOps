package com.Po.Vo;

import com.Po.Entity.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品的出参VO类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductVO extends Product {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userAvatar;
    /**
     * 商品类别名
     */
    private String categoryName;
    /**
     * 想要人数
     */
    private Integer likeNumber;
    /**
     * 收藏人数
     */
    private Integer saveNumber;
    /**
     * 浏览人数
     */
    private Integer viewNumber;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getSaveNumber() {
        return saveNumber;
    }

    public void setSaveNumber(Integer saveNumber) {
        this.saveNumber = saveNumber;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }
}
