package code_product.LayerMap;

import com.Po.Dto.query.sons.ChatterQueryDto;
import com.Po.Entity.Chatter;
import com.Po.Vo.ChatterVO;

import java.util.List;

/**
 * 聊天信息持久化
 */
public interface LayerChatterMapper {
    /**
     * 新增信息
     *
     * @param chatter
     * @return 行数
     */
    int save(Chatter chatter);

    List<ChatterVO> query(ChatterQueryDto chatterQueryDto);

    List<ChatterVO> queryTable(ChatterQueryDto chatterQueryDto);
}
