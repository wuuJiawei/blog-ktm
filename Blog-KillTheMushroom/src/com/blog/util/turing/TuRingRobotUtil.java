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
 * ͼ�������api
 * @author ���ΰؼ
 *
 */
public class TuRingRobotUtil {
	
	private final static String REQUEST_URL = "http://www.tuling123.com/openapi/api";

	/**
	 * ��ȡ�ش�
	 * @param content
	 * @param userId
	 * @return
	 */
	public static String getAnswer(String content,int userId) {
		try {
			String appkey = PropertiesUtil.getInstance().getPropMap().get("turing.appkey");
			
	        try {
	            // ����HttpRequest����
	            HttpClient client = new HttpClient();
	            PostMethod request = new PostMethod(REQUEST_URL);
	            // ���request headers
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
	            //��ȡ��Ӧ
				JSONObject obj = new JSONObject(resp);
				String text = obj.getString("text");
				System.out.println(text);
				return text;
	        } catch (Exception e) {
	            System.out.println("����POST��������쳣��" + e);
	            e.printStackTrace();
	        } 
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		TuRingRobotUtil.getAnswer("���", 1023930);
	}
}
