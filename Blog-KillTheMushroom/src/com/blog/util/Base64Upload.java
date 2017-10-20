package com.blog.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Upload {
	// 私有化构造器
    private Base64Upload() {
    }

    // 事先定义一个变量存放该类的实例
    private static Base64Upload fileBase64 = null;

    // 对外暴露一个静态方法获取该类的实例
    public static Base64Upload getFileBase64() {
        if (fileBase64 == null) {
            fileBase64 = new Base64Upload();
        }
        return fileBase64;
    }

    // 将 file 转化为 Base64
    public String fileToBase64(String path) {
        File file = new File(path);
        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return new BASE64Encoder().encode(buffer);
        } catch (Exception e) {
            throw new RuntimeException("文件路径无效\n" + e.getMessage());
        }
    }

    // 将 base64 转化为 file
    public boolean base64ToFile(String base64, String path) {
        byte[] buffer;
        try {
            buffer = new BASE64Decoder().decodeBuffer(base64);
            FileOutputStream out = new FileOutputStream(path);
            out.write(buffer);
            out.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("base64字符串异常或地址异常\n" + e.getMessage());
        }
    }
}
