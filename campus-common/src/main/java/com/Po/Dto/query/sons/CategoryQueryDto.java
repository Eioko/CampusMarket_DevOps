package com.Po.Dto.query.sons;

import com.Po.Dto.query.base.QueryDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品类别的查询条件类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryQueryDto extends QueryDto {
    /**
     * 分类名
     */
    private String name;
    /**
     * 是否启用
     */
    private Boolean isUse;

    public String getName() {
        return name;
    }

    public Boolean getUse() {
        return isUse;
    }  // 注意Boolean字段的特殊命名

    public void setName(String name) {
        this.name = name;
    }

    public void setUse(Boolean use) {
        isUse = use;
    }  // 注意Boolean字段的特殊命名
}
