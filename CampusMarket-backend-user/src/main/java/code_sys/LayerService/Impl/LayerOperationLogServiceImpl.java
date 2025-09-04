package code_sys.LayerService.Impl;

import com.LayerMap.LayerOperationLogMapper;
import com.Po.Api.ApiResult;
import com.Po.Api.Result;
import com.Po.Dto.query.sons.OperationLogQueryDto;
import com.Po.Entity.OperationLog;
import com.Po.Vo.OperationLogVO;
import code_sys.LayerService.OperationLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LayerOperationLogServiceImpl implements OperationLogService {

    private static final Logger logger = LoggerFactory.getLogger(LayerOperationLogServiceImpl.class);

    @Resource
    private LayerOperationLogMapper layerOperationLogMapper;

    /**
     * 保存操作日志（不暴露给外部，仅限内部调用）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(OperationLog operationLog) {
        try {
            // 参数验证
            if (operationLog == null) {
                return ApiResult.error("日志参数不能为空");
            }
            if (operationLog.getUserId() == null) {
                return ApiResult.error("用户ID不能为空");
            }
            if (operationLog.getDetail() == null || operationLog.getDetail().isEmpty()) {
                return ApiResult.error("操作详情不能为空");
            }

            // 保存日志
            layerOperationLogMapper.save(operationLog);

            return ApiResult.success("操作日志记录成功");
        } catch (Exception e) {
            logger.error("保存操作日志失败: {}", e.getMessage(), e);
            return ApiResult.error("操作日志保存失败");
        }
    }

    /**
     * 批量删除操作日志
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<String> batchDelete(List<Integer> ids) {
        try {
            // 参数验证
            if (CollectionUtils.isEmpty(ids)) {
                logger.warn("批量删除操作日志失败: 删除列表为空");
                return ApiResult.error("删除列表不能为空");
            }

            // 执行删除
            layerOperationLogMapper.batchDelete(ids);

            // 记录操作日志
            logger.info("批量删除操作日志: 数量={}", ids.size());

            return ApiResult.success("成功删除" + ids.size() + "条操作日志");
        } catch (Exception e) {
            logger.error("批量删除操作日志失败: {}", e.getMessage(), e);
            return ApiResult.error("操作日志删除失败");
        }
    }

    /**
     * 查询
     *
     * @param operationLogQueryDto 查询参数
     * @return Result<List < OperationLogVO>> 后台通用返回封装类
     */
    @Override
    public Result<List<OperationLogVO>> query(OperationLogQueryDto operationLogQueryDto) {
        try{
            int totalCount = layerOperationLogMapper.queryCount(operationLogQueryDto);
            List<OperationLogVO> categoryList = layerOperationLogMapper.query(operationLogQueryDto);
            return ApiResult.success(categoryList, totalCount);
        }catch(Exception e){
            return ApiResult.error("操作日志删除失败");
        }
    }
}