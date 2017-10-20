package com.blog.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;



/**
 * �ļ�������
 * @author WuJiaWei
 * @since 2017-02-11 18:45:03
 *
 */
public class FileUtil {

	/** 
	 * ����·��ɾ��ָ����Ŀ¼���ļ������۴������ 
	 * @param sPath  Ҫɾ����Ŀ¼���ļ� 
	 * @return ɾ���ɹ����� true�����򷵻� false�� 
	 */  
	public static boolean DeleteFolder(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // �ж�Ŀ¼���ļ��Ƿ����  
	    if (!file.exists()) {  // �����ڷ��� false  
	        return flag;  
	    } else {  
	        // �ж��Ƿ�Ϊ�ļ�  
	        if (file.isFile()) {  // Ϊ�ļ�ʱ����ɾ���ļ�����  
	            return deleteFile(sPath);  
	        } else {  // ΪĿ¼ʱ����ɾ��Ŀ¼����  
	            return deleteDirectory(sPath);  
	        }  
	    }  
	}  
	
	/** 
	 * ɾ�������ļ� 
	 * @param   sPath    ��ɾ���ļ����ļ��� 
	 * @return �����ļ�ɾ���ɹ�����true�����򷵻�false 
	 */  
	public static boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // ·��Ϊ�ļ��Ҳ�Ϊ�������ɾ��  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}
	
	/** 
	 * ɾ��Ŀ¼�Լ�Ŀ¼�µ��ļ� 
	 * @param  sPath ��ɾ��Ŀ¼���ļ�·�� 
	 * @return  Ŀ¼ɾ���ɹ�����true�����򷵻�false 
	 */  
	public static boolean deleteDirectory(String sPath) {  
	    //���sPath�����ļ��ָ�����β���Զ�����ļ��ָ���  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //���dir��Ӧ���ļ������ڣ����߲���һ��Ŀ¼�����˳�  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    boolean flag = true;  
	    //ɾ���ļ����µ������ļ�(������Ŀ¼)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //ɾ�����ļ�  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //ɾ����Ŀ¼  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //ɾ����ǰĿ¼  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}
	
	/**
	 * ���ͼƬ����
	 * @param suffix ��׺����
	 * @param fileType �ļ����ͣ�0-IMG,1-VIDEO,2-AUDIO
	 * @return
	 */
	public static boolean checkFileSuffix(Integer fileType,MultipartFile file) throws Exception {
		//��ʼ��
        String[] TYPES=new String[10];
        
        switch (fileType) {
		case 0:
			TYPES[0]=".jpg";
			TYPES[1]=".png";
			TYPES[2]=".bmp";
			TYPES[3]=".jpeg";
			TYPES[4]=".gif";
			break;
		case 1:	//AVI��MP4��3gp��3gp2�ȵ� ƻ��ϵ�ֻ�һ��ΪMOV��
			TYPES[0]=".mp4";
			TYPES[1]=".rmvb";
			TYPES[2]=".3gp";
			TYPES[3]=".mov";
			TYPES[4]=".avi";
			TYPES[5]=".ogg";
			break;
		case 2:
			TYPES[0]=".amr";
			TYPES[1]=".wav";
			TYPES[2]=".mp3";
			TYPES[3]=".awb";
			TYPES[4]=".wma";
			TYPES[5]=".ogg";
		default:
			break;
		}
        
        String origName=file.getOriginalFilename();// �ļ�ԭ����
        System.out.println("\n\n\n"+origName+"\n\n\n");
        
		String type=origName.substring(origName.lastIndexOf("."));
		type = type.toLowerCase();
        
        boolean isTypes=false;
        for (int i = 0; i < TYPES.length; i++) {
			if (TYPES!=null&&type.equals(TYPES[i])) {
				isTypes=true;
				break;
			}
		}
        
        return isTypes;
	}
	
	/**
   	 * �ϴ�ͼƬ
   	 * @param request ����
   	 * @param path_deposit ���λ��(·��)
   	 * @param file �ļ�
   	 * @param isRandomName �Ƿ��������
   	 * @return �����ļ�·��
   	 */
   	public static String uploadImage(HttpServletRequest request,String path_deposit,MultipartFile file,boolean isRandomName) {
   		try {
   			if(file!=null){
   				String origName=file.getOriginalFilename();// �ļ�ԭ����
   				String type=origName.substring(origName.lastIndexOf("."));
   				type = type.toLowerCase();
				//���ͼƬ�ļ���·��
   				String path_deposit_new = path_deposit;
				String path=request.getSession().getServletContext().getRealPath(path_deposit_new);
				//�������
				String fileSrc=""; 
				//�Ƿ��������
				if(isRandomName){
					origName=UUID.randomUUID().toString().replaceAll("-", "")+type;
				}
		        
				File targetFile = new File(path, origName);  
		        if(!targetFile.exists()){
		        	targetFile.mkdirs();//����Ŀ¼
		        }
		        //�ϴ�
		        file.transferTo(targetFile);
		        //�µ�·��
		        fileSrc = path_deposit + origName;
		        return fileSrc;
			}
   			return null;
   		}catch (Exception e) {
   			e.printStackTrace();
   			return null;
   		}
   	}
   	
   	public static void download(String urlString, String filename,String savePath) throws Exception {  
        // ����URL  
        URL url = new URL(urlString);  
        // ������  
        URLConnection con = url.openConnection();  
        //��������ʱΪ5s  
        con.setConnectTimeout(5*1000);  
        // ������  
        InputStream is = con.getInputStream();  
      
        // 1K�����ݻ���  
        byte[] bs = new byte[1024];  
        // ��ȡ�������ݳ���  
        int len;  
        // ������ļ���  
       File sf=new File(savePath);  
       if(!sf.exists()){  
           sf.mkdirs();  
       }  
       OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);  
        // ��ʼ��ȡ  
        while ((len = is.read(bs)) != -1) {  
          os.write(bs, 0, len);  
        }  
        // ��ϣ��ر���������  
        os.close();  
        is.close();  
    }   
   	
   	public static void main(String[] args) throws Exception {
		download("http://himg2.huanqiu.com/attachment2010/2016/0729/20/10/20160729081050166.jpg", "aaa.jpg", "D://");
	}
}
