package com.xd.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xd.model.Book;
import com.xd.service.BookService;

@Controller
@RequestMapping("view/book/book.it")
public class BookController {
	private BookService bookService;

	public BookController() {
		bookService = new BookService();
	}

	@RequestMapping(params = "action=getBookList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getBookList(Book book,HttpServletRequest request) {
		return bookService.getListBook(book,request);
	}


	@RequestMapping(params = "action=saveBook", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> saveBook(Book book) {
		return bookService.saveBook(book);
	}
	
	@RequestMapping(params="action=deleteBook",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> deleteBook(Book book){
		return bookService.deleteBook(book);
	}
	
}
