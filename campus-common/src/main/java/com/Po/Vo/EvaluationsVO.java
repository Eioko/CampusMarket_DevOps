package com.Po.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 评论VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationsVO {
    /**
     * 总数
     */
    private Integer count;
    /**
     * 评论数据
     */
    private List<CommentParentVO> data;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<CommentParentVO> getData() {
        return data;
    }

    public void setData(List<CommentParentVO> data) {
        this.data = data;
    }
}
