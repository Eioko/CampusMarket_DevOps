package com.Aop;

import com.Interceptor.LocalThreadHolder;
import com.Po.Api.ApiResult;
import com.Po.Em.SystemRoleType;
import com.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 接口鉴权保护切面
 */
@Aspect
@Component
public class ProtectorAspect {

    @Around("@annotation(com.Aop.Protector)")
    public Object auth(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attrs.getRequest();

        String token = request.getHeader("token");
        if (token == null) {
            return ApiResult.error("身份认证失败，请先登录");
        }

        Claims claims = JwtUtil.parseToken(token);
        if (claims == null) {
            return ApiResult.error("身份认证失败，请先登录");
        }

        Integer userId = claims.get("id", Integer.class);
        Integer roleId = claims.get("role", Integer.class);

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Protector protector = signature.getMethod().getAnnotation(Protector.class);
        if (protector == null) {
            return ApiResult.error("身份认证失败，请先登录");
        }

        String requiredRole = protector.role();
        if (!requiredRole.isEmpty()) {
            String userRole = SystemRoleType.resolveDisplayName(roleId);
            if (!requiredRole.equals(userRole)) {
                return ApiResult.error("无操作权限");
            }
        }

        LocalThreadHolder.setUserId(userId, roleId);
        try {
            return joinPoint.proceed();
        } finally {
            LocalThreadHolder.clear();
        }
    }

}
