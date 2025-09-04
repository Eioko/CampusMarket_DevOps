package code_product.LayerService.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;

import com.Interceptor.LocalThreadHolder;
import org.springframework.stereotype.Service;

import code_product.LayerMap.LayerInteractionMapper;
import code_product.LayerMap.LayerOrdersMapper;
import code_product.LayerMap.LayerProductMapper;
import com.Po.Api.ApiResult;
import com.Po.Api.Result;
import com.Po.Dto.query.sons.ProductQueryDto;
import com.Po.Dto.update.OrdersDTO;
import com.Po.Em.InteractionType;
import com.Po.Entity.Interaction;
import com.Po.Entity.Orders;
import com.Po.Entity.Product;
import com.Po.Vo.ChartVO;
import com.Po.Vo.ProductVO;
import code_product.LayerService.ProductService;
import code_product.LayerService.InteractionService;

/**
 * 商品服务实现
 */
@Service
public class LayerProductServiceImpl implements ProductService {

    @Resource
    private LayerProductMapper productDataAccess;

    @Resource
    private LayerOrdersMapper orderDataAccess;

    @Resource
    private LayerInteractionMapper interactionDataAccess;

    @Resource
    private InteractionService interactionManager;

    // ==================== 商品管理操作 ====================

    @Override
    public Result<String> save(Product item) {
        Integer currentUser = LocalThreadHolder.getUserId();
        item.setUserId(currentUser);
        item.setCreateTime(LocalDateTime.now());
        productDataAccess.save(item);
        interactionManager.newProduct(item.getId());
        return ApiResult.success("商品添加成功");
    }

    @Override
    public Result<String> update(Product item) {
        productDataAccess.update(item);
        return ApiResult.success("商品更新完成");
    }

    @Override
    public Result<String> batchDelete(List<Integer> idList) {
        productDataAccess.batchDelete(idList);
        return ApiResult.success("商品删除操作成功");
    }

    // ==================== 商品查询操作 ====================

    @Override
    public Result<List<ProductVO>> query(ProductQueryDto queryParams) {
        int recordCount = productDataAccess.queryCount(queryParams);
        List<ProductVO> items = productDataAccess.query(queryParams);
        return ApiResult.success(items, recordCount);
    }

    @Override
    public Result<List<ChartVO>> queryProductInfo(ProductQueryDto queryParams) {
        Integer currentUser = LocalThreadHolder.getUserId();
        queryParams.setUserId(currentUser);
        List<Integer> itemIds = productDataAccess.queryProductIds(currentUser);

        if (itemIds.isEmpty()) {
            return ApiResult.success(new ArrayList<>());
        }

        List<Interaction> interactions = interactionDataAccess.queryByProductIds(itemIds);
        List<ChartVO> stats = new ArrayList<>();

        stats.add(new ChartVO("被浏览", countInteractions(interactions, InteractionType.VIEW.getType())));
        stats.add(new ChartVO("被收藏", countInteractions(interactions, InteractionType.SAVE.getType())));
        stats.add(new ChartVO("被想要", countInteractions(interactions, InteractionType.LOVE.getType())));

        return ApiResult.success(stats);
    }

    // ==================== 订单处理操作 ====================

    @Override
    public Result<String> buyProduct(OrdersDTO orderData) {
        if (orderData.getProductId() == null) {
            return ApiResult.error("商品ID必须提供");
        }

        ProductQueryDto query = new ProductQueryDto();
        query.setId(orderData.getProductId());
        List<ProductVO> items = productDataAccess.query(query);

        if (items.isEmpty()) {
            return ApiResult.error("未找到商品信息");
        }

        ProductVO item = items.get(0);
        int requestedQty = orderData.getBuyNumber();

        if (item.getInventory() <= 0 || (item.getInventory() - requestedQty) < 0) {
            return ApiResult.error("库存不足无法购买");
        }

        prepareOrder(orderData, item);
        orderDataAccess.save(orderData);

        Product stockUpdate = new Product();
        stockUpdate.setId(item.getId());
        stockUpdate.setInventory(item.getInventory() - requestedQty);
        productDataAccess.update(stockUpdate);

        return ApiResult.success("购买操作成功");
    }

    @Override
    public Result<String> placeAnOrder(Integer orderId) {
        Orders order = new Orders();
        order.setId(orderId);
        order.setTradeStatus(true);
        order.setTradeTime(LocalDateTime.now());
        orderDataAccess.update(order);
        return ApiResult.success("订单确认成功");
    }

    @Override
    public Result<String> refund(Integer orderId) {
        Orders order = new Orders();
        order.setId(orderId);
        order.setRefundStatus(false);
        orderDataAccess.update(order);
        return ApiResult.success("退款申请已提交");
    }

    // ==================== 推荐系统操作 ====================

    @Override
    public Result<List<Product>> getRecommendedProducts(Integer userId) {
        List<Integer> viewedItems = interactionDataAccess.getRecentViews(userId, 10);

        if (viewedItems.isEmpty()) {
            return ApiResult.success(productDataAccess.getPopularProducts(5));
        }

        return ApiResult.success(productDataAccess.findRecommendedProducts(userId));
    }

    // ==================== 内部辅助方法 ====================

    private void prepareOrder(Orders order, ProductVO item) {
        order.setCode(generateOrderCode());
        order.setUserId(LocalThreadHolder.getUserId());
        order.setTradeStatus(false);
        order.setBuyPrice(item.getPrice());
        order.setCreateTime(LocalDateTime.now());
    }

    private String generateOrderCode() {
        return String.valueOf(System.currentTimeMillis());
    }

    private int countInteractions(List<Interaction> interactions, Integer type) {
        return (int) interactions.stream()
                .filter(inter -> Objects.equals(type, inter.getType()))
                .count();
    }
}