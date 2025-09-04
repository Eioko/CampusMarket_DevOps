package com.Po.Dto.query.sons;

import com.Po.Dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 订单的查询条件类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrdersQueryDto extends QueryDto {
    /**
     * 订单号
     */
    private String code;
    /**
     * 备注
     */
    private String detail;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 商品ID
     */
    private Integer productId;
    /**
     * 交易状态
     */
    private Boolean tradeStatus;
    /**
     * 退款状态
     */
    private Boolean refundStatus;
    /**
     * 商品ID列表
     */
    private List<Integer> productIds;

    // Getter Methods
    public String getCode() {
        return code;
    }


    public String getDetail() {
        return detail;
    }


    public Integer getUserId() {
        return userId;
    }


    public Integer getProductId() {
        return productId;
    }


    public Boolean getTradeStatus() {
        return tradeStatus;
    }


    public Boolean getRefundStatus() {
        return refundStatus;
    }


    public List<Integer> getProductIds() {
        return productIds;
    }


    // Setter Methods
    public void setCode(String code) {
        this.code = code;
    }


    public void setDetail(String detail) {
        this.detail = detail;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public void setProductId(Integer productId) {
        this.productId = productId;
    }


    public void setTradeStatus(Boolean tradeStatus) {
        this.tradeStatus = tradeStatus;
    }


    public void setRefundStatus(Boolean refundStatus) {
        this.refundStatus = refundStatus;
    }


    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }
}
