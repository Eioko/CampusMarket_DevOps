package code_product.LayerMap;

import com.Po.Dto.query.sons.InteractionQueryDto;
import com.Po.Entity.Interaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface LayerInteractionMapper {


    int save(Interaction interaction);


    List<Interaction> query(InteractionQueryDto interactionQueryDto);


    List<Interaction> queryByProductIds(@Param(value = "ids") List<Integer> ids);


    int queryCount(InteractionQueryDto interactionQueryDto);


    void batchDelete(@Param(value = "ids") List<Integer> ids);

    List<Integer> getRecentViews(@Param("userId") Integer userId,@Param("limit") Integer limit);
}
