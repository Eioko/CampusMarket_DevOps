package com.Po.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    /**
     * ID
     */
    private Integer id;
    /**
     * 商品名
     */
    private String name;
    /**
     * 简介
     */
    private String detail;
    /**
     * 商品封面列表
     */
    private String coverList;
    /**
     * 新旧程度
     */
    private Integer oldLevel;
    /**
     * 所属商品类别ID
     */
    private Integer categoryId;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 库存
     */
    private Integer inventory;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 是否支持砍价
     */
    private Boolean isBargain;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    private int status;

    /**
     * 获取商品ID
     * @return 商品ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置商品ID
     * @param id 商品ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商品名称
     * @return 商品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商品名称
     * @param name 商品名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取商品详情描述
     * @return 商品详情
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置商品详情描述
     * @param detail 商品详情
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 获取商品封面图片列表（JSON格式或逗号分隔）
     * @return 封面图片列表
     */
    public String getCoverList() {
        return coverList;
    }

    /**
     * 设置商品封面图片列表
     * @param coverList 封面图片列表（JSON格式或逗号分隔）
     */
    public void setCoverList(String coverList) {
        this.coverList = coverList;
    }

    /**
     * 获取商品新旧程度（1-10级）
     * @return 新旧程度
     */
    public Integer getOldLevel() {
        return oldLevel;
    }

    /**
     * 设置商品新旧程度
     * @param oldLevel 新旧程度（1-10级）
     */
    public void setOldLevel(Integer oldLevel) {
        this.oldLevel = oldLevel;
    }

    /**
     * 获取商品分类ID
     * @return 分类ID
     */
    public Integer getCategoryId() {
        return categoryId;
    }

    /**
     * 设置商品分类ID
     * @param categoryId 分类ID
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取所属用户ID
     * @return 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置所属用户ID
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取商品库存数量
     * @return 库存数量
     */
    public Integer getInventory() {
        return inventory;
    }

    /**
     * 设置商品库存数量
     * @param inventory 库存数量
     */
    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    /**
     * 获取商品价格
     * @return 商品价格
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 设置商品价格
     * @param price 商品价格
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 检查是否支持砍价
     * @return true支持砍价，false/null不支持
     */
    public Boolean getBargain() {
        return isBargain;
    }

    /**
     * 设置是否支持砍价
     * @param bargain true支持砍价，false不支持
     */
    public void setBargain(Boolean bargain) {
        isBargain = bargain;
    }

    /**
     * 获取商品创建时间
     * @return 创建时间
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 设置商品创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
