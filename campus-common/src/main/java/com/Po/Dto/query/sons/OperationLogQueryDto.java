package com.Po.Dto.query.sons;

import com.Po.Dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 操作日志查询条件类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OperationLogQueryDto extends QueryDto {
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 描述
     */
    private String detail;

    public Integer getUserId() {
        return userId;
    }


    public String getDetail() {
        return detail;
    }


    // Setter Methods
    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public void setDetail(String detail) {
        this.detail = detail;
    }
}
