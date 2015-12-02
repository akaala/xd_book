package com.xd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.xd.dao.book.BookDao;
import com.xd.model.Book;
import com.xd.model.User;

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
	public Map<String, Object> getListBook(Book book,HttpServletRequest request){
		book.setPageStart(book.getcurrentPage());
		HttpSession session=request.getSession();
		/**
		 * TODO sql语句有问题
		 */
		User user=(User)session.getAttribute("user");
		book.setUserId(user.getId());
		List<Book> list=bookDao.getListBook(book);
		
		Map<String, Object> map=new HashMap<String, Object>();
		int totalCount=bookDao.getBookCount(book);
		map.put("totalCount", totalCount);
		map.put("currentPage",book.getcurrentPage());
		map.put("list", list);
		return map;
	}
	
	/**
	 * 获取图书数量
	 * @param book
	 * @return
	 *//*
		public int getBookCount(Book book){
			int i=bookDao.getBookCount(book);
			return i;
		}*/
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
	/**
	 * 保存图书
	 * @param book
	 * @return
	 */
	public Map<String, Object> saveBook(Book book){
		Book b1=new Book();
		b1.setNumber(book.getNumber());
		int i=bookDao.getBookCount(b1);
		Map<String, Object> map=new HashMap<String, Object>();
		if(i>0){
			map.put("status", "error");
			map.put("msg", "图书编码重复");
			return map;
		}
		
		if(book.getId()==-1){
			return insertBook(book);
		}else{
			return updateBook(book);
		}
	}
	
}
