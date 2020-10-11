package com.myblog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * jwt工具类
 *
 * @author lmd
 * @version 1.0
 * @date 2019-10-24 11:13
 */
@Slf4j
public class JwtUtil {
    /**
     * token前缀
     */
    private static final String TOKEN_PREFIX = "Bearer ";
    /**
     * 密匙
     */
    private static final String SECRET = "bIvzjavMFcFKfHcOOU9ERSl5oqJ0ZvDq";
    /**
     * 发行人
     */
    private static final String ISSUER = "admin";
    /**
     * 过期时间
     */
    private static final Long EXPIRE = 900000L;

    /**
     * 生成token
     *
     * @param claims          参数
     * @param expireDatePoint 过期时间点
     * @return 返回token
     */
    public static String genToken(Map<String, String> claims, Date expireDatePoint) {
        try {
            //加入时间毫秒值，保证每次都不一样
            claims.put("time", System.currentTimeMillis() + "");
            //使用HMAC256进行加密
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            //创建jwt
            JWTCreator.Builder builder = JWT.create()
                    //发行人
                    .withIssuer(ISSUER);
            //过期时间点
            if (Objects.nonNull(expireDatePoint)) {
                builder.withExpiresAt(expireDatePoint);
            }
            //传入参数
            claims.forEach(builder::withClaim);
            //签名加密
            return TOKEN_PREFIX + builder.sign(algorithm);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成token
     *
     * @param claims 参数
     * @return 返回token
     */
    public static String genToken(Map<String, String> claims) {
        return genToken(claims, null);
    }

    /**
     * 解密jwt
     *
     * @param token token
     * @return 返回参数
     */
    public static Map<String, String> verifyToken(String token) {
        token = token.replaceFirst(TOKEN_PREFIX, "");
        //使用HMAC256进行加密
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        //解密
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
        try {
            DecodedJWT jwt = verifier.verify(token);
            Map<String, Claim> map = jwt.getClaims();
            Map<String, String> resultMap = new HashMap<>();
            map.forEach((k, v) -> resultMap.put(k, v.asString()));
            return resultMap;
        } catch (TokenExpiredException ignored) {
        }
        //token过期
        return null;
    }

    /**
     * 创建一个token
     *
     * @param userId
     * @return
     */
    public static String createToken(String userId) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + EXPIRE);
        String token = Jwts.builder().setHeaderParam("type", "JWT").setSubject(userId).setIssuedAt(now)
                .setExpiration(expireDate).signWith(
                        SignatureAlgorithm.HS512, SECRET).compact();
        return TOKEN_PREFIX + token;
    }


    /**
     * 解析Claims
     *
     * @param token
     * @return
     */
    public static Claims getClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("token解析错误");
            return null;
        }
    }

    /**
     * 判断 token 是否过期
     */
    public static boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}