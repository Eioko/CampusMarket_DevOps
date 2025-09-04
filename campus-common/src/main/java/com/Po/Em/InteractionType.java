package com.Po.Em;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 用户内容互动行为类型枚举
 * <p>
 * 定义用户与系统内容(如商品、文章等)的交互方式类型
 */
@Getter
@AllArgsConstructor
public enum InteractionType {

    SAVE(1, "收藏") {
        @Override
        public String getActionDescription() {
            return "用户将内容添加到个人收藏夹";
        }
    },

    VIEW(2, "浏览") {
        @Override
        public String getActionDescription() {
            return "用户查看内容详情页";
        }
    },

    LOVE(3, "想要") {
        @Override
        public String getActionDescription() {
            return "用户表达对内容的获取意愿";
        }
    },

    NEW(4, "新增") {
        @Override
        public String getActionDescription() {
            return "用户创建新的系统内容";
        }
    };

    /**
     * 互动类型唯一标识符 (与数据库存储值一致)
     */
    private final Integer type;

    /**
     * 互动类型描述文本 (用于前端展示)
     */
    private final String detail;

    /**
     * 获取互动行为的详细描述
     *
     * @return 互动行为的完整描述文本
     */
    public abstract String getActionDescription();

    /**
     * 根据类型标识获取枚举实例
     *
     * @param type 类型标识符
     * @return 匹配的枚举实例
     * @throws IllegalArgumentException 如果找不到匹配的枚举类型
     */
    public static InteractionType fromType(Integer type) {
        for (InteractionType interaction : values()) {
            if (interaction.type.equals(type)) {
                return interaction;
            }
        }
        throw new IllegalArgumentException("无效的互动类型标识符: " + type);
    }

    /**
     * 获取所有互动类型的类型标识符数组
     *
     * @return 所有类型标识符组成的数组
     */
    public static Integer[] getAllTypes() {
        return Arrays.stream(values())
                .map(InteractionType::getType)
                .toArray(Integer[]::new);
    }
}