package com.Po.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StarVo {
    private Integer id;
    private Integer user1Id;
    private Integer user2Id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Integer user1Id) {
        this.user1Id = user1Id;
    }

    public Integer getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(Integer user2Id) {
        this.user2Id = user2Id;
    }
}
