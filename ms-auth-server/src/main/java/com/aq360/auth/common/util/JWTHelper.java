package com.aq360.auth.common.util;
import java.util.Date;
import java.util.Map;

import com.aq360.auth.common.constant.JwtConstants;
import com.aq360.auth.domain.bean.JWTInfo;
import com.aq360.auth.domain.bean.JWTInfoImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTHelper {
	public static void main(String[] args) {
		JWTInfoImpl infoImpl = new JWTInfoImpl();
		infoImpl.setUserId("dingli");
		String token = generateToken(infoImpl);
		System.out.println(token);
	}

    /**
     * 密钥加密token
     * @param jwtInfo
     */
    public static String generateToken(JWTInfo jwtInfo) {

        Date date = new Date(System.currentTimeMillis() + JwtConstants.JWT_EXPIRATION);

        String compactJws = Jwts.builder()
                .setHeader((Map<String, Object>) JwtConstants.JWT_HEADER)
                .claim(JwtConstants.JWT_KEY_USER_ID, jwtInfo.getUserId())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, JwtConstants.JWT_SIGNING_KEY)
                .compact();
        return compactJws;
    }

    /**
     * 公钥解析token
     * @param token
     */
    public static Jws<Claims> parserToken(String token){

        Jws<Claims> claimsJws = null;
        try {
            claimsJws = Jwts.parser().setSigningKey(JwtConstants.JWT_SIGNING_KEY).parseClaimsJws(token);
        }catch (Exception e){
        	log.error("公钥解析token错误",e);
        }
        return claimsJws;
    }

    /**
     * 获取token中的用户信息
     *
     * @param token
     */
    public static JWTInfo getInfoFromToken(String token) {

        JWTInfo jwtInfo = null;

        Jws<Claims> claimsJws = parserToken(token);
        if(null != claimsJws){
            Claims body = claimsJws.getBody();
            jwtInfo = new JWTInfoImpl(StringHelper.getObjectValue(StringHelper.getObjectValue(body.get(JwtConstants.JWT_KEY_USER_ID))));

        }
        return jwtInfo;
    }

    public static int volidateToken(String token)  {

        int statuscode = JwtConstants.JWT_ERROR;
        try{
            long currTime = System.currentTimeMillis();
            Jws<Claims> claimsJws = parserToken(token);
            long tokenExpiraTime = claimsJws.getBody().getExpiration().getTime();
            if(currTime < tokenExpiraTime){
                statuscode = JwtConstants.JWT_SUCCESS;
            }
        }catch(Exception e){
        	log.error("token校验错误",e);
        }

        return statuscode;
    }

    public static long getTokenExpiraTime(String token)  {

        Jws<Claims> claimsJws = parserToken(token);
        long tokenExpiraTime = claimsJws.getBody().getExpiration().getTime();

        return tokenExpiraTime;
    }

    public static String getUniqueKey(JWTInfo jwtInfo){

        String key = "";

        if(null != jwtInfo){
        	key = jwtInfo.getUserId();
        }

        return key;

    }

}
