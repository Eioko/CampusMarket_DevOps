package com.Interceptor;

import com.Po.Api.ApiResult;
import com.Po.Api.Result;
import com.utils.JwtUtil;
import com.alibaba.fastjson2.JSONObject;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String method = request.getMethod();
        if ("OPTIONS".equalsIgnoreCase(method)) {
            return true;
        }

        String uri = request.getRequestURI();

        if (isExcludedUri(uri)) {
            return true;
        }

        String token = request.getHeader("token");
        Claims claims = JwtUtil.parseToken(token);

        if (claims == null) {
            writeErrorResponse(response, ApiResult.error("身份认证异常，请先登录"));
            return false;
        }

        Integer userId = claims.get("id", Integer.class);
        Integer roleId = claims.get("role", Integer.class);

        LocalThreadHolder.setUserId(userId, roleId);

        return true;
    }

    private boolean isExcludedUri(String uri) {
        if (uri.contains("/login")) {
            return true;
        }
        if(uri.contains("/error") ){
            return true;
        }
        if(uri.contains("/file") ){
            return true;
        }
        if( uri.contains("/register")){
            return  true;
        }
        if (uri.contains("/query") && !uri.contains("/queryUser")
                && !uri.contains("/queryOrdersList")
                && !uri.contains("/queryProductInfo")
                && !uri.contains("/queryCurrentUser")) {
            return true;
        }
        return false;
    }

    private void writeErrorResponse(HttpServletResponse response, Result<String> error) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (Writer writer = response.getWriter()) {
            writer.write(JSONObject.toJSONString(error));
            writer.flush();
        }
    }

}
