package com.xd.model;

public class BaseModel {
	private int pageSize;//每页数据
	private int totalCount;//总条数
	private int currentPage;//当前页
	
	private int pageStart;//起始数据索引
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getcurrentPage() {
		return currentPage;
	}
	public void setcurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int currentPage) {
		this.pageStart = (currentPage-1)*this.pageSize;
	}
	
	
	
}
