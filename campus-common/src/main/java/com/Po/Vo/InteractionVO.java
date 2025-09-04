package com.Po.Vo;

import com.Po.Entity.Interaction;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 互动行为信息VO类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class InteractionVO extends Interaction {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 商品标题
     */
    private String productTitle;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
