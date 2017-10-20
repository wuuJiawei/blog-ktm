package com.blog.util;

import java.io.Serializable;
import java.util.List;

public class LayTableBean<T> implements Serializable {

	private static final long serialVersionUID = 5493118823312743810L;

	public LayTableBean(int code,String msg,int count,List<T> data) {
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}
	
	public LayTableBean(int count,List<T> data){
		this.code=0;
		this.msg="";
		this.count = count;
		this.data=data;
	}
	
	
	private int code;//返回编码
	
	private String msg;//返回消息
	
	private int count;//数据总量
	
	private List<T> data;//数据集合

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	
}
