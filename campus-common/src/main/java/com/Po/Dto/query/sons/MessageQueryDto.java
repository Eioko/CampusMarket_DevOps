package com.Po.Dto.query.sons;

import com.Po.Dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息的查询条件Dto类
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MessageQueryDto extends QueryDto {

    private Integer userId;

    private String content;

    public Integer getUserId() {
        return userId;
    }


    public String getContent() {
        return content;
    }


    // Setter Methods
    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public void setContent(String content) {
        this.content = content;
    }
}
