package com.Po.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 评论实体
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Evaluations {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 父级评论ID
     */
    private Integer parentId;

    /**
     * 评论者ID
     */
    private Integer commenterId;

    /**
     * 回复者ID
     */
    private Integer replierId;

    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 内容ID
     */
    private Integer contentId;

    /**
     * 点赞信息列表(以","分割)
     */
    private String upvoteList;

    /**
     * 评论时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public Integer getCommenterId() {
        return commenterId;
    }

    public Integer getReplierId() {
        return replierId;
    }

    public String getContentType() {
        return contentType;
    }

    public String getContent() {
        return content;
    }

    public Integer getContentId() {
        return contentId;
    }

    public String getUpvoteList() {
        return upvoteList;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    // ==================== Setter Methods ====================
    public void setId(Integer id) {
        this.id = id;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setCommenterId(Integer commenterId) {
        this.commenterId = commenterId;
    }

    public void setReplierId(Integer replierId) {
        this.replierId = replierId;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public void setUpvoteList(String upvoteList) {
        this.upvoteList = upvoteList;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
