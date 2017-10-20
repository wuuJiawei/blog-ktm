package com.blog.model.url;

public class RequestUrl {
	
	
	
	/***********分割****************/
	

	//后台controller地址前缀
	public static final String adminUrl = "m/admin/";
	//后台首页
	public static final String admin_index = adminUrl + "index";
	//后台主页
	public static final String admin_homepage = adminUrl + "homepage";
	//后台文件上传
	public static final String admin_upload = adminUrl + "upload";
	//编辑文章
	public static final String admin_article_edit = adminUrl + "article/edit";
	//文章列表
	public static final String admin_article = adminUrl + "article/list";
	//文章详情
	public static final String admin_article_detail = adminUrl + "article/detail";
		
	
	/********** ↓ 博客 ↓ *****************/
	
	//博客首页
	public static final String blog_index = "index";
	//博客登录
	public static final String blog_login = "login";
	//博客注册
	public static final String blog_register = "register";
	//博客聊天室
	public static final String blog_chatroom = "chatroom";
	
	
	/********** ↓ 公共的请求方法  ↓  *****************/
	 
	//登录
	public static final String public_login = "login/do";
	//注册
	public static final String public_register = "register/do";
	//注销
	public static final String public_logout = "logout";
}
