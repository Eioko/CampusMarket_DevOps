package code_product.LayerMap;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.Po.Dto.query.sons.StarQueryDto;
import com.Po.Vo.StarVo;

public interface LayerStarMapper {
    List<StarVo> query(StarQueryDto starQueryDto);

    int save(StarQueryDto starQueryDto);

    void batchDelete(@Param(value = "ids") List<Integer> ids);
}
