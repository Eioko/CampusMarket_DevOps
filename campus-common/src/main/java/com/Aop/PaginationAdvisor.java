package com.Aop;

import com.Po.Dto.query.base.QueryDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1) // 添加执行顺序控制
public class PaginationAdvisor {

    @Around("@annotation(pagerAnnotation)")
    public Object adaptPaginationParameters(ProceedingJoinPoint joinPoint, Pager pagerAnnotation)
            throws Throwable {

        Object[] arguments = joinPoint.getArgs();
        boolean paginationApplied = applyParameterAdjustment(arguments);

        if (paginationApplied) {
            return joinPoint.proceed(arguments);
        }
        return joinPoint.proceed();
    }

    /**
     * 对查询参数应用分页调整
     *
     * @param methodArgs 方法参数数组
     * @return 是否应用了分页调整
     */
    private boolean applyParameterAdjustment(Object[] methodArgs) {
        boolean adjustmentMade = false;

        for (int i = 0; i < methodArgs.length; i++) {
            if (methodArgs[i] instanceof QueryDto) {
                QueryDto queryParam = (QueryDto) methodArgs[i];
                if (requiresPagination(queryParam)) {
                    applyPaginationOffset(queryParam);
                    adjustmentMade = true;
                }
            }
        }
        return adjustmentMade;
    }

    /**
     * 检查参数是否需要分页处理
     */
    private boolean requiresPagination(QueryDto queryParam) {
        return queryParam.getCurrent() != null &&
                queryParam.getSize() != null &&
                queryParam.getCurrent() > 0;
    }

    /**
     * 应用分页偏移量计算
     */
    private void applyPaginationOffset(QueryDto queryParam) {
        int currentPage = queryParam.getCurrent();
        int pageSize = queryParam.getSize();

        // 使用改进算法计算偏移量
        int offset = (currentPage - 1) * pageSize;
        queryParam.setCurrent(offset);
    }
}