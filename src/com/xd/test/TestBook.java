package com.xd.test;

import java.util.List;

import com.xd.model.Book;
import com.xd.service.BookService;

public class TestBook {
	public static void main(String[] args) {
		BookService bookService=new BookService();
		List<Book> list=bookService.getListBook();
		for(Book book:list){
			System.out.println("书名:"+book.getName());
		}
	}
}
