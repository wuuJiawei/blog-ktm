package com.blog.util;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.stereotype.Component;


/**
 * HTTPͨ����
 * @ClassName: HttpConnection
 * @Description: TODO
 * @author �˹�ΰ(����)
 * @date 2015��04��18��
 *
 */
@Component
public class HttpConnection {
	/** 
     * ��ָ��URL����GET���������� 
     * @param url ���������URL 
     * @param param ����������������Ӧ����name1=value1&name2=value2����ʽ�� 
     * @return URL������Զ����Դ����Ӧ 
     */ 
	public  String get(String url, String param) throws Exception {  
        String urlName = url + "?" + param;  
        return get(urlName);
    }  
	
	/**
	 * ��ָ��URL����GET����������
	 * 
	 * @param url
	 *            ���������URL
	 * @return URL������Զ����Դ����Ӧ
	 */
	public  String get(String url) throws Exception {
		String result = "";
		BufferedReader in = null;
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
		conn.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
		conn.setRequestProperty("connection", "keep-alive");
		conn.setRequestProperty("user-agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		// ����ʵ�ʵ�����
		conn.connect();

		// ����BufferedReader����������ȡURL����Ӧ
		in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		String line;
		while ((line = in.readLine()) != null) {
			result += line;
		}
		
		in.close();
		return result;
	}
	
	/**  
     * ��ָ��URL����POST����������  
     * @param url ���������URL  
     * @param content ����
     * @return URL������Զ����Դ����Ӧ  
	 * @throws Exception 
     */  
    public String post(String url,String content) throws Exception{
    	String result = "";
        URL postUrl = new URL(url); 
        HttpURLConnection connection = (HttpURLConnection) postUrl 
                .openConnection(); 
        connection.setDoOutput(true); 
        connection.setDoInput(true); 
        connection.setRequestMethod("POST"); 
        connection.setUseCaches(false); 
        connection.setInstanceFollowRedirects(true); 
        connection.setRequestProperty("Content-Type", 
                "application/x-www-form-urlencoded"); 
        connection.connect(); 
        DataOutputStream out = new DataOutputStream(connection 
                .getOutputStream()); 
//        out.writeBytes(content); 
        out.write(content.getBytes("UTF-8"));
        out.flush(); 
        out.close(); // flush and close 
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));//���ñ���,������������ 
        String line=""; 
        while ((line = reader.readLine()) != null){ 
        	result += line;
        } 
        reader.close(); 
        connection.disconnect();
		return result; 
    }
    /**
     * ��ָ��URL����POST����������
     * @Title: post
     * @Description: TODO
     * @param @param url
     * @param @param textMap
     * @param @return    
     * @return String    
     * @throws
     */
    public String post(String url, Map<String, String> textMap){
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------123821742118716"; //boundary����requestͷ���ϴ��ļ����ݵķָ���
		try {
			URL postUrl = new URL(url);
			conn = (HttpURLConnection) postUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
							"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// text
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append(
							"\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// ��ȡ��������
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("����POST�������" + url);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}
    
    /**  
     * ��ָ��URL����POST���������� �����ļ��� 
     * @param url ���������URL  
     * @param textMap �ı�������ֵ
     * @param fileMap �ļ���ֵ
     * @return URL������Զ����Դ����Ӧ  
	 * @throws Exception 
     */  
    public String filePost(String url, Map<String, String> textMap,
			Map<String, String> fileMap) {
		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------123821742118716"; //boundary����requestͷ���ϴ��ļ����ݵķָ���
		try {
			URL postUrl = new URL(url);
			conn = (HttpURLConnection) postUrl.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
							"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// text
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append(
							"\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}

			// file
			if (fileMap != null) {
				Iterator iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					File file = new File(inputValue);
					String filename = file.getName();
					String contentType = new MimetypesFileTypeMap()
							.getContentType(file);
					if (filename.endsWith(".png")) {
						contentType = "image/png";
					}
					if (contentType == null || contentType.equals("")) {
						contentType = "application/octet-stream";
					}

					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append(
							"\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

					out.write(strBuf.toString().getBytes());

					DataInputStream in = new DataInputStream(
							new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// ��ȡ��������
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("����POST�������" + url);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}
}