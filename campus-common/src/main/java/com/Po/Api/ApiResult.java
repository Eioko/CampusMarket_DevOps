package com.Po.Api;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用API响应对象（保持原始方法签名）
 *
 * @param <T> 响应数据类型
 */
@Setter
@Getter
public class ApiResult<T> extends Result<T> {

    private T data;
    private Integer total;

    // 构造方法与原始版本保持一致
    public ApiResult(Integer code) {
        super(code, "操作成功");
    }

    public ApiResult(Integer code, String msg) {
        super(code, msg);
    }

    public ApiResult(Integer code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }

    public ApiResult(T data, Integer total) {
        this.data = data;
        this.total = total;
    }

    public ApiResult(Integer code, String msg, T data, Integer total) {
        super(code, msg);
        this.data = data;
        this.total = total;
    }

    public static <T> Result<T> success() {
        ApiResult<T> result = new ApiResult<>(ResultCode.REQUEST_SUCCESS.getCode());
        result.setData(null);
        return result;
    }

    public static <T> Result<T> success(T data) {
        ApiResult<T> result = new ApiResult<>(ResultCode.REQUEST_SUCCESS.getCode());
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(T data, Integer total) {
        ApiResult<T> result = new ApiResult<>(ResultCode.REQUEST_SUCCESS.getCode());
        result.setData(data);
        result.setTotal(total);
        return result;
    }

    public static <T> Result<T> success(String msg) {
        return new Result<>(ResultCode.REQUEST_SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> success(String msg, T data) {
        return new ApiResult<>(ResultCode.REQUEST_SUCCESS.getCode(), msg, data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<T>(ResultCode.REQUEST_ERROR.getCode(), msg);
    }

    /**
     * 错误响应（带数据）
     */
    public static <T> ApiResult<T> error(String msg, T data) {
        return new ApiResult<>(ResultCode.REQUEST_ERROR.getCode(), msg, data);
    }

    /**
     * 智能分页响应（自动计算总页数）
     */
    public static <T> ApiResult<T> pageResult(T data, long totalCount, int pageSize) {
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);
        return (ApiResult<T>) success(data, totalPages);
    }
}