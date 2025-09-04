package com.Po.Api;

import java.util.function.Consumer;

/**
 * API响应通用基类
 * <p>
 * 封装API调用的通用响应结构，包含响应状态码和消息
 *
 * @param <T> 响应负载数据类型（无负载时为Void）
 */
public class Result<T> {

    /**
     * API响应状态码
     */
    private Integer code;

    /**
     * API响应消息描述
     */
    private String msg;

    /**
     * 默认构造函数
     */
    public Result() {
    }

    /**
     * 带状态参数的构造函数
     *
     * @param code 响应状态码
     * @param msg 响应消息文本
     */
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 响应状态码访问器
     *
     * @return 当前响应状态码
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 响应状态码修改器
     *
     * @param code 要设置的响应状态码
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 响应消息访问器
     *
     * @return 当前响应消息文本
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 响应消息修改器
     *
     * @param msg 要设置的响应消息文本
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 检查是否成功响应
     *
     * @return true 当响应状态码在200-299范围内
     */
    public boolean isSuccess() {
        return code != null && code >= 200 && code < 300;
    }

    /**
     * 检查是否为客户端错误
     *
     * @return true 当响应状态码在400-499范围内
     */
    public boolean isClientError() {
        return code != null && code >= 400 && code < 500;
    }

    /**
     * 检查是否为服务端错误
     *
     * @return true 当响应状态码在500-599范围内
     */
    public boolean isServerError() {
        return code != null && code >= 500 && code < 600;
    }

    /**
     * 创建成功响应
     *
     * @param <T> 数据类型
     * @return 状态码200的成功响应
     */
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功");
    }

    /**
     * 创建带有消息的成功响应
     *
     * @param message 成功消息
     * @param <T> 数据类型
     * @return 状态码200的成功响应
     */
    public static <T> Result<T> success(String message) {
        return new Result<>(200, message);
    }

    /**
     * 创建错误响应
     *
     * @param code 错误状态码
     * @param message 错误消息
     * @param <T> 数据类型
     * @return 错误响应实例
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message);
    }

    /**
     * 如果响应成功，则执行操作
     *
     * @param action 要执行的操作
     * @return 当前结果实例（用于链式调用）
     */
    public Result<T> ifSuccess(Consumer<Result<T>> action) {
        if (isSuccess()) {
            action.accept(this);
        }
        return this;
    }

    /**
     * 如果响应失败，则执行操作
     *
     * @param action 要执行的操作
     * @return 当前结果实例（用于链式调用）
     */
    public Result<T> ifFailure(Consumer<Result<T>> action) {
        if (!isSuccess()) {
            action.accept(this);
        }
        return this;
    }

    /**
     * 获取标准化字符串表示
     *
     * @return 格式化的响应字符串
     */
    @Override
    public String toString() {
        return String.format(
                "Result{code=%d, msg='%s'}",
                code != null ? code : 0,
                msg != null ? msg : ""
        );
    }
}