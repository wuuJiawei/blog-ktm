package com.blog.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * ��strǰ����� %
	 * 
	 * @param str
	 * @return
	 */
	public static String appendPerCent(String str) {
		return "'%" + str + "%'";
	}

	/**
	 * ת�����ֱ���
	 * 	ISO-8859-1   -->   UTF-8
	 * @param str
	 * @return
	 */
	public static String encodeStr(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ͨ��������˳�string
	 * @param regex
	 * @param str
	 * @return
	 */
	public static String RegexString(String targetStr, String patternStr) {  
        // ����һ����ʽģ�壬����ʹ��������ʽ����������Ҫץ������  
        // �൱�����������ƥ��ĵط��ͻ����ȥ  
        Pattern pattern = Pattern.compile(patternStr);  
        // ����һ��matcher������ƥ��  
        Matcher matcher = pattern.matcher(targetStr);  
        
        String str="";
        // ����ҵ���  
        for(int i=1;i<=matcher.groupCount();i++){ //����
        	str += matcher.group(i);
    	}  
        return str;
    }
	
	/**
	 * �ж��Ƿ�Ϊ��ĸ+��������
	 * @param str
	 * @return
	 */
	public static boolean isNumberAndWord(String value) {
		/**
			^ ƥ��һ�еĿ�ͷλ��
			(?![0-9]+$) Ԥ���λ�ú��治ȫ������
			(?![a-zA-Z]+$) Ԥ���λ�ú��治ȫ����ĸ
			[0-9A-Za-z] {2,} ����2λ����ĸ�����ֵ����
		 */
		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{2,}$";	
		return value.matches(regex);
	}
	
	public static void main(String[] args) {
		System.out.println(isNumberAndWord("a2"));
	}
}
