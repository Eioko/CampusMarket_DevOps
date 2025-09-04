package com.Po.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 父级评论
 */
@Data
public class CommentParentVO {

    /**
     * 评论ID
     */
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户头像
     */
    private String userAvatar;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 回复框显示状态
     */
    private Boolean showReplyInput;
    /**
     * 一共拥有的子级评论数
     */
    private Integer childTotal;
    /**
     * 用户是否已经点赞
     */
    private Boolean upvoteFlag;
    /**
     * 点赞列表
     */
    private String upvoteList;
    /**
     * 点赞量
     */
    private Integer upvoteCount;
    /**
     * 评论时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 子级评论
     */
    private List<CommentChildVO> commentChildVOS;

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
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


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public Boolean getShowReplyInput() {
        return showReplyInput;
    }


    public void setShowReplyInput(Boolean showReplyInput) {
        this.showReplyInput = showReplyInput;
    }


    public Integer getChildTotal() {
        return childTotal;
    }


    public void setChildTotal(Integer childTotal) {
        this.childTotal = childTotal;
    }


    public Boolean getUpvoteFlag() {
        return upvoteFlag;
    }


    public void setUpvoteFlag(Boolean upvoteFlag) {
        this.upvoteFlag = upvoteFlag;
    }


    public String getUpvoteList() {
        return upvoteList;
    }


    public void setUpvoteList(String upvoteList) {
        this.upvoteList = upvoteList;
    }


    public Integer getUpvoteCount() {
        return upvoteCount;
    }


    public void setUpvoteCount(Integer upvoteCount) {
        this.upvoteCount = upvoteCount;
    }


    public LocalDateTime getCreateTime() {
        return createTime;
    }


    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }


    public List<CommentChildVO> getCommentChildVOS() {
        return commentChildVOS;
    }


    public void setCommentChildVOS(List<CommentChildVO> commentChildVOS) {
        this.commentChildVOS = commentChildVOS;
    }
}
