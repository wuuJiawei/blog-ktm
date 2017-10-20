package com.blog.util.sms;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONObject;

/**
 * 短信API服务调用示例代码 － 聚合数据 在线接口文档：http://www.juhe.cn/docs/54
 **/

public class JuHeSmsUtils {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	// 配置您申请的KEY
	public static final String APPKEY = "5e3eb663d08be1588221ee97f6feb20d";

	public static String mobileNo = "18018266280";// 短信接收的手机号码

	public static void main(String[] args) {
		//sendMsgToPay("18018266280", "武佳伟", "坚果Pro细红线版", "500");
		sendCode("18018266280");
	}
	
	// 1.屏蔽词检查测
	public static void getRequest1() {
		String result = null;
		String url = "http://v.juhe.cn/sms/black";// 请求接口地址
		Map<String, Object> params = new HashMap<String, Object> ();// 请求参数
		params.put("word", "");// 需要检测的短信内容，需要UTF8 URLENCODE
		params.put("key", APPKEY);// 应用APPKEY(应用详细页查询)

		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.fromObject(result);
			if (object.getInt("error_code") == 0) {
				System.out.println(object.get("result"));
			} else {
				System.out.println(object.get("error_code") + ":"
						+ object.get("reason"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送验证码
	 * 
	 * @param tel
	 * @return
	 */
	public static String sendCode(String tel) {
		String result = null;
		String code = makeValidateCode(4);
		String url = "http://v.juhe.cn/sms/send";// 请求接口地址
		Map<String, Object> params = new HashMap<String, Object>();// 请求参数
		params.put("mobile", tel);// 接收短信的手机号码
		params.put("tpl_id", 38635);// 短信模板ID，请参考个人中心短信模板设置
		params.put("tpl_value", "#code#=" + code);// 变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<
		params.put("key", APPKEY);// 应用APPKEY(应用详细页查询)
		params.put("dtype", "json");// 返回数据的格式,xml或json，默认json

		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.fromObject(result);
			if (object.getInt("error_code") == 0) {
				System.out.println(object.get("result"));
				return code;
			} else {
				System.out.println(object.get("error_code") + ":"
						+ object.get("reason"));
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	/**
	 * 发送支付短信
	 * 
	 * @param phone
	 * @param username
	 * @param productName
	 * @param money
	 *            余额
	 * @return
	 */
	public static boolean sendMsgToPay(String phone, String username,
			String productName, String money) {
		String url = "http://v.juhe.cn/sms/send";// 请求接口地址
		Map<String, Object> params = new HashMap<String, Object>();// 请求参数
		params.put("mobile", phone);// 接收短信的手机号码
		params.put("tpl_id", 38694);// 短信模板ID，请参考个人中心短信模板设置
		params.put("tpl_value", "#name#=" + username + "&#goods#="
				+ productName + "&#money#=" + money);// 变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a
														// href="http://www.juhe.cn/news/index/id/50"
														// target="_blank">详细说明></a>
		params.put("key", APPKEY);// 应用APPKEY(应用详细页查询)
		params.put("dtype", "json");// 返回数据的格式,xml或json，默认json

		try {
			String result = net(url, params, "GET");
			JSONObject object = JSONObject.fromObject(result);
			if (object.getInt("error_code") == 0) {
				System.out.println(object.get("result"));
				return true;
			} else {
				System.out.println(object.get("error_code") + ":"
						+ object.get("reason"));
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 产生随机数
	 * 
	 * @param bits
	 *            位数
	 * @return
	 */
	public static String makeValidateCode(int bits) {
		Random random = new Random();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < bits; i++) {
			list.add(random.nextInt(10));
		}
		StringBuffer sBuffer = new StringBuffer();
		for (Integer item : list) {
			sBuffer.append(item);
		}

		return sBuffer.toString();
	}


	/**
	 * 身份证查询
	 * 
	 * @param cardno
	 * @return
	 */
	public static String checkIDCard(String cardno) {
		String tempResult = null;
		String result = null;
		String url = "http://apis.juhe.cn/idcard/index";// 请求接口地址
		Map<String, Object> params = new HashMap<String, Object>();// 请求参数
		params.put("cardno", cardno);// 身份证号码
		params.put("dtype", "json");// 返回数据格式：json或xml,默认json
		params.put("key", APPKEY);// 你申请的key

		try {
			result = net(url, params, "GET");
			JSONObject object = JSONObject.fromObject(result);
			if (object.getInt("error_code") == 0) {
				tempResult = object.toString();
			} else {
				tempResult = "error";
				// System.out.println(object.get("error_code")+":"+object.get("reason"));
			}
		} catch (Exception e) {
			tempResult = "error";
			e.printStackTrace();
		}
		return tempResult;

	}

	/**
	 * 
	 * @param strUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param method
	 *            请求方法
	 * @return 网络请求字符串
	 * @throws Exception
	 */
	public static String net(String strUrl, Map<String,Object> params, String method)
			throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(
							conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}

	// 将map型转为请求参数型
	public static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=")
						.append(URLEncoder.encode(i.getValue() + "", "UTF-8"))
						.append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}