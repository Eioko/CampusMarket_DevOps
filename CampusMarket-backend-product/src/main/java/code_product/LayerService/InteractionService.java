package code_product.LayerService;

import com.Po.Api.Result;
import com.Po.Dto.query.sons.InteractionQueryDto;
import com.Po.Entity.Interaction;
import com.Po.Vo.ProductVO;

import java.util.List;


public interface InteractionService {

    Result<String> save(Interaction interaction);

    Result<String> batchDelete(List<Integer> ids);

    Result<List<Interaction>> query(InteractionQueryDto interactionQueryDto);

    Result<Boolean> saveOperation(Integer productId);

    Result<String> likeProduct(Integer productId);

    Result<List<ProductVO>> queryUser();

    Result<Void> view(Integer productId);

    Result<List<ProductVO>> myView();

    Result<String> batchDeleteInteraction();

    public Result<String> newProduct(Integer productId);

}
