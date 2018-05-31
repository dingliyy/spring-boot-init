package com.aq360.auth.common.constant;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.impl.DefaultHeader;

public  class JwtConstants {

    public static  final String JWT_SIGNING_KEY = "ceshi";

    public static final Header JWT_HEADER = new DefaultHeader();

    public static final long JWT_EXPIRATION = 1800000l;

    //token校验成功
    public static final int JWT_SUCCESS = 5000;

    //token校验失败
    public static final int JWT_ERROR = 5001;
    
    //token校验过期
    public static final int JWT_EXPIRE = 5002;
    
    public static  final String JWT_KEY_USER_ID = "userId";


}
