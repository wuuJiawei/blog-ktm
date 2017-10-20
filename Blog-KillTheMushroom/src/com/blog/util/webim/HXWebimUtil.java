package com.blog.util.webim;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import org.apache.commons.httpclient.methods.RequestEntity;
//import org.apache.http.client.HttpClient;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.blog.util.HttpConnection;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class HXWebimUtil {
	
	private static final String OWNER = "admin";
	private static final String ORG_NAME = "diecolornj";
	private static final String APP_NAME = "auction";
	private static final String CLIENT_SECRET = "YXA60rEIWK3ACN5oDfUM6qv7iPjikMI";
	private static final String CLIENT_ID = "YXA6wvJqgDbvEeevQk0hN8-kyQ";
	private static String TOKEN = "YWMtOauvUFZUEee52lFMjlH9qgAAAAAAAAAAAAAAAAAAAAHC8mqANu8R569CTSE3z6TJAgMAAAFcyZQGAABPGgB3q3BQ-0yT3B55QD2je4xDiS2ju_wJ4UxDBRuvCkpMRQ";
	private static String REQUEST_URL = "https://a1.easemob.com/"+ORG_NAME+"/"+APP_NAME+"/"; 
	
	/**
	 * 获取令牌
	 * @return
	 */
	public static AccessToken getAccessToken() {
		StringBuilder sb = new StringBuilder(REQUEST_URL+"token");
		sb.append("?grant_type=client_credentials");
		sb.append("&client_id="+CLIENT_ID);
		sb.append("&client_secret="+CLIENT_SECRET);
		String resp;
		try {
			resp = new HttpConnection().get(sb.toString());
			JSONObject obj = new JSONObject(resp);
			String access_token = obj.getString("access_token");
			String expires_in = obj.getString("expires_in");//有效期,秒
			String application = obj.getString("application");
			System.out.println(access_token);
			
			AccessToken token = new AccessToken();
			token.setAccess_token(access_token);
			token.setExpiresDate(new Date(new Date().getTime()+Integer.parseInt(expires_in)*1000));
			token.setApplication(application);
			
			return token;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * 创建群组
	 * @param token 令牌
	 * @param groupName 群组名称
	 * @param desc 描述
	 * @param isPublic 是否公开
	 * @param approval 是否需要批准
	 * @return groupId 群组id
	 */
	public static String createGroup(String token,String groupName,String desc,boolean isPublic,boolean approval ) {
		try {
			String resp = "";//响应
	        String reqUrl = REQUEST_URL+"chatgroups";
	        try {
	            // 构造httprequest设置
	            HttpClient client = new HttpClient();
	            PostMethod request = new PostMethod(reqUrl);
	            // 添加equest headers
	            request.addRequestHeader("Authorization", "Bearer "+token);
	            request.addRequestHeader("Content-type", "application/json");
	            request.addRequestHeader("Accept", "application/json");
	            
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("groupname", groupName);
	            map.put("desc", desc);
	            map.put("public", isPublic);
	            map.put("approval", approval);
	            map.put("owner", OWNER);
	            
	            String json = new Gson().toJson(map);
	            request.setRequestEntity(new ByteArrayRequestEntity(json.getBytes("UTF-8")));  
	              
	            client.executeMethod(request);  
	            resp = request.getResponseBodyAsString();  
	            System.out.println(resp);
	            //获取响应
				JSONObject obj = new JSONObject(resp);
				JSONObject data = obj.getJSONObject("data");
				String groupid = data.getString("groupid");
				System.out.println(groupid);
				return groupid;
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
	
	/**
	 * 删除群组
	 * @param groupId 群组id
	 * @param token 令牌
	 * @return boolean
	 */
	public static boolean deleteGroup(String groupId,String token) {
		String url = REQUEST_URL+"chatgroups/"+groupId;
		
		String resp = "";//响应
        try {
            // 构造httprequest设置
            HttpClient client = new HttpClient();
            DeleteMethod request = new DeleteMethod(url);
            // 添加equest headers
            request.addRequestHeader("Authorization", "Bearer "+token);
            request.addRequestHeader("Content-type", "application/json");
            request.addRequestHeader("Accept", "application/json");
            
            client.executeMethod(request);  
            resp = request.getResponseBodyAsString();  
            System.out.println(resp);
            //获取响应
            JSONObject obj = new JSONObject(resp);
            JSONObject data = obj.getJSONObject("data");
            boolean isSuccess = data.getBoolean("success");
            System.out.println(isSuccess);
            return isSuccess;
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        } 
		return false;
	}
	
	/**
	 * 添加群组成员
	 * @param token 令牌
	 * @param groupId 群组id
	 * @param userName 用户名，唯一标识
	 * @return
	 */
	public static boolean AddGroupMember(String token,String groupId,String userName) {
		String  url = REQUEST_URL + "chatgroups/" + groupId + "/users/" + userName;
		
        try {
        	HttpClient client = new HttpClient();
    		PostMethod request = new PostMethod(url);//post请求
    		//添加请求头部
    		request.addRequestHeader("Authorization", "Bearer "+token);
            //request.addRequestHeader("Content-type", "application/json");
            //request.addRequestHeader("Accept", "application/json");
            //进行请求
			client.executeMethod(request);
			//获取响应
 			String response = request.getResponseBodyAsString();
            System.out.println(response);
            //获取响应
			JSONObject obj = new JSONObject(response);
			if (obj.toString().indexOf("true")!=-1) {
				return true;
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 注册IM用户
	 * @param username
	 * @param password 同username
	 * @return
	 */
	public static boolean createUser(String username) {
		String url = REQUEST_URL+"users";
		
		String resp = "";//响应
		try {
            // 构造httprequest设置
            HttpClient client = new HttpClient();
            PostMethod request = new PostMethod(url);
            // 添加request headers
            request.addRequestHeader("Content-type", "application/json");
            request.addRequestHeader("Accept", "application/json");
            //添加request body
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("username", username);
            map.put("password", username);
            String json = new Gson().toJson(map);
            request.setRequestEntity(new ByteArrayRequestEntity(json.getBytes("UTF-8")));  
            
            client.executeMethod(request);  
            resp = request.getResponseBodyAsString();  
            System.out.println(resp);
            //获取响应
			JSONObject obj = new JSONObject(resp);
			if (obj.toString().indexOf("true")!=-1) {
				return true;
			}
			return false;
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        } 
		return false;
	}
	
	/**
	 * 查询用户是否存在
	 * @param token
	 * @param username
	 * @return
	 */
	public static boolean checkUserExist(String token,String username) {
		String resp = "";//响应
        String reqUrl = REQUEST_URL+"users/"+username;
        try {
            // 构造httprequest设置
            HttpClient client = new HttpClient();
            PostMethod request = new PostMethod(reqUrl);
            // 添加equest headers
            request.addRequestHeader("Authorization", "Bearer "+token);
            request.addRequestHeader("Content-type", "application/json");
            request.addRequestHeader("Accept", "application/json");
            
            client.executeMethod(request);  
            resp = request.getResponseBodyAsString();  
            System.out.println(resp);
            //获取响应
            JSONObject obj = new JSONObject(resp);
			if (obj.toString().indexOf("true")!=-1) {
				return true;
			}
			return false;
        } catch (Exception e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        } 
		return false;
	}
	
	/**
	 * 发送文本消息
	 * @param token 令牌
	 * @param content 消息内容
	 * @param targetType 消息类型，users: 给用户发消息。chatgroups: 给群发消息，chatrooms: 给聊天室发消息
	 * @param receiveId 接收id，userId/groupId
	 * @param sendId 发送人id
	 * @param sendHeadpic 发送人头像
	 * @return
	 */
	public static boolean sendTxtMsg(String token,String content,String targetType,String receiveId,String sendId,String sendHeadpic) {
		String resp = "";//响应
        String url = REQUEST_URL+"messages";
        try {
        	// 构造httpRequest设置
            HttpClient client = new HttpClient();
            PostMethod request = new PostMethod(url);
            // 添加request headers
            request.addRequestHeader("Authorization", "Bearer "+token);
            request.addRequestHeader("Content-type", "application/json");
            request.addRequestHeader("Accept", "application/json");
            //添加request body
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("target_type", targetType);//类型
            String[] target = {receiveId};
            map.put("target",target);//接收人
            Map<String, Object> msg = new HashMap<String, Object>();
            msg.put("type", "txt");
            msg.put("msg", content);
            map.put("msg", msg);//消息内容
            map.put("from", sendId);//发送人
            Map<String, Object> ext = new HashMap<String, Object>();
            ext.put("head", sendHeadpic);
            map.put("ext", ext);//拓展内容
            String json = new Gson().toJson(map);
            request.setRequestEntity(new ByteArrayRequestEntity(json.getBytes("UTF-8")));  
            
            client.executeMethod(request);  
            resp = request.getResponseBodyAsString();  
            System.out.println(resp);
            //获取响应
			JSONObject obj = new JSONObject(resp);
			if (obj.toString().indexOf("success")!=-1) {
				return true;
			}
			return false;
		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		}
        return false;
	}
	
	public static void main(String[] args) {
		AccessToken token = getAccessToken();
		//String groupId = createGroup(token,"123", "123", true, false);
		//boolean bool = deleteGroup(groupId, token);
		//boolean bp = createUser("18018266283");
		//boolean ch = checkUserExist(token.getAccess_token(), "ddd");
		boolean ch = sendTxtMsg(token.getAccess_token(), "你好，这是一段中文", "chatgroups", "23869766303746", "18018266280","http://wx.qlogo.cn/mmopen/Oymr0lK8wPJEElJiaLFqc1ia6B2AS3Cjt0vowe9LIF9rbZd78BF1icPCx9PJOmEpTGKcMy94GlMjKxOEGc0XnMtIibibTHoWRLtCH/0");
		//boolean ch = AddGroupMember(token.getAccess_token(), "22705990598667", "18018266280");
		
		System.out.println(ch);
	}
}
