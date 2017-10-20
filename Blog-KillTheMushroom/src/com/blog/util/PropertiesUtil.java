package com.blog.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * 单例模式读取配置文件
 * @author 武佳伟丶
 *
 */
public class PropertiesUtil {

	private static Map<String, String> proMap = new HashMap<String,String>();//Maps.newHashMap();//并不知道是干什么用的
	
	private static class PropertiesInstance {
		private static final PropertiesUtil props = new PropertiesUtil();
	}
	
	public static PropertiesUtil getInstance(){
		return PropertiesInstance.props;
	}
	
	public Map<String,String> getPropMap() {
		return proMap;
	}
	
	/*
	 * 构造函数
	 */
	private PropertiesUtil(){
		proMap = readProperties();
	}
	
	/**
	 * 读取配置文件
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static Map<String, String> readProperties() {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = PropertiesUtil.class.getClassLoader().getResourceAsStream("sdk.properties");
			props.load(in);
			Enumeration en = props.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String value = props.getProperty(key);
				proMap.put(key, value);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return proMap;
	}
	
}
