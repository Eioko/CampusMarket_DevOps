package com.Po.Dto.update;

import com.Po.Entity.Orders;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单的DTO类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrdersDTO extends Orders {
    /**
     * 下单多少件
     */
    private Integer buyNumber;

    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
}
