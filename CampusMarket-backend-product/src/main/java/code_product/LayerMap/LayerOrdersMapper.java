package code_product.LayerMap;

import com.Po.Dto.query.sons.OrdersQueryDto;
import com.Po.Entity.Orders;
import com.Po.Vo.OrdersVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单持久化接口
 */
public interface LayerOrdersMapper {

    /**
     * 新增
     *
     * @param orders 实体信息
     * @return int 受影响行数
     */
    int save(Orders orders);

    /**
     * 分页查询订单信息
     *
     * @param ordersQueryDto 分页查询参数
     * @return List<OrdersVO>
     */
    List<OrdersVO> query(OrdersQueryDto ordersQueryDto);

    /**
     * 查询满足分页查询的记录总数
     *
     * @param ordersQueryDto 分页查询参数
     * @return int 数据总数
     */
    int queryCount(OrdersQueryDto ordersQueryDto);

    /**
     * 修改
     *
     * @param orders 商品类别信息
     * @return int 受影响行数
     */
    int update(Orders orders);

    /**
     * 批量删除订单信息
     *
     * @param ids 用户ID集合
     */
    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<OrdersVO> queryByProductIds(OrdersQueryDto ordersQueryDto);

}
