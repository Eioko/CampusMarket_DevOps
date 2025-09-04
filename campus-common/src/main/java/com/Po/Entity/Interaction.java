package com.Po.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 互动行为实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Interaction {
    /**
     * ID
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 商品ID
     */
    private Integer productId;
    /**
     * 行为类型
     */
    private Integer type;
    /**
     * 互动时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    public Integer getId() {
        return this.id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public Integer getType() {
        return this.type;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
