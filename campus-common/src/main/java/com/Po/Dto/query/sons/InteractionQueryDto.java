package com.Po.Dto.query.sons;

import com.Po.Dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 互动行为查询条件类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InteractionQueryDto extends QueryDto {

    private Integer userId;

    private Integer productId;

    private Integer type;

    // Getter Methods
    public Integer getUserId() {
        return userId;
    }


    public Integer getProductId() {
        return productId;
    }


    public Integer getType() {
        return type;
    }


    // Setter Methods
    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public void setProductId(Integer productId) {
        this.productId = productId;
    }


    public void setType(Integer type) {
        this.type = type;
    }
}
