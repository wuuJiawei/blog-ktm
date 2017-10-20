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
 * 文件操作类
 * @author WuJiaWei
 * @since 2017-02-11 18:45:03
 *
 */
public class FileUtil {

	/** 
	 * 根据路径删除指定的目录或文件，无论存在与否 
	 * @param sPath  要删除的目录或文件 
	 * @return 删除成功返回 true，否则返回 false。 
	 */  
	public static boolean DeleteFolder(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 判断目录或文件是否存在  
	    if (!file.exists()) {  // 不存在返回 false  
	        return flag;  
	    } else {  
	        // 判断是否为文件  
	        if (file.isFile()) {  // 为文件时调用删除文件方法  
	            return deleteFile(sPath);  
	        } else {  // 为目录时调用删除目录方法  
	            return deleteDirectory(sPath);  
	        }  
	    }  
	}  
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}
	
	/** 
	 * 删除目录以及目录下的文件 
	 * @param  sPath 被删除目录的文件路径 
	 * @return  目录删除成功返回true，否则返回false 
	 */  
	public static boolean deleteDirectory(String sPath) {  
	    //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
	    if (!sPath.endsWith(File.separator)) {  
	        sPath = sPath + File.separator;  
	    }  
	    File dirFile = new File(sPath);  
	    //如果dir对应的文件不存在，或者不是一个目录，则退出  
	    if (!dirFile.exists() || !dirFile.isDirectory()) {  
	        return false;  
	    }  
	    boolean flag = true;  
	    //删除文件夹下的所有文件(包括子目录)  
	    File[] files = dirFile.listFiles();  
	    for (int i = 0; i < files.length; i++) {  
	        //删除子文件  
	        if (files[i].isFile()) {  
	            flag = deleteFile(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        } //删除子目录  
	        else {  
	            flag = deleteDirectory(files[i].getAbsolutePath());  
	            if (!flag) break;  
	        }  
	    }  
	    if (!flag) return false;  
	    //删除当前目录  
	    if (dirFile.delete()) {  
	        return true;  
	    } else {  
	        return false;  
	    }  
	}
	
	/**
	 * 检查图片类型
	 * @param suffix 后缀名称
	 * @param fileType 文件类型：0-IMG,1-VIDEO,2-AUDIO
	 * @return
	 */
	public static boolean checkFileSuffix(Integer fileType,MultipartFile file) throws Exception {
		//初始化
        String[] TYPES=new String[10];
        
        switch (fileType) {
		case 0:
			TYPES[0]=".jpg";
			TYPES[1]=".png";
			TYPES[2]=".bmp";
			TYPES[3]=".jpeg";
			TYPES[4]=".gif";
			break;
		case 1:	//AVI、MP4、3gp、3gp2等等 苹果系手机一般为MOV。
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
        
        String origName=file.getOriginalFilename();// 文件原名称
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
   	 * 上传图片
   	 * @param request 请求
   	 * @param path_deposit 存放位置(路径)
   	 * @param file 文件
   	 * @param isRandomName 是否随机名称
   	 * @return 完整文件路径
   	 */
   	public static String uploadImage(HttpServletRequest request,String path_deposit,MultipartFile file,boolean isRandomName) {
   		try {
   			if(file!=null){
   				String origName=file.getOriginalFilename();// 文件原名称
   				String type=origName.substring(origName.lastIndexOf("."));
   				type = type.toLowerCase();
				//存放图片文件的路径
   				String path_deposit_new = path_deposit;
				String path=request.getSession().getServletContext().getRealPath(path_deposit_new);
				//组合名称
				String fileSrc=""; 
				//是否随机名称
				if(isRandomName){
					origName=UUID.randomUUID().toString().replaceAll("-", "")+type;
				}
		        
				File targetFile = new File(path, origName);  
		        if(!targetFile.exists()){
		        	targetFile.mkdirs();//创建目录
		        }
		        //上传
		        file.transferTo(targetFile);
		        //新的路径
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
        // 构造URL  
        URL url = new URL(urlString);  
        // 打开连接  
        URLConnection con = url.openConnection();  
        //设置请求超时为5s  
        con.setConnectTimeout(5*1000);  
        // 输入流  
        InputStream is = con.getInputStream();  
      
        // 1K的数据缓冲  
        byte[] bs = new byte[1024];  
        // 读取到的数据长度  
        int len;  
        // 输出的文件流  
       File sf=new File(savePath);  
       if(!sf.exists()){  
           sf.mkdirs();  
       }  
       OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);  
        // 开始读取  
        while ((len = is.read(bs)) != -1) {  
          os.write(bs, 0, len);  
        }  
        // 完毕，关闭所有链接  
        os.close();  
        is.close();  
    }   
   	
   	public static void main(String[] args) throws Exception {
		download("http://himg2.huanqiu.com/attachment2010/2016/0729/20/10/20160729081050166.jpg", "aaa.jpg", "D://");
	}
}
