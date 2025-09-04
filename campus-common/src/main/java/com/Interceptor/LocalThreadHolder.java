package com.Interceptor;

public class LocalThreadHolder {
    private static final ThreadLocal<UserContext> USER_HOLDER = new ThreadLocal<>();

    // 用户上下文值对象
    private static class UserContext {
        private final Integer userId;
        private final Integer userRole;

        public UserContext(Integer userId, Integer userRole) {
            this.userId = userId;
            this.userRole = userRole;
        }

        public Integer getUserId() {
            return userId;
        }

        public Integer getUserRole() {
            return userRole;
        }
    }

    /**
     * 设置用户信息
     *
     * @param userId   用户ID
     * @param userRole 用户角色
     */
    public static void setUserId(Integer userId, Integer userRole) {
        USER_HOLDER.set(new UserContext(userId, userRole));
    }

    /**
     * 取出用户ID
     *
     * @return 用户ID，未设置时返回null
     */
    public static Integer getUserId() {
        UserContext context = USER_HOLDER.get();
        return context != null ? context.getUserId() : null;
    }

    /**
     * 取出用户角色
     *
     * @return 用户角色，未设置时返回null
     */
    public static Integer getRoleId() {
        UserContext context = USER_HOLDER.get();
        return context != null ? context.getUserRole() : null;
    }

    /**
     * 清理线程资源
     */
    public static void clear() {
        USER_HOLDER.remove();
    }
}