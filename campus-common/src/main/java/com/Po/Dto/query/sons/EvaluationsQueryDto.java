package com.Po.Dto.query.sons;

import com.Po.Dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 评论查询参数Dto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EvaluationsQueryDto extends QueryDto {

    /**
     * 内容类型
     */
    private String contentType;
    /**
     * 评论的内容
     */
    private String content;

    public String getContentType() {
        return contentType;
    }


    public String getContent() {
        return content;
    }


    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public void setContent(String content) {
        this.content = content;
    }
}
