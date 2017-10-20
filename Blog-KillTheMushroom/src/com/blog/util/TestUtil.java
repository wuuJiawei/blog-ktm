package com.blog.util;

public class TestUtil {

	
	public static void main(String[] args) {
		PropertiesUtil p = PropertiesUtil.getInstance();
		for(String key : p.getPropMap().keySet()){
			String value = p.getPropMap().get(key);
			System.out.println( key+"="+value);
		}
		
		String url = p.getPropMap().get("upyun.url");
		System.out.println(url);
	}
}
