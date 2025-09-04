package code_product.LayerMap;

import com.Po.Dto.query.sons.CategoryQueryDto;
import com.Po.Entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品类别持久化接口
 */
public interface LayerCategoryMapper {
    /**
     * 批量删除用户信息
     *
     * @param ids 用户ID集合
     */
    void batchDelete(@Param(value = "ids") List<Integer> ids);

    /**
     * 修改
     *
     * @param category 商品类别信息
     * @return int 受影响行数
     */
    int update(Category category);

    /**
     * 查询满足分页查询的记录总数
     *
     * @param categoryQueryDto 分页查询参数
     * @return int 数据总数
     */
    int queryCount(CategoryQueryDto categoryQueryDto);

    /**
     * 分页查询商品类别信息
     *
     * @param categoryQueryDto 分页查询参数
     * @return List<Category>
     */
    List<Category> query(CategoryQueryDto categoryQueryDto);

    /**
     * 新增
     *
     * @param category 实体信息
     * @return int 受影响行数
     */
    int save(Category category);
}
