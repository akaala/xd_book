package com.xd.dao.book;

import java.util.List;


import com.xd.dao.SqlSessionDao;
import com.xd.model.Book;

public class BookDao{
	public List<Book> getListBook() {
		List<Book> list = null;
		try {
			list = SqlSessionDao.session.selectList("book.getListBook", null);
			
			for (Book book : list) {
				System.out.println("书名:" + book.getName());
			}
		} catch (Exception e) {
			System.out.println("获取书籍dao层list异常....");
			e.printStackTrace();
		}
finally{
	SqlSessionDao.session.close();
}
		return list;
	}

}
