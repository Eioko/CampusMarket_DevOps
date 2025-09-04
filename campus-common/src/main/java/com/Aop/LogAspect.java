package com.Aop;

import com.Interceptor.LocalThreadHolder;
import com.LayerMap.LayerOperationLogMapper;
import com.Po.Entity.OperationLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Aspect
@Component
public class LogAspect {

    @Resource
    private LayerOperationLogMapper layerOperationLogMapper;

    @Around("@annotation(log)")
    public Object handlePageableParams(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        OperationLog logEntity = createLogEntity(log);
        layerOperationLogMapper.save(logEntity);
        return joinPoint.proceed();
    }

    private OperationLog createLogEntity(Log log) {
        return OperationLog.builder()
                .createTime(LocalDateTime.now())
                .detail(log.detail())
                .userId(LocalThreadHolder.getUserId())
                .build();
    }

}
