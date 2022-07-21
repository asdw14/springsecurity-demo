package com.dizhongdi.spring_security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ClassName:TokenManager
 * Package:com.dizhongdi.spring_security.security
 * Description:
 *      token 操作的工具类
 * @Date: 2022/7/21 20:28
 * @Author:dizhongdi
 */
@Component
public class TokenManager {
    private long tokenExpiration = 24*60*60*1000;
    private String tokenSignKey = "123456";

    public String createToken(String username) {
        String token = Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(SignatureAlgorithm.ES512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    public String getUserFromToken(String token) {
        String user = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token).getBody().getSubject();
        return user;
    }

    public void removeToken(String token) {
        //jwttoken 无需删除，客户端扔掉即可。
    }
}
