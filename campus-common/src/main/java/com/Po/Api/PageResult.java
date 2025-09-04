package com.Po.Api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Iterator;
import java.util.Optional;
import java.util.function.Function;

/**
 * 分页查询结果封装
 * <p>
 * 用于返回带分页信息的数据结果集，包含数据集合和总记录数
 *
 * @param <T> 数据类型
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult<T> extends Result<T> {

    /**
     * 分页数据集合
     */
    private T data;

    /**
     * 符合条件的总记录数
     */
    private Integer total;

    /**
     * 响应状态码构造器
     *
     * @param code HTTP响应状态码
     */
    public PageResult(Integer code) {
        super(code, "查询成功");
    }

    /**
     * 构建分页成功响应
     *
     * @param <T> 数据泛型类型
     * @param data 数据集合
     * @param total 总记录数
     * @return 封装好的分页结果对象
     */
    public static <T> PageResult<T> of(T data, Integer total) {
        PageResult<T> result = new PageResult<>(ResultCode.REQUEST_SUCCESS.getCode());
        result.setData(data);
        result.setTotal(total);
        return result;
    }

    /**
     * 获取有效总记录数（处理空值情况）
     *
     * @return 总记录数（如果没有则为0）
     */
    public int getEffectiveTotal() {
        return Optional.ofNullable(total).orElse(0);
    }


    /**
     * 迭代器映射处理（辅助方法）
     */
    private <E, R> Iterable<R> mapIterable(Iterable<E> iterable,
                                           Function<? super E, ? extends R> mapper) {
        return () -> new Iterator<R>() {
            private final Iterator<E> originalIterator = iterable.iterator();

            @Override
            public boolean hasNext() {
                return originalIterator.hasNext();
            }

            @Override
            public R next() {
                return mapper.apply(originalIterator.next());
            }
        };
    }

    /**
     * 检查是否包含有效数据
     *
     * @return true 表示有数据（数据非空且总记录数大于0）
     */
    public boolean hasData() {
        return data != null && getEffectiveTotal() > 0;
    }

    /**
     * 判断数据是否为空
     *
     * @return true 表示无数据（数据为空或总记录数为0）
     */
    public boolean isEmpty() {
        return !hasData();
    }
}