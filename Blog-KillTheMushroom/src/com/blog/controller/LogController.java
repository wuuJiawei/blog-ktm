package com.blog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.model.User;
import com.blog.model.url.RequestUrl;
import com.blog.service.IUserService;
import com.blog.util.IPUtil;
import com.blog.util.ResultBean;


@Controller
public class LogController extends BaseController{

	@Autowired
	private IUserService userService;
	
	@RequestMapping(RequestUrl.blog_login)
	public String toLoginPage() {
		return "blog/login";
	}
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(RequestUrl.public_login)
	@ResponseBody
	public ResultBean login(
			String username,
			String password,
			HttpSession session,
			HttpServletRequest request
			) {
		//登录验证
		User user = userService.login(username, password);
		if (user==null) {
			return setResultBean(926, "用户名或密码错误",null);
		}
		
		//更新信息
		user.setLastLoginIp(IPUtil.getIpAddr(request));//最后登录ip
		userService.updateByPrimaryKeySelective(user);
		
		setCurrentUser(session, user);
		return setResultBean(0, null,null);
	}
	
	@RequestMapping(RequestUrl.blog_register)
	public String toRegisterPage() {
		return "blog/register";
	}
	
	/**
	 * 注册
	 * @param record
	 * @param request
	 * @return
	 */
	@RequestMapping(RequestUrl.public_register)
	public ResultBean register(User record,HttpServletRequest request) {
		//检查用户名是否重复
		User user = userService.login(record.getUsername(), null);
		if (user!=null) {
			return setResultBean(926, "用户名已存在",null);
		}
		
		record.setFreeze(0);//0-未冻结
		record.setRegisterIp(IPUtil.getIpAddr(request));
		record.setRegisterTime(new Date());
		record.setUserType(1);
		int num = userService.insertSelective(record);
		if (num>0) {
			setCurrentUser(request.getSession(), user);
			return setResultBean(0, null,null);
		}
		return setResultBean(110, null,null);
	}
	
	/**
	 * 注销
	 * @param session
	 * @return
	 */
	@RequestMapping(RequestUrl.public_logout)
	public String logout(HttpSession session) {
		session.removeAttribute("USER_ONLINE");
		session.invalidate();
		return "redirect:/"+RequestUrl.blog_index;
	}
}

