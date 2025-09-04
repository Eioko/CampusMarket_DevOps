package code_product.LayerControl;

import com.Interceptor.LocalThreadHolder;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;
import com.Po.Entity.Product;
import com.Po.Vo.ChartVO;
import com.Po.Vo.ProductVO;
import com.Aop.Log;
import com.Aop.Pager;
import com.Po.Api.Result;
import code_product.LayerService.ProductService;
import com.Po.Dto.query.sons.ProductQueryDto;
import com.Po.Dto.update.OrdersDTO;

/**
 * 商品管理接口入口
 */
@CrossOrigin
@RestController
@RequestMapping("/product")
public class LayerProductController {

    @Resource
    ProductService productManager;

    // ==================== 商品操作接口 ====================

    @Log(detail = "创建新商品")
    @PostMapping("/save")
    public Result<String> addNewProduct(@RequestBody Product newProd) {
        return productManager.save(newProd);
    }

    @Log(detail = "变更商品信息")
    @PutMapping("/update")
    public Result<String> modifyProduct(@RequestBody Product existingProd) {
        return productManager.update(existingProd);
    }

    @PostMapping("/batchDelete")
    public Result<String> removeProducts(@RequestBody List<Integer> idList) {
        return productManager.batchDelete(idList);
    }

    // ==================== 订单操作接口 ====================

    @Log(detail = "用户购买商品")
    @PostMapping("/buyProduct")
    public Result<String> purchaseItem(@RequestBody OrdersDTO orderData) {
        return productManager.buyProduct(orderData);
    }

    @PostMapping("/placeAnOrder/{ordersId}")
    public Result<String> confirmOrder(@PathVariable Integer ordersId) {
        return productManager.placeAnOrder(ordersId);
    }

    @Log(detail = "用户申请退款")
    @PostMapping("/refund/{ordersId}")
    public Result<String> requestRefund(@PathVariable Integer ordersId) {
        return productManager.refund(ordersId);
    }

    // ==================== 数据查询接口 ====================

    @Pager
    @PostMapping("/query")
    public Result<List<ProductVO>> searchProducts(@RequestBody ProductQueryDto searchParams) {
        return productManager.query(searchParams);
    }

    @PostMapping("/queryUser")
    public Result<List<ProductVO>> userProductQuery(@RequestBody ProductQueryDto userQuery) {
        userQuery.setUserId(LocalThreadHolder.getUserId());
        return productManager.query(userQuery);
    }

    @GetMapping("/getById/{id}")
    public Result<List<ProductVO>> fetchById(@PathVariable Integer id) {
        ProductQueryDto param = new ProductQueryDto();
        param.setUserId(id);
        return productManager.query(param);
    }

    @PostMapping("/queryProductInfo")
    public Result<List<ChartVO>> retrieveProductStats(@RequestBody ProductQueryDto statParams) {
        return productManager.queryProductInfo(statParams);
    }

    // ==================== 个性化接口 ====================

    @GetMapping("/recommend")
    public Result<List<Product>> getSuggestedItems() {
        Integer currentUserId = LocalThreadHolder.getUserId();
        return productManager.getRecommendedProducts(currentUserId);
    }
}