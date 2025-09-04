package com.Po.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentChildVO {
    /**
     * 评论ID
     */
    private Integer id;
    /**
     * 父级评论ID
     */
    private Integer parentId;
    /**
     * 评论者ID
     */
    private Integer userId;
    /**
     * 评论者用户名
     */
    private String userName;
    /**
     * 评论者用户头像
     */
    private String userAvatar;

    /**
     * 被回复者ID
     */
    private Integer replierId;

    /**
     * 被回复者用户名
     */
    private String replierName;

    /**
     * 被回复者头像
     */
    private String replierAvatar;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论回复状态
     */
    private Boolean replyInputStatus;

    /**
     * 点赞列表
     */
    private String upvoteList;

    /**
     * 用户是否已经点赞
     */
    private Boolean upvoteFlag;

    /**
     * 点赞量
     */
    private Integer upvoteCount;

    /**
     * 举报量
     */
    private Integer reportsNum;
    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 评论时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getParentId() {
        return parentId;
    }


    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserAvatar() {
        return userAvatar;
    }


    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }


    public Integer getReplierId() {
        return replierId;
    }


    public void setReplierId(Integer replierId) {
        this.replierId = replierId;
    }


    public String getReplierName() {
        return replierName;
    }


    public void setReplierName(String replierName) {
        this.replierName = replierName;
    }


    public String getReplierAvatar() {
        return replierAvatar;
    }


    public void setReplierAvatar(String replierAvatar) {
        this.replierAvatar = replierAvatar;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public Boolean getReplyInputStatus() {
        return replyInputStatus;
    }


    public void setReplyInputStatus(Boolean replyInputStatus) {
        this.replyInputStatus = replyInputStatus;
    }


    public String getUpvoteList() {
        return upvoteList;
    }


    public void setUpvoteList(String upvoteList) {
        this.upvoteList = upvoteList;
    }


    public Boolean getUpvoteFlag() {
        return upvoteFlag;
    }


    public void setUpvoteFlag(Boolean upvoteFlag) {
        this.upvoteFlag = upvoteFlag;
    }


    public Integer getUpvoteCount() {
        return upvoteCount;
    }


    public void setUpvoteCount(Integer upvoteCount) {
        this.upvoteCount = upvoteCount;
    }


    public Integer getReportsNum() {
        return reportsNum;
    }


    public void setReportsNum(Integer reportsNum) {
        this.reportsNum = reportsNum;
    }


    public String getContentType() {
        return contentType;
    }


    public void setContentType(String contentType) {
        this.contentType = contentType;
    }


    public LocalDateTime getCreateTime() {
        return createTime;
    }


    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
