package com.Config;

import com.Interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Value("${my-server.api-context-path}")
    private String apiContextPath;

    private static final String[] EXCLUDED_PATHS = {
            "/user/login",
            "/user/register",
            "/file/upload",
            "/file/getFile"
    };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 构建完整的排除路径数组
        String[] fullExcludedPaths = buildFullExcludedPaths();

        registry.addInterceptor(new JwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(fullExcludedPaths);
    }

    /**
     * 构建完整的排除路径数组（添加API上下文路径）
     */
    private String[] buildFullExcludedPaths() {
        String[] result = new String[EXCLUDED_PATHS.length];
        for (int i = 0; i < EXCLUDED_PATHS.length; i++) {
            result[i] = apiContextPath + EXCLUDED_PATHS[i];
        }
        return result;
    }
}