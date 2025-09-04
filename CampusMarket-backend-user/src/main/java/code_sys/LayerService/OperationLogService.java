package code_sys.LayerService;

import com.Po.Api.Result;
import com.Po.Dto.query.sons.OperationLogQueryDto;
import com.Po.Entity.OperationLog;
import com.Po.Vo.OperationLogVO;

import java.util.List;

/**
 * 操作日志的业务逻辑接口
 */
public interface OperationLogService {

    Result<String> save(OperationLog operationLog);

    Result<String> batchDelete(List<Integer> ids);

    Result<List<OperationLogVO>> query(OperationLogQueryDto operationLogQueryDto);

}
