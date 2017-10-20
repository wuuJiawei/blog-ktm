package com.blog.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GridBean<T> implements Serializable {
	
	private List<T> data;
	private int itemsCount;
	
	public GridBean()
	{
		data=new ArrayList<T>();
		itemsCount=0;
	}
	
	public GridBean(List<T> l)
	{
		data=l;
	}
	public GridBean(List<T> l,int i)
	{
		data=l;
		itemsCount=i;
	}
	
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getItemsCount() {
		return itemsCount;
	}
	public void setItemsCount(int itemsCount) {
		this.itemsCount = itemsCount;
	}
}
