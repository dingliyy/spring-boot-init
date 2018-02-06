package com.tv189.core.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author SunLei
 * 2014-3-12
 * 
 */
public class JsonUtil{

	private static Logger logger = Logger.getLogger(JsonUtil.class);
	
	public static String toJsonStr(Object obj) {
		try{
			return JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
		}catch(Exception e){
			logger.error(obj+" toJsonStr fail"+e.getMessage());
			return null;
		}
	}

	public static <T> T toJSONObject(String jsonStr,Class<T> type){
		try{
			return JSON.parseObject(jsonStr,type);
		}catch(Exception e){
			logger.error(jsonStr +","+type+" toJSONObject fail"+e.getMessage());
			return null;
		}
		
	}
	
	public static  <T> List<T> toBeanList(String jsonStr,Class<T> type){
		try{
			return JSON.parseArray(jsonStr, type);
		}catch(Exception e){
			logger.error(jsonStr +","+type+" toObjectList fail"+e.getMessage());
			return null;
		}
	}
	
	public static  <T> List<T> toBeanList(String jsonStr,Type type){
		try{
			return JSON.parseObject(jsonStr, type);
		}catch(Exception e){
			logger.error(jsonStr +","+type+" toObjectList fail"+e.getMessage());
			return null;
		}
	}
	
	public static  Map beanToMap(Object obj){
		try{
			String jsonStr=toJsonStr(obj);
			return toJSONObject(jsonStr, Map.class);
		}catch(Exception e){
			logger.error(obj+"to map error result:"+e.getMessage());
			return null;
		}
	}


	public static <T> T toGenericBean(String jsonStr,TypeReference<T> tr){
		try {
			return JSON.parseObject(jsonStr, tr);
		} catch (Exception e) {
			logger.error(jsonStr + " to GenericBean fail " + e.getMessage());
			return null;
		}
	}
	

}
