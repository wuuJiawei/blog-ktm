package com.blog.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import schemasMicrosoftComOfficeOffice.STInsetMode;

/*
 * ��¼��֤��������
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
	 * ִ��Handle����֮ǰִ��
	 * ���������֤
	 * ��֤ͨ����ʾ��ǰ�û�û�е�¼����Ҫ�˷������ز�������ִ��
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		/*//��ȡ�����url
		String url = request.getRequestURI();

		//�ӿ�ֱ�ӷ���
		if (url.indexOf("m/admin/")==-1) { 
			return true;//ֱ�ӷ���
		}
		
		//��¼ҳֱ�ӷ���
		if(url.indexOf("log")>=0){
			//��֤ͨ��������
			return true;
		}
		
		//��ȡsession
		HttpSession session = request.getSession();
		Admin admin = (Admin)session.getAttribute("ADMIN");
		
		//�ѵ�¼ֱ�ӷ���
		if (admin!=null) {
			return true;
		}
		
		//ִ�е�����˵����Ҫ���е�¼����ת����¼ҳ��
		response.sendRedirect("log/to");
		//rquest.getRequestDispatcher("m/log/display").forward(request, response);
		
		//return false��ʾ���أ�������ִ��  
        //return true��ʾ����   
		return false;*/
		
		return true;
	}

}
