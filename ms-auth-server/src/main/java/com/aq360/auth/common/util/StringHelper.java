package com.aq360.auth.common.util;


public class StringHelper {
	public static final String EMPTY = "";
	
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }
}
