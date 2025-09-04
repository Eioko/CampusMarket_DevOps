package com.Po.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 互动消息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chatter {
    /**
     * ID
     */
    private Integer id;
    /**
     * 发者ID
     */
    private Integer senderId;
    /**
     * 收者id
     */
    private Integer receiverId;
    /**
     * 消息体
     */
    private String content;
    /**
     * 是否已读
     */
    private Boolean isRead;
    /**
     * 发送时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy年MM月dd日 HH:mm:ss")
    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public String getContent() {
        return content;
    }

    // 注意：Boolean类型字段的特殊命名处理
    public Boolean getRead() {
        return isRead;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    // ==================== Setter Methods ====================
    public void setId(Integer id) {
        this.id = id;
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

    public void setRead(Boolean read) {
        isRead = read;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
