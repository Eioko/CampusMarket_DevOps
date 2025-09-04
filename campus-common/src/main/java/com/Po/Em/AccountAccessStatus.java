package com.Po.Em;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户账户登录状态标识枚举
 * <p>
 * 定义系统用户账户的登录访问控制状态，影响用户能否成功登录系统
 */
@Getter
@AllArgsConstructor
public enum AccountAccessStatus {

    ACCESS_GRANTED(Boolean.FALSE, "可用登录") {
        @Override
        public String getStatusExplanation() {
            return "账户状态正常，用户可以进行登录操作";
        }
    },

    ACCESS_RESTRICTED(Boolean.TRUE, "登录受限") {
        @Override
        public String getStatusExplanation() {
            return "账户存在异常或限制条件，阻止用户完成登录";
        }
    };

    /**
     * 状态标识符 (与数据库存储值一致)
     * <p>
     * true: 禁止登录
     * false: 允许登录
     */
    private final Boolean flag;

    /**
     * 状态描述文本 (供管理界面显示)
     */
    private final String name;

    /**
     * 获取当前登录状态的详细解释
     *
     * @return 状态详细说明文本
     */
    public abstract String getStatusExplanation();

    /**
     * 通过状态标识符查找对应枚举项
     *
     * @param statusFlag 状态标识符 (true/false)
     * @return 匹配的登录状态枚举
     * @throws IllegalArgumentException 如果找不到匹配的状态
     */
    public static AccountAccessStatus resolveStatus(Boolean statusFlag) {
        for (AccountAccessStatus status : values()) {
            if (status.flag.equals(statusFlag)) {
                return status;
            }
        }
        throw new IllegalArgumentException("无效的登录状态标识: " + statusFlag);
    }

    /**
     * 检查当前状态是否允许登录
     *
     * @return true=允许登录, false=禁止登录
     */
    public boolean allowsLogin() {
        return !this.flag;
    }

    /**
     * 创建用户账户状态摘要
     *
     * @return 状态摘要文本 (e.g. "可用登录: 账户状态正常")
     */
    @Override
    public String toString() {
        return this.name + ": " + this.getStatusExplanation();
    }
}