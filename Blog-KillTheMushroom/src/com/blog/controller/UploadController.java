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
	 * �ļ��ϴ�
	 * *������
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
			//��ȡ������������Ϣ
			Map<String, String> propMap = PropertiesUtil.getInstance().getPropMap();
			String bucketName = propMap.get("upyun.bucketName");
			String userName = propMap.get("upyun.userName");
			String password = propMap.get("upyun.password");
			String url = propMap.get("upyun.url");
			
			UpYun upyun = new UpYun(bucketName, userName, password);//ʵ����
			
			String origName=file.getOriginalFilename();// �ļ�ԭ����
			String type=origName.substring(origName.lastIndexOf("."));
			origName=UUID.randomUUID().toString().replaceAll("-", "")+type;
			boolean flag = upyun.writeFile(origName, file);
			if (flag) {
				return setResultBean(0, "�����ɹ�", url + "/" + origName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return setResultBean(401,"�ϴ�ʧ��",null);
	}
	
	@RequestMapping("m/admin/upload/2")
	@ResponseBody
	public ResultBean upload_request(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String path = request.getRealPath("/image");
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
        String fileName = "";// �ļ�����
        
        
        /**�ϴ��ļ���������**/
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        sfu.setHeaderEncoding("UTF-8"); // ������������
        sfu.setSizeMax(1024 * 1024); // �����ļ���С
        try {
            List<FileItem> fileItems = sfu.parseRequest(request); // ��������
            for (FileItem fi : fileItems) {
                fileName = UUID.randomUUID()+fi.getName().substring(fi.getName().lastIndexOf("."),fi.getName().length());
                fi.write(new File(path, fileName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**********************/

        //��ȡͼƬurl��ַ
        String imgUrl = "http://localhost:8080/image/" + fileName;
        
        return setResultBean(0, "success", imgUrl);
	}
}
