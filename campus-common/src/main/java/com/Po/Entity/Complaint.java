package com.Po.Entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Complaint {
    private Integer id;
    private Integer complainantId; // 投诉人ID
    private Integer targetId; // 被投诉人ID
    private Integer orderId; // 关联订单ID
    private Integer productId; // 关联订单ID
    private String content; // 投诉内容
    private String status; // 状态: pending/resolved/rejected
    private Date createTime;
    private Date handleTime;

    public Integer getId() {
        return id;
    }

    public Integer getComplainantId() {
        return complainantId;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public String getContent() {
        return content;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setComplainantId(Integer complainantId) {
        this.complainantId = complainantId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }
}
