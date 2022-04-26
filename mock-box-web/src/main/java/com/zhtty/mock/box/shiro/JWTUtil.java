package com.zhtty.mock.box.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {

    // 过期时间30天
    private static final long EXPIRE_TIME = 24 * 60 * 30 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token    **
     * @param userNo 登录名
     * @param password 密码
     * @return
     */
    public static boolean verify(String token, String userNo, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);

            JWTVerifier verifier = JWT.require(algorithm).withClaim("userNo", userNo).build();

            DecodedJWT jwt = verifier.verify(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取登录名
     *
     * @param token
     * @return
     */
    public static String getUserNo(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);

            return jwt.getClaim("userNo").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 生成签名
     *
     * @param userNo
     * @param password
     * @return
     */
    public static String sign(String userNo, String password) {
        try {
            // 指定过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);

            Algorithm algorithm = Algorithm.HMAC256(password);

            return JWT.create()
                    .withClaim("userNo", userNo)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

}