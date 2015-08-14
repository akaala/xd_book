package com.xd.model;

public class Book extends BaseModel{
	private int id;	
	//图书编码
	private String number;
	//书名
	private String name;
	//作者
	private String author;
	//价格
	private float price;
	//出版社
	private String publisher;
	//图书借阅人id
	private int status;
	//借书记录状态
private int borrowStatus;
	//申请人
	private int userId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBorrowStatus() {
		return borrowStatus;
	}
	public void setBorrowStatus(int borrowStatus) {
		this.borrowStatus = borrowStatus;
	}
}
