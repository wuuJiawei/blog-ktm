package io.upyun;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.blog.util.PropertiesUtil;

public class TestUpYun {

	public static void main(String[] args) {
		/*File file = new File("C:/Users/12093_000/Pictures/mmexport1475557890121.jpg").;
		
		//获取又拍云配置信息
		Map<String, String> propMap = PropertiesUtil.getInstance().getPropMap();
		String bucketName = propMap.get("upyun.bucketName");
		String userName = propMap.get("upyun.userName");
		String password = propMap.get("upyun.password");
		
		UpYun upyun = new UpYun(bucketName, userName, password);//实例化
		
		String origName=file.getOriginalFilename();// 文件原名称
		String type=origName.substring(origName.lastIndexOf("."));
		origName=UUID.randomUUID().toString().replaceAll("-", "")+type;
		boolean flag = upyun.writeFile(origName, file);
		
		return null;*/
		
	}
}
