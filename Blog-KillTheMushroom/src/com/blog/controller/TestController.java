package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

	@RequestMapping("index")
	public String test() {
		return "test/index";
	}
	
	@RequestMapping("upload")
	public String testUpload() {
		return "test/upload";
	}
}
