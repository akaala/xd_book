package com.xd.model;

public class BaseModel {
	private int pageSize;//每页数据
	private int totalCount;//总条数
	private int pageNumber;//页面
	
	private int pageStart;
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
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart() {
		this.pageStart = (this.pageNumber-1)*this.pageSize;
	}
	
	
	
}
