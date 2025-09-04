package code_product.LayerService;

import com.Po.Api.Result;
import com.Po.Dto.query.sons.ProductQueryDto;
import com.Po.Dto.update.OrdersDTO;
import com.Po.Entity.Product;
import com.Po.Vo.ChartVO;
import com.Po.Vo.ProductVO;

import java.util.List;

/**
 * 商品的业务逻辑接口
 */
public interface ProductService {

    Result<String> save(Product product);

    Result<String> update(Product product);

    Result<String> batchDelete(List<Integer> ids);

    Result<List<ProductVO>> query(ProductQueryDto productQueryDto);

    Result<String> buyProduct(OrdersDTO ordersDTO);

    Result<String> placeAnOrder(Integer ordersId);

    Result<String> refund(Integer ordersId);

    Result<List<ChartVO>> queryProductInfo(ProductQueryDto productQueryDto);

    Result<List<Product>> getRecommendedProducts(Integer userId);
}
