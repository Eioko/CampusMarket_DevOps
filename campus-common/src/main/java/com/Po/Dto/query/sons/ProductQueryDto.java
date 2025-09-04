package com.Po.Dto.query.sons;

import com.Po.Dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品的查询条件类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductQueryDto extends QueryDto {
    /**
     * 商品名
     */
    private String name;
    /**
     * 所属商品类别ID
     */
    private Integer categoryId;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 是否支持砍价
     */
    private Boolean isBargain;
    /**
     * 价格区间
     */
    private Integer priceMin;
    /**
     * 价格区间
     */
    private Integer priceMax;
    /**
     * 推荐条目
     */
    private Integer recommendLimit;

    // Getter Methods
    public String getName() {
        return name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Boolean getBargain() {
        return isBargain;
    }

    public Integer getPriceMin() {
        return priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public Integer getRecommendLimit() {
        return recommendLimit;
    }

    // Setter Methods
    public void setName(String name) {
        this.name = name;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setBargain(Boolean bargain) {
        isBargain = bargain;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
    }

    public void setPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
    }

    public void setRecommendLimit(Integer recommendLimit) {
        this.recommendLimit = recommendLimit;
    }
}
