package com.Po.Dto.query.sons;

import com.Po.Dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChatterQueryDto extends QueryDto {

    private Integer senderId;

    private Integer receiverId;

    private String content;

    public Integer getSenderId() {
        return senderId;
    }


    public Integer getReceiverId() {
        return receiverId;
    }


    public String getContent() {
        return content;
    }


    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }


    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }


    public void setContent(String content) {
        this.content = content;
    }
}
