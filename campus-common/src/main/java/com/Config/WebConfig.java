package com.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置 - 使用可配置属性
 */
@Configuration
@ConfigurationProperties(prefix = "cors")
public class WebConfig implements WebMvcConfigurer {

    private String allowedOrigins = "*";
    private String allowedMethods = "*";
    private String allowedHeaders = "*";
    private long maxAge = 5000L;

    // Setter方法用于属性注入
    public void setAllowedOrigins(String allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public void setAllowedMethods(String allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public void setAllowedHeaders(String allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    public void setMaxAge(long maxAge) {
        this.maxAge = maxAge;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigins.split(","))
                        .allowedMethods(allowedMethods.split(","))
                        .allowedHeaders(allowedHeaders.split(","))
                        .maxAge(maxAge);
            }
        };
    }
}