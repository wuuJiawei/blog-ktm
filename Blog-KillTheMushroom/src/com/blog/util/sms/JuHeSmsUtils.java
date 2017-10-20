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
 * ����API�������ʾ������ �� �ۺ����� ���߽ӿ��ĵ���http://www.juhe.cn/docs/54
 **/

public class JuHeSmsUtils {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	// �����������KEY
	public static final String APPKEY = "5e3eb663d08be1588221ee97f6feb20d";

	public static String mobileNo = "18018266280";// ���Ž��յ��ֻ�����

	public static void main(String[] args) {
		//sendMsgToPay("18018266280", "���ΰ", "���Proϸ���߰�", "500");
		sendCode("18018266280");
	}
	
	// 1.���δʼ���
	public static void getRequest1() {
		String result = null;
		String url = "http://v.juhe.cn/sms/black";// ����ӿڵ�ַ
		Map<String, Object> params = new HashMap<String, Object> ();// �������
		params.put("word", "");// ��Ҫ���Ķ������ݣ���ҪUTF8 URLENCODE
		params.put("key", APPKEY);// Ӧ��APPKEY(Ӧ����ϸҳ��ѯ)

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
	 * ������֤��
	 * 
	 * @param tel
	 * @return
	 */
	public static String sendCode(String tel) {
		String result = null;
		String code = makeValidateCode(4);
		String url = "http://v.juhe.cn/sms/send";// ����ӿڵ�ַ
		Map<String, Object> params = new HashMap<String, Object>();// �������
		params.put("mobile", tel);// ���ն��ŵ��ֻ�����
		params.put("tpl_id", 38635);// ����ģ��ID����ο��������Ķ���ģ������
		params.put("tpl_value", "#code#=" + code);// �������ͱ���ֵ�ԡ������ı��������߱���ֵ�д���#&=�е�����һ��������ţ����ȷֱ����urlencode������ٴ��ݣ�<
		params.put("key", APPKEY);// Ӧ��APPKEY(Ӧ����ϸҳ��ѯ)
		params.put("dtype", "json");// �������ݵĸ�ʽ,xml��json��Ĭ��json

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
	 * ����֧������
	 * 
	 * @param phone
	 * @param username
	 * @param productName
	 * @param money
	 *            ���
	 * @return
	 */
	public static boolean sendMsgToPay(String phone, String username,
			String productName, String money) {
		String url = "http://v.juhe.cn/sms/send";// ����ӿڵ�ַ
		Map<String, Object> params = new HashMap<String, Object>();// �������
		params.put("mobile", phone);// ���ն��ŵ��ֻ�����
		params.put("tpl_id", 38694);// ����ģ��ID����ο��������Ķ���ģ������
		params.put("tpl_value", "#name#=" + username + "&#goods#="
				+ productName + "&#money#=" + money);// �������ͱ���ֵ�ԡ������ı��������߱���ֵ�д���#&=�е�����һ��������ţ����ȷֱ����urlencode������ٴ��ݣ�<a
														// href="http://www.juhe.cn/news/index/id/50"
														// target="_blank">��ϸ˵��></a>
		params.put("key", APPKEY);// Ӧ��APPKEY(Ӧ����ϸҳ��ѯ)
		params.put("dtype", "json");// �������ݵĸ�ʽ,xml��json��Ĭ��json

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
	 * ���������
	 * 
	 * @param bits
	 *            λ��
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
	 * ���֤��ѯ
	 * 
	 * @param cardno
	 * @return
	 */
	public static String checkIDCard(String cardno) {
		String tempResult = null;
		String result = null;
		String url = "http://apis.juhe.cn/idcard/index";// ����ӿڵ�ַ
		Map<String, Object> params = new HashMap<String, Object>();// �������
		params.put("cardno", cardno);// ���֤����
		params.put("dtype", "json");// �������ݸ�ʽ��json��xml,Ĭ��json
		params.put("key", APPKEY);// �������key

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
	 *            �����ַ
	 * @param params
	 *            �������
	 * @param method
	 *            ���󷽷�
	 * @return ���������ַ���
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

	// ��map��תΪ���������
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