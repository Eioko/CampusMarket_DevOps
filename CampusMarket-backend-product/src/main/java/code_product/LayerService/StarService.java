package code_product.LayerService;

import java.util.List;

import com.Po.Api.Result;
import com.Po.Dto.query.sons.StarQueryDto;
import com.Po.Vo.StarVo;
import com.Po.Vo.UserVO;

public interface StarService {

    Result<List<UserVO>> queryByUser1(Integer userId);

    Result<List<UserVO>> queryByUser2(Integer userId);

    Result<List<StarVo>> query(StarQueryDto starQueryDto);

    Result<Boolean> starOperation(Integer userId);

    public List<StarVo> getStarVos(StarQueryDto starQueryDto);
}
