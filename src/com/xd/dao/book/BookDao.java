package com.xd.dao.book;

import java.util.List;

import org.apache.ibatis.session.SqlSession;


import com.xd.dao.SqlSessionDao;
import com.xd.model.Book;

public class BookDao{
	private SqlSession getSession(){
		SqlSessionDao sqlSessionDao=new SqlSessionDao();
		return sqlSessionDao.session;
	}
	
	/**
	 * 获取图书列表
	 * @return
	 */
	public List<Book> getListBook(Book book) {
		List<Book> list = null;
		SqlSession session=getSession();
		try {
			list = session.selectList("book.getListBook", book);
		} catch (Exception e) {
			System.out.println("获取书籍dao层list异常....");
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return list;
	}
/**
 * 获取图书数量
 * @param book
 * @return
 */
	public int getBookCount(Book book){
		int i=-1;
		SqlSession session=getSession();
		try {
			i=session.selectOne("book.getBookCount",book);
		} catch (Exception e) {
			System.out.println("获取数量时异常......");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return i;
	}
	
	/**
	 * 更新图书信息
	 * @param book
	 * @return
	 */
	public int updateBook(Book book){
		int i=-1;
		SqlSession session=getSession();
		try {
			i=session.update("book.updateBook",book);
			session.commit();
		} catch (Exception e) {
			System.out.println("更新图书时异常......");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return i;
	}
	
	/**
	 * 删除图书
	 * @param book
	 * @return
	 */
	public int deleteBook(Book book){
		int i=-1;
		SqlSession session=getSession();
		try {
			i=session.delete("book.deleteBook",book);
			session.commit();
		} catch (Exception e) {
			System.out.println("删除数据时出现异常.....");
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		return i;
	}
	
	/**
	 * 插入图书
	 * @param book
	 * @return
	 */
	public int insertBook(Book book){
		int i=-1;
		SqlSession session=getSession();
		
		try {
			i=session.insert("book.insertBook",book);
			session.commit();
		} catch (Exception e) {
			System.out.println("插入书籍时出现异常......");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return i=0;
	}
	
	
}
