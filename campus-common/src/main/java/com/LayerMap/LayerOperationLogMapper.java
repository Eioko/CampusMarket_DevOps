package com.LayerMap;

import com.Po.Dto.query.sons.OperationLogQueryDto;
import com.Po.Entity.OperationLog;
import com.Po.Vo.OperationLogVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作日志持久化接口
 */
public interface LayerOperationLogMapper {

    /**
     * 新增
     *
     * @param operationLog 实体信息
     * @return int 受影响行数
     */
    int save(OperationLog operationLog);

    /**
     * 分页查询信息
     *
     * @param operationLogQueryDto 分页查询参数
     * @return List<OperationLogVO>
     */
    List<OperationLogVO> query(OperationLogQueryDto operationLogQueryDto);

    /**
     * 查询满足分页查询的记录总数
     *
     * @param operationLogQueryDto 分页查询参数
     * @return int 数据总数
     */
    int queryCount(OperationLogQueryDto operationLogQueryDto);

    /**
     * 批量删除操作日志信息
     *
     * @param ids 用户ID集合
     */
    void batchDelete(@Param(value = "ids") List<Integer> ids);

}
