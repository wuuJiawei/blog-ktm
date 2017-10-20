package com.blog.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.model.Article;
import com.blog.model.url.RequestUrl;
import com.blog.service.IArticleService;

@Controller
public class IndexController{

	@Autowired
	private IArticleService articleService;
	
	@RequestMapping(RequestUrl.admin_index)
	public String toPage() {
		return "admin/index";
	}
	
	//后台-主页
	@RequestMapping(RequestUrl.admin_homepage)
	public String toHomepage(HttpSession session) {
		return "admin/index_homepage";
	}
	
	/**
	 * 博客的首页
	 * @param request
	 * @return
	 */
	@RequestMapping(RequestUrl.blog_index)
	public String toBlogIndex(HttpServletRequest request) {
		Article article = articleService.get4Index();
		request.setAttribute("article", article);
		return "blog/index";
	}
}
