package com.blog.util;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 默认成功：code-0,msg-操作成功
 * @author Administrator
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)  //为NULL时不参与序列化
public class ResultBean {

	private Integer code;
	private String msg;
	private Object object;
	
	
	
	/**
	 * 成功：
	 * 	-不携带参数
	 */
	public ResultBean() {
		this.code=0;
		this.msg="操作成功！";
	}
	
	/**
	 * 通用函数
	 * @param code 101-您尚未登录，请先登录
	 * @param code 0-操作成功
	 * @param code 10011-录入失败，请检查输入！
	 * @param code 10012-更新失败，请检查输入！
	 * @param code 10021-网络连接异常
	 * @param code 10031-删除失败，请刷新浏览器重试！
	 * @param code default-请联系管理员！
	 */
	public ResultBean(Integer code) {
		this.code=code;
		switch (code) {
		case 101:
			this.msg="您尚未登录，请先登录";
			break;
		case 0:
			this.msg="操作成功！";
			break;
		case 10011:
			this.msg="录入失败，请检查输入！";
			break;
		case 10012:
			this.msg="更新失败，请检查输入！";
			break;
		case 10013:
			this.msg="用户名或密码错误！";
			break;
		case 10021:
			this.msg="网络连接异常";
			break;
		case 10031:
			this.msg="删除失败，请刷新浏览器重试！";
			break;
		default:
			this.msg="请联系管理员！";
			break;
		}
	}
	
	/**
	 * 通用函数：
	 * 	-不携带object参数
	 * @param code
	 * @param msg
	 */
	public ResultBean(Integer code,String msg) {
		this.code=code;
		this.msg=msg;
	}
	
	/**
	 * 通用函数
	 * @param code
	 * @param msg
	 * @param object
	 */
	public ResultBean(Integer code,String msg,Object object) {
		this.code=code;
		this.msg=msg;
		this.object=object;
	}
	
	/**
	 * 成功：
	 * 	-携带object
	 * @param object
	 */
	public ResultBean(Object object) {
		this.code=0;
		this.msg="操作成功！";
		this.object=object;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	
	
}
