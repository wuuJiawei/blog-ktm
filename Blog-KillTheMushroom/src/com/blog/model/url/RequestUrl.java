package com.blog.model.url;

public class RequestUrl {
	
	
	
	/***********�ָ�****************/
	

	//��̨controller��ַǰ׺
	public static final String adminUrl = "m/admin/";
	//��̨��ҳ
	public static final String admin_index = adminUrl + "index";
	//��̨��ҳ
	public static final String admin_homepage = adminUrl + "homepage";
	//��̨�ļ��ϴ�
	public static final String admin_upload = adminUrl + "upload";
	//�༭����
	public static final String admin_article_edit = adminUrl + "article/edit";
	//�����б�
	public static final String admin_article = adminUrl + "article/list";
	//��������
	public static final String admin_article_detail = adminUrl + "article/detail";
		
	
	/********** �� ���� �� *****************/
	
	//������ҳ
	public static final String blog_index = "index";
	//���͵�¼
	public static final String blog_login = "login";
	//����ע��
	public static final String blog_register = "register";
	//����������
	public static final String blog_chatroom = "chatroom";
	
	
	/********** �� ���������󷽷�  ��  *****************/
	 
	//��¼
	public static final String public_login = "login/do";
	//ע��
	public static final String public_register = "register/do";
	//ע��
	public static final String public_logout = "logout";
}
