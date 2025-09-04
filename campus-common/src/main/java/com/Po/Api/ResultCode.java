package com.Po.Api;

/**
 * 请求响应码
 */
public enum ResultCode {
    /**
     * 请求成功码
     */
    REQUEST_SUCCESS(200),
    /**
     * 请求失败码
     */
    REQUEST_ERROR(400),
    /**
     * 需要身份认证：表示需要提供有效的身份凭证
     */
    AUTHENTICATION_REQUIRED(401),

    /**
     * 禁止操作资源：表示认证成功但权限不足
     */
    ACCESS_DENIED(403),

    /**
     * 请求资源未找到：表示请求的目标资源不存在
     */
    RESOURCE_MISSING(404),

    /**
     * 服务器内部异常：表示服务器端发生意外错误
     */
    SYSTEM_FAILURE(500);

    private Integer code;

    @Override
    public String toString() {
        return "ResultCode{" +
                "code=" + code +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    /**
     * 检查是否成功状态（新添加）
     *
     * @return true 表示成功状态
     */
    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }

    /**
     * 检查是否为系统错误（新添加）
     *
     * @return true 表示系统错误
     */
    public boolean isServerError() {
        return code >= 500 && code < 600;
    }

    ResultCode(Integer code) {
        this.code = code;
    }
}
