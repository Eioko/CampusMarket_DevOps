package com.Po.Vo;

import com.Po.Entity.OperationLog;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 操作日志出参类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OperationLogVO extends OperationLog {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户账号
     */
    private String userAccount;

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
}
