package com.aq360.gateway.common.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Arvin
 *
 */
public class JsonUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static String obj2Json(Object obj) throws IOException {
		return mapper.writeValueAsString(obj);
	}
	
	public static byte[] obj2JsonBytes(Object obj) throws IOException {
		return mapper.writeValueAsBytes(obj);
	}
	
	public static Object json2Obj(String json, Class<? extends Object> clazz) throws IOException {
		return mapper.readValue(json, clazz);
	}
	
	public static Object json2Obj(byte[] json, Class<? extends Object> clazz) throws IOException {
		return mapper.readValue(json, clazz);
	}
}
