package com.Po.Em;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户发言权限状态枚举
 * <p>
 * 定义用户在平台中的言论表达权限状态，影响用户能否在平台中发布内容
 */
@Getter
@AllArgsConstructor
public enum SpeechPermissionStatus {

    SPEECH_ALLOWED(false, "可用") {
        @Override
        public String getStatusDescription() {
            return "用户拥有正常的发言权限，可以自由发布内容";
        }
    },

    SPEECH_PROHIBITED(true, "禁言状态") {
        @Override
        public String getStatusDescription() {
            return "用户发言权限被限制，无法在平台中发布内容";
        }
    };

    /**
     * 状态标识符 (数据库存储值)
     * <p>
     * true - 表示发言被禁止
     * false - 表示允许发言
     */
    private final Boolean flag;

    /**
     * 状态显示名称 (用户界面展示)
     */
    private final String name;

    /**
     * 获取当前状态的详细描述
     *
     * @return 状态的详细解释文本
     */
    public abstract String getStatusDescription();

    /**
     * 通过标识符查找对应的状态枚举
     *
     * @param flag 状态标识符
     * @return 匹配的状态枚举
     * @throws IllegalArgumentException 当标识符无效时抛出
     */
    public static SpeechPermissionStatus resolveByFlag(boolean flag) {
        for (SpeechPermissionStatus status : values()) {
            if (status.flag.equals(flag)) {
                return status;
            }
        }
        throw new IllegalArgumentException("无效的状态标识: " + flag);
    }

    /**
     * 检查用户当前是否被禁止发言
     *
     * @return true 表示用户被禁言
     */
    public boolean isMuted() {
        return this.flag;
    }

    /**
     * 生成状态摘要信息
     *
     * @return 包含状态名称和描述的摘要
     */
    @Override
    public String toString() {
        return this.name + ": " + this.getStatusDescription();
    }
}