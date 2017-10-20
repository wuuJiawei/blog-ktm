package com.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.model.url.RequestUrl;

@Controller
public class ChatRoomController extends BaseController{

	@RequestMapping(RequestUrl.blog_chatroom)
	public String toPage() {
		return "blog/chatroom";
	}
	
}
