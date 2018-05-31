/**
 * 
 */
package com.aq360.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * 配置管理
 *
 * @author dingli02
 * @date 2018/05/18 15:18
 */
public class ConfigInit {
	private ConfigInit() {}
	
	private static Map<String, String> configMap = null;
	
	private static void init() {
		configMap = new HashMap<String, String>();
		InputStream inputStream = Object.class.getResourceAsStream("/config.properties");
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Iterator<Entry<Object, Object>> iterator = properties.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<Object, Object> entry = iterator.next();
			configMap.put(entry.getKey().toString().trim(), entry.getValue().toString().trim());
		}
	}
	
	public static String getProperty(String key) {
		if(configMap == null) {
			synchronized (ConfigInit.class) {
				if(configMap == null) {
					init();
				}
			}
		}
		return configMap.get(key);
	}
}
