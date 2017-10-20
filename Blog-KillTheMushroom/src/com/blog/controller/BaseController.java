package com.blog.controller;

import javax.jms.Session;
import javax.servlet.http.HttpSession;

import com.blog.model.User;
import com.blog.util.ResultBean;

public class BaseController {

	/**
	 * 获取当前用户
	 * @param session
	 * @return
	 */
	protected User getCurrentUser(HttpSession session) {
		User user = (User)session.getAttribute("USER_ONLINE");
		return user;
	}
	
	/**
	 * 保存当前用户到session
	 * @param session
	 * @param user
	 */
	protected void setCurrentUser(HttpSession session,User user){
		session.setAttribute("USER_ONLINE", user);
	}
	
	/**
	 * 返回JSON
	 * @param code
	 * @param msg
	 * @return
	 */
	protected ResultBean setResultBean(int code,String msg,Object obj) {
		String msg_copy = null;
		switch (code) {
		case 0:
			msg_copy = "操作成功";
			break;
		case 110:
			msg_copy = "操作失败";
			break;
		case 120:
			msg_copy = "服务端出现异常";
			break;
		default:
			break;
		}
		
		if (msg==null) {
			msg = msg_copy;
		}
		return new ResultBean(code, msg, obj);
	}
}
