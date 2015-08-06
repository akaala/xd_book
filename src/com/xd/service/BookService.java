package com.xd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.xd.dao.book.BookDao;
import com.xd.model.Book;

@Service
public class BookService {
	
	private BookDao bookDao;
	
	public BookService(){
		bookDao=new BookDao();
	}
	
	/**
	 * 书籍列表
	 * @return
	 */
	public Map<String, Object> getListBook(Book book){
		List<Book> list=bookDao.getListBook(book);
		Map<String, Object> map=new HashMap<String, Object>();
		int totalCount=bookDao.getBookCount(book);
		map.put("totalCount", totalCount);
		map.put("pageSize",book.getPageSize());
		map.put("list", list);
		return map;
	}
	
	/**
	 * 更新图书信息
	 * @param book
	 * @return
	 */
	public Map<String, Object> updateBook(Book book){
		int i=bookDao.updateBook(book);
		Map<String, Object> map=new HashMap<String, Object>();
		if(i==-1){
			map.put("status", "error");
			map.put("msg", "更新失败");
		}else{
			map.put("status", "success");
			map.put("msg", "更新成功");
		}
		return map;
	}
	/**
	 * 删除图书
	 * @param book
	 * @return
	 */
	public Map<String, Object> deleteBook(Book book){
		int i=bookDao.deleteBook(book);
		Map<String, Object> map=new HashMap<String, Object>();
		if(i==-1){
			map.put("status", "error");
			map.put("msg", "删除失败");
		}else{
			map.put("status", "success");
			map.put("msg", "删除成功");
		}
		return map;
	}
	/**
	 * 插入图书
	 * @param book
	 * @return
	 */
	public  Map<String, Object> insertBook(Book book){
		int i=bookDao.insertBook(book);
		Map<String, Object> map=new HashMap<String, Object>();
		if(i==-1){
			map.put("status", "error");
			map.put("msg", "插入失败");
		}else{
			map.put("status", "success");
			map.put("msg", "插入成功");
		}
		return map;
	}
	
}
