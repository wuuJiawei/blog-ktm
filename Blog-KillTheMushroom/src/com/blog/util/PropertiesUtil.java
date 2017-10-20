package com.blog.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * ����ģʽ��ȡ�����ļ�
 * @author ���ΰؼ
 *
 */
public class PropertiesUtil {

	private static Map<String, String> proMap = new HashMap<String,String>();//Maps.newHashMap();//����֪���Ǹ�ʲô�õ�
	
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
	 * ���캯��
	 */
	private PropertiesUtil(){
		proMap = readProperties();
	}
	
	/**
	 * ��ȡ�����ļ�
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
