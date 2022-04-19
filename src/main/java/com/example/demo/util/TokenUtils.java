package com.example.demo.util;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.jsonwebtoken.*;


import java.util.Date;

public class TokenUtils {

    private static final long EXPIRE_TIME = 10 * 60 * 60 * 1000;
    private static final String TOKENKEY = "tjrac";


    public static String createToken(String userId){
        String token = null;

        try {
            token = Jwts.builder()
                    .setSubject("YYGH_USER")
                    .setExpiration(new Date(System.currentTimeMillis()+EXPIRE_TIME))
                    .claim("userId",userId)
                    .signWith(SignatureAlgorithm.HS512,TOKENKEY)
                    .compressWith(CompressionCodecs.GZIP)
                    .compact();
        } catch (Exception e) {
            System.out.println("createToken 失败");
            e.printStackTrace();
        }
        return token;
    }

    public static String getUserId(String token){
        if (StringUtils.isEmpty(token)){
            return null;
        }

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(TOKENKEY).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();

        String userId = (String) claims.get("userId");
        return userId;
    }

    public static Boolean isExpiration(String token){
        try {
            Boolean isExpire = Jwts.parser().setSigningKey(TOKENKEY)
                    .parseClaimsJws(token).getBody().getExpiration().before(new Date());

            return isExpire;
        } catch (Exception e) {
            return true;
        }
    }

    public static String refreshToken(String token){

        String refreshToken;

        Claims claims  = Jwts.parser()
                .setSigningKey(TOKENKEY)
                .parseClaimsJws(token)
                .getBody();

        refreshToken = TokenUtils.createToken(getUserId(token));
        return refreshToken;
    }
}
