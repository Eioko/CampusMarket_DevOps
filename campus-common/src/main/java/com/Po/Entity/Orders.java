package com.Po.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {
    /**
     * ID
     */
    private Integer id;
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
     * 购买时价格
     */
    private BigDecimal buyPrice;
    /**
     * 购买数量
     */
    private Integer buyNumber;
    /**
     * 交易状态
     */
    private Boolean tradeStatus;
    /**
     * 退款状态
     */
    private Boolean refundStatus;
    /**
     * 退款时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime refundTime;
    /**
     * 交易时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime tradeTime;
    /**
     * 退款是否已经确认(卖家进行的确认)
     */
    private Boolean isRefundConfirm;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }

    public Boolean getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(Boolean tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Boolean getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Boolean refundStatus) {
        this.refundStatus = refundStatus;
    }

    public LocalDateTime getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(LocalDateTime refundTime) {
        this.refundTime = refundTime;
    }

    public LocalDateTime getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(LocalDateTime tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Boolean getRefundConfirm() {
        return isRefundConfirm;
    }

    public void setRefundConfirm(Boolean refundConfirm) {
        isRefundConfirm = refundConfirm;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
