package com.blog.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	/**
	 * 发送json到客户端
	 * @param response
	 * @param obj
	 */
	public static void printJson(HttpServletResponse response,Object obj) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("text/json;charset=UTF-8");
		String temp="";
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
		if (obj!=null) {
			temp=gson.toJson(obj);
		}
		try {
			response.getWriter().print(temp);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printJsonForVideo(HttpServletResponse response,Object obj) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "video/mp4");
		response.setHeader("Accept-Ranges", "bytes");
		response.setHeader("Content-Range", "btyes 0-1");
		response.setContentType("text/json;charset=UTF-8");
		String temp="";
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
		if (obj!=null) {
			temp=gson.toJson(obj);
		}
		try {
			response.getWriter().print(temp);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 拆分成array数组
	 * @param json 需要拆分的json字符串
	 * @param pot json中的分隔符
	 * @return
	 */
	public static String[] toArray(String json,String pot) {
		//去除两端空格
		json=json.replace("[", "");
		json=json.replace("]", "");
		//根据符号拆分
		String[] arr= json.split(pot);
		return arr;
	}
	
	/**
	 * 将str转换为int
	 * @param str
	 * @return
	 */
	public static int toInteger(String str) {
		str=str.replaceAll("\"", "");
		int i = Integer.parseInt(str);
		return i;
	}
	
}
