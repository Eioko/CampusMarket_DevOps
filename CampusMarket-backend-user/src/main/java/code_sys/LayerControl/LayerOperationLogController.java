package code_sys.LayerControl;

import com.Aop.Log;
import com.Aop.Pager;
import com.Aop.Protector;
import com.Po.Api.ApiResult;
import com.Po.Api.Result;
import com.Po.Dto.query.sons.OperationLogQueryDto;
import com.Po.Vo.OperationLogVO;
import code_sys.LayerService.OperationLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/operation-log")
public class LayerOperationLogController {

    private static final Logger logger = LoggerFactory.getLogger(LayerOperationLogController.class);

    @Resource
    private OperationLogService operationLogService;

    /**
     * 批量删除操作日志（保持原函数名）
     */
    @Log(detail = "批量删除操作日志")
    @Protector(role = "管理员")
    @PostMapping("/batchDelete")
    public Result<String> batchDelete(@RequestBody List<Integer> ids) {
        try {
            // 参数验证
            if (ids == null || ids.isEmpty()) {
                logger.warn("批量删除操作日志失败: 删除列表为空");
                return ApiResult.error("删除列表不能为空");
            }

            // 记录详细日志
            logger.info("执行操作日志批量删除: 数量={}, ID列表={}", ids.size(), ids);

            // 调用服务层
            return operationLogService.batchDelete(ids);
        } catch (Exception e) {
            logger.error("批量删除操作日志失败: {}", e.getMessage(), e);
            return ApiResult.error("删除失败，请稍后重试");
        }
    }

    /**
     * 查询操作日志（保持原函数名）
     */
    @Log(detail = "查询操作日志")
    @Pager
    @PostMapping("/query")
    public Result<List<OperationLogVO>> query(@RequestBody OperationLogQueryDto operationLogQueryDto) {
        try {
            // 参数验证
            if (operationLogQueryDto == null) {
                logger.warn("操作日志查询失败: 查询参数为空");
                return ApiResult.error("查询参数不能为空");
            }

            // 记录详细日志
            logger.info("执行操作日志查询: {}", operationLogQueryDto);

            // 调用服务层
            return operationLogService.query(operationLogQueryDto);
        } catch (Exception e) {
            logger.error("操作日志查询失败: {}", e.getMessage(), e);
            return ApiResult.error("查询失败，请稍后重试");
        }
    }
}