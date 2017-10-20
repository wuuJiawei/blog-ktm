package com.blog.util.turing;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;

import com.blog.util.PropertiesUtil;
import com.blog.util.StringUtil;
import com.google.gson.Gson;

/**
 * 图灵机器人api
 * @author 武佳伟丶
 *
 */
public class TuRingRobotUtil {
	
	private final static String REQUEST_URL = "http://www.tuling123.com/openapi/api";

	/**
	 * 获取回答
	 * @param content
	 * @param userId
	 * @return
	 */
	public static String getAnswer(String content,int userId) {
		try {
			String appkey = PropertiesUtil.getInstance().getPropMap().get("turing.appkey");
			
	        try {
	            // 构造HttpRequest设置
	            HttpClient client = new HttpClient();
	            PostMethod request = new PostMethod(REQUEST_URL);
	            // 添加request headers
	            request.addRequestHeader("Content-type", "application/json");
	            request.addRequestHeader("Accept", "application/json");
	            
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("key", appkey);
	            map.put("info", content);
	            map.put("userid", userId);
	            
	            String json = new Gson().toJson(map);
	            request.setRequestEntity(new ByteArrayRequestEntity(json.getBytes("UTF-8")));  
	              
	            client.executeMethod(request);  
	            String resp = request.getResponseBodyAsString();  
	            resp = new String(StringUtil.encodeStr(resp));
	            System.out.println(resp);
	            //获取响应
				JSONObject obj = new JSONObject(resp);
				String text = obj.getString("text");
				System.out.println(text);
				return text;
	        } catch (Exception e) {
	            System.out.println("发送POST请求出现异常！" + e);
	            e.printStackTrace();
	        } 
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		TuRingRobotUtil.getAnswer("你好", 1023930);
	}
}
