package com.blog.controller;

import io.upyun.UpYun;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.blog.model.url.RequestUrl;
import com.blog.util.PropertiesUtil;
import com.blog.util.ResultBean;

@Controller
public class UploadController extends BaseController{

	/**
	 * 文件上传
	 * *又拍云
	 * @param image
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(RequestUrl.admin_upload)
	@ResponseBody
	public ResultBean upload(
			@RequestParam(value="image",required=false)MultipartFile file,
			HttpServletRequest request,
			HttpServletResponse response
			) {
		try {
			//获取又拍云配置信息
			Map<String, String> propMap = PropertiesUtil.getInstance().getPropMap();
			String bucketName = propMap.get("upyun.bucketName");
			String userName = propMap.get("upyun.userName");
			String password = propMap.get("upyun.password");
			String url = propMap.get("upyun.url");
			
			UpYun upyun = new UpYun(bucketName, userName, password);//实例化
			
			String origName=file.getOriginalFilename();// 文件原名称
			String type=origName.substring(origName.lastIndexOf("."));
			origName=UUID.randomUUID().toString().replaceAll("-", "")+type;
			boolean flag = upyun.writeFile(origName, file);
			if (flag) {
				return setResultBean(0, "操作成功", url + "/" + origName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return setResultBean(401,"上传失败",null);
	}
	
	@RequestMapping("m/admin/upload/2")
	@ResponseBody
	public ResultBean upload_request(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String path = request.getRealPath("/image");
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        String fileName = "";// 文件名称
        
        
        /**上传文件处理内容**/
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        sfu.setHeaderEncoding("UTF-8"); // 处理中文问题
        sfu.setSizeMax(1024 * 1024); // 限制文件大小
        try {
            List<FileItem> fileItems = sfu.parseRequest(request); // 解码请求
            for (FileItem fi : fileItems) {
                fileName = UUID.randomUUID()+fi.getName().substring(fi.getName().lastIndexOf("."),fi.getName().length());
                fi.write(new File(path, fileName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**********************/

        //获取图片url地址
        String imgUrl = "http://localhost:8080/image/" + fileName;
        
        return setResultBean(0, "success", imgUrl);
	}
}
