package com.Po.Vo;

import com.Po.Entity.Orders;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单的出参类VO
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrdersVO extends Orders {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userAvatar;
    /**
     * 商品标题
     */
    private String productTitle;
    /**
     * 商品图
     */
    private String productCover;

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

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductCover() {
        return productCover;
    }

    public void setProductCover(String productCover) {
        this.productCover = productCover;
    }
}
