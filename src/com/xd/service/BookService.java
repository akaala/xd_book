package com.xd.service;

import java.util.List;

import com.xd.dao.book.BookDao;
import com.xd.model.Book;

public class BookService {
	public List<Book> getListBook(){
		BookDao bookDao=new BookDao();
		return bookDao.getListBook();
	}
}
