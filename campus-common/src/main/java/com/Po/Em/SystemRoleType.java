package com.Po.Em;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 系统用户角色类型定义
 * <p>
 * 描述系统中不同权限级别的用户角色类型，用于权限控制及访问管理
 */
@Getter
@AllArgsConstructor
public enum SystemRoleType {

    SYSTEM_ADMINISTRATOR(1, "管理员") {
        @Override
        public boolean hasAdminPrivileges() {
            return true;
        }
    },

    REGISTERED_USER(2, "用户") {
        @Override
        public boolean hasAdminPrivileges() {
            return false;
        }
    },

    CONTENT_MODERATOR(3, "内容审核员") {
        @Override
        public boolean hasAdminPrivileges() {
            return true;
        }
    };

    /**
     * 角色唯一标识代码 (用于存储及权限判断)
     */
    private final Integer role;

    /**
     * 角色显示名称 (用于界面展示)
     */
    private final String displayName;

    /**
     * 检查角色是否具有管理员权限
     *
     * @return true表示有管理员权限
     */
    public abstract boolean hasAdminPrivileges();

    /**
     * 获取所有角色代码数组
     *
     * @return 所有角色代码组成的数组
     */
    public static Integer[] getAllRoleCodes() {
        return Arrays.stream(values())
                .map(SystemRoleType::getRole)
                .toArray(Integer[]::new);
    }

    /**
     * 通过角色代码查找角色显示名称
     *
     * @param roleCode 角色代码
     * @return 对应的角色显示名称，未找到时返回"未知角色"
     */
    public static String resolveDisplayName(Integer roleCode) {
        return Arrays.stream(values())
                .filter(role -> role.role.equals(roleCode))
                .findFirst()
                .map(SystemRoleType::getDisplayName)
                .orElse("未知角色");
    }

    /**
     * 通过角色代码获取对应的枚举项
     *
     * @param roleCode 角色代码
     * @return 对应的角色枚举
     * @throws IllegalArgumentException 当代码无效时抛出
     */
    public static SystemRoleType fromCode(Integer roleCode) {
        for (SystemRoleType role : values()) {
            if (role.role.equals(roleCode)) {
                return role;
            }
        }
        throw new IllegalArgumentException("无效的角色代码: " + roleCode);
    }
}