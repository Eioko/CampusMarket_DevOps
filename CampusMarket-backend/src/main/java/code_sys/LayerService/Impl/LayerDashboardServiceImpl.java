package code_sys.LayerService.Impl;

import code_sys.LayerMap.*;
import code_sys.Po.Api.ApiResult;
import code_sys.Po.Api.Result;
import code_sys.Po.Dto.query.base.QueryDto;
import code_sys.Po.Dto.query.sons.*;
import code_sys.Po.Vo.ChartVO;
import code_sys.Po.Vo.ProductVO;
import code_sys.LayerService.DashboardService;
import code_sys.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 仪表盘业务逻辑接口实现类
 */
@Service
public class LayerDashboardServiceImpl implements DashboardService {

    @Resource
    private LayerUserMapper layerUserMapper;
    @Resource
    private LayerProductMapper layerProductMapper;
    @Resource
    private LayerOrdersMapper layerOrdersMapper;
    @Resource
    private LayerMessageMapper layerMessageMapper;
    @Resource
    private LayerInteractionMapper layerInteractionMapper;
    @Resource
    private LayerEvaluationsMapper layerEvaluationsMapper;

    /**
     * 创建ChartVO对象，避免重复代码
     *
     * @param label 显示的标签
     * @param count 数据
     * @return ChartVO
     */
    private ChartVO createChartVO(String label, int count) {
        System.out.println("进入接口");
        System.out.println("进入返回函数");
        return new ChartVO(label, count);
    }

    /**
     * 统计商品的上架情况
     *
     * @param day 往前查的天数
     * @return Result<List<ChartVO>> 响应结果
     */
    @Override
    public Result<List<ChartVO>> productShelvesInfo(Integer day) {
        QueryDto queryDto = DateUtil.startAndEndTime(day);//
        ProductQueryDto productQueryDto = new ProductQueryDto();
        productQueryDto.setStartTime(queryDto.getStartTime());
        productQueryDto.setEndTime(queryDto.getEndTime());
        List<ProductVO> productVOS = layerProductMapper.query(productQueryDto);//

        List<LocalDateTime> dateTimeList = productVOS.stream()
                .map(ProductVO::getCreateTime)
                .collect(Collectors.toList());

        List<ChartVO> chartVOS = DateUtil.countDatesWithinRange(day, dateTimeList);
        return ApiResult.success(chartVOS);
    }
//
    /**
     * 统计系统的基础数据
     *
     * @return Result<List<ChartVO>> 响应结果
     */
    @Override
    public Result<List<ChartVO>> staticCount() {
        // 使用统一方法来创建ChartVO列表
        List<ChartVO> chartVOList = new ArrayList<>();
        chartVOList.add(createChartVO("用户数", layerUserMapper.queryCount(new UserQueryDto())));
        chartVOList.add(createChartVO("收录商品", layerProductMapper.queryCount(new ProductQueryDto())));
        chartVOList.add(createChartVO("订单数", layerOrdersMapper.queryCount(new OrdersQueryDto())));
        chartVOList.add(createChartVO("消息通知", layerMessageMapper.queryCount(new MessageQueryDto())));
        chartVOList.add(createChartVO("互动数据", layerInteractionMapper.queryCount(new InteractionQueryDto())));
        chartVOList.add(createChartVO("商品评论", layerEvaluationsMapper.queryCount(new EvaluationsQueryDto())));

        return ApiResult.success(chartVOList);
    }
}
