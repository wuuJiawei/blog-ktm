package com.blog.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Upload {
	// ˽�л�������
    private Base64Upload() {
    }

    // ���ȶ���һ��������Ÿ����ʵ��
    private static Base64Upload fileBase64 = null;

    // ���Ⱪ¶һ����̬������ȡ�����ʵ��
    public static Base64Upload getFileBase64() {
        if (fileBase64 == null) {
            fileBase64 = new Base64Upload();
        }
        return fileBase64;
    }

    // �� file ת��Ϊ Base64
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
            throw new RuntimeException("�ļ�·����Ч\n" + e.getMessage());
        }
    }

    // �� base64 ת��Ϊ file
    public boolean base64ToFile(String base64, String path) {
        byte[] buffer;
        try {
            buffer = new BASE64Decoder().decodeBuffer(base64);
            FileOutputStream out = new FileOutputStream(path);
            out.write(buffer);
            out.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("base64�ַ����쳣���ַ�쳣\n" + e.getMessage());
        }
    }
}
