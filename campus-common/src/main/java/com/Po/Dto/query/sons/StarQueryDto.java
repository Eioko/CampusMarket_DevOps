package com.Po.Dto.query.sons;

import com.Po.Dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 互动行为查询条件类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class StarQueryDto extends QueryDto {
    /**
     * 用户ID
     */
    private Integer user1Id;
    private Integer user2Id;

    // Setter Methods
    public void setUser1Id(Integer user1Id) {
        this.user1Id = user1Id;
    }

    public void setUser2Id(Integer user2Id) {
        this.user2Id = user2Id;
    }
}
