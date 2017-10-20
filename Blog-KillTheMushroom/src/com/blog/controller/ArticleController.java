package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.model.url.RequestUrl;

@Controller
public class ArticleController extends BaseController{

	@RequestMapping(RequestUrl.admin_article_edit)
	public String toArticleEdit() {
		return "blog/article_edit";
	}
	
	@RequestMapping(RequestUrl.admin_article)
	public String toArticleList() {
		return "blog/article_list";
	}
	
	@RequestMapping(RequestUrl.admin_article_detail)
	public String toArticleDetail() {
		return "blog/article_detail";
	}
}
