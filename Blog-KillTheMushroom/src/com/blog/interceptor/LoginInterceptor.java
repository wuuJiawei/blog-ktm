package com.blog.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import schemasMicrosoftComOfficeOffice.STInsetMode;

/*
 * 登录认证的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	
	/**
	 * 执行Handle方法之前执行
	 * 用于身份认证
	 * 认证通过表示当前用户没有登录，需要此方法拦截不再向下执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		/*//获取请求的url
		String url = request.getRequestURI();

		//接口直接放行
		if (url.indexOf("m/admin/")==-1) { 
			return true;//直接放行
		}
		
		//登录页直接放行
		if(url.indexOf("log")>=0){
			//认证通过，放行
			return true;
		}
		
		//获取session
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("ADMIN");
		
		//已登录直接放行
		if (admin!=null) {
			return true;
		}
		
		//执行到这里说明需要进行登录，跳转到登录页面
		response.sendRedirect("log/to");
		//rquest.getRequestDispatcher("m/log/display").forward(request, response);
		
		//return false表示拦截，不向下执行  
        //return true表示放行   
		return false;*/
		
		return true;
	}

}
