package com.tv189.common.util;

import com.alibaba.fastjson.JSON;

/**
 * key值生成
 * @author dingli
 * @date 2015-4-27 下午4:14:19
 */
public class CacheKeyGenarator {

	private static final String prefix="cache";
	
	public static String makeKey(String tablePrefix,Object... keyNames) {
		int len = keyNames.length;
		String keyStr=null;
		StringBuilder key = new StringBuilder(prefix + "_" + tablePrefix+"_");
		for (int i = 0; i < len; i++) {
			Object keyItem = keyNames[i];
			if (keyItem == null) {
				continue;
			}
			
			key.append(JSON.toJSONString(keyItem));
		}
			keyStr=Md5Util.encodeStr(key.toString());
		return keyStr;
	}
	
}
