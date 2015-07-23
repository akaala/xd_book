package com.xd.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xd.model.Book;
import com.xd.service.BookService;
@Controller
@RequestMapping(value="book")
public class BookController {
	@RequestMapping(value="getList")
	public String getList(){
		return "book/book";
	}
}
