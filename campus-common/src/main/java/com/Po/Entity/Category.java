package com.Po.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品类别实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    /**
     * ID
     */
    private Integer id;
    /**
     * 类别名
     */
    private String name;
    /**
     * 是否启用
     */
    private Boolean isUse;

    public Integer getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Boolean getIsUse(){
        return  isUse;
    }
}
