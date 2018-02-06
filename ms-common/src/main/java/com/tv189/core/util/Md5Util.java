package com.tv189.core.util;

import org.apache.commons.codec.digest.DigestUtils;


public class Md5Util {
	public static String encode(String value) {  
        if (value == null) {  
            return null;  
        }  
        return DigestUtils.md5Hex(value);
    }
	
	
	public static String encode(byte[] value) {  
        if (value == null) {  
            return null;  
        }  
        return DigestUtils.md5Hex(value);
    }
	
	public static String encodeStr(String value) {  
		String str=null;
        try {
        	str= DigestUtils.md5Hex(value.getBytes("utf-8"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        return str;
    }
	
}
