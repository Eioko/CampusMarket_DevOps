package com.utils;

import io.jsonwebtoken.*;
import java.util.Date;
import java.util.UUID;

/**
 * JWT Token 工具类
 */
public class JwtUtil {
    private static final String SECRET_KEY = "d8c986df-8512-42b5-906f-eeea9b3acf86";
    private static final long EXPIRATION_TIME = 7 * 24 * 60 * 60 * 1000L; // 一周的有效期

    private static final String TOKEN_TYPE = "JWT";
    private static final String ALGORITHM = "HS256";
    private static final String TOKEN_SUBJECT = "用户认证";

    /**
     * 生成 JWT Token
     *
     * @param id   用户ID
     * @param role 用户角色
     * @return JWT Token字符串
     */
    public static String createToken(Integer id, Integer role) {
        return Jwts.builder()
                .setHeaderParam("typ", TOKEN_TYPE)
                .setHeaderParam("alg", ALGORITHM)
                .claim("id", id)
                .claim("role", role)
                .setSubject(TOKEN_SUBJECT)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * 解析并验证 JWT Token
     *
     * @param token JWT Token字符串
     * @return 声明对象，解析失败返回null
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            // 处理无效Token的情况
            return null;
        }
    }
}