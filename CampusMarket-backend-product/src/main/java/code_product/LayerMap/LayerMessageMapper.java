package code_product.LayerMap;

import com.Po.Dto.query.sons.MessageQueryDto;
import com.Po.Entity.Message;
import com.Po.Vo.MessageVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 消息持久化接口
 */
public interface LayerMessageMapper {


    int save(Message message);


    int setRead(@Param(value = "userId") Integer userId);


    List<MessageVO> query(MessageQueryDto messageQueryDto);

    int queryCount(MessageQueryDto messageQueryDto);


    void batchDelete(@Param(value = "ids") List<Integer> ids);

}
