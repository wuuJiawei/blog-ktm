package com.blog.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	/**
	 * 在str前后添加 %
	 * 
	 * @param str
	 * @return
	 */
	public static String appendPerCent(String str) {
		return "'%" + str + "%'";
	}

	/**
	 * 转换文字编码
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
	 * 通过正则过滤出string
	 * @param regex
	 * @param str
	 * @return
	 */
	public static String RegexString(String targetStr, String patternStr) {  
        // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容  
        // 相当于埋好了陷阱匹配的地方就会掉下去  
        Pattern pattern = Pattern.compile(patternStr);  
        // 定义一个matcher用来做匹配  
        Matcher matcher = pattern.matcher(targetStr);  
        
        String str="";
        // 如果找到了  
        for(int i=1;i<=matcher.groupCount();i++){ //搜索
        	str += matcher.group(i);
    	}  
        return str;
    }
	
	/**
	 * 判断是否为字母+数组的组合
	 * @param str
	 * @return
	 */
	public static boolean isNumberAndWord(String value) {
		/**
			^ 匹配一行的开头位置
			(?![0-9]+$) 预测该位置后面不全是数字
			(?![a-zA-Z]+$) 预测该位置后面不全是字母
			[0-9A-Za-z] {2,} 大于2位的字母与数字的组合
		 */
		String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{2,}$";	
		return value.matches(regex);
	}
	
	public static void main(String[] args) {
		System.out.println(isNumberAndWord("a2"));
	}
}
