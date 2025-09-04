package com.Po.Dto.query.base;

import lombok.*;

import java.time.LocalDateTime;

/**
 * 查询参数接收实体类基类，含有五项基础参数，使用时可以拓展
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class QueryDto {
    /**
     * ID
     */
    private Integer id;
    /**
     * 当前页
     */
    private Integer current;
    /**
     * 页面数据大小
     */
    private Integer size;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    public Integer getId() {
        return id;

    }

    public Integer getCurrent() {
        return current;
    }

    public Integer getSize() {
        return size;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
