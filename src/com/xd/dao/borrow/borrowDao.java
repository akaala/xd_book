package com.xd.dao.borrow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.taglibs.standard.lang.jstl.Literal;

import com.xd.dao.SqlSessionDao;
import com.xd.model.Book;
import com.xd.model.Borrow;
public class BorrowDao {
	/**
	 * 获取session
	 * @return
	 */
	private SqlSession getSession(){
		SqlSessionDao sqlSessionDao=new SqlSessionDao();
		return sqlSessionDao.session;
	}
	
	/**
	 * 获取borrow列表
	 * @param borrow
	 * @return
	 */
	public List<Borrow> getBorrowList(Borrow borrow){
		List<Borrow> list=new ArrayList<Borrow>();
		SqlSession session=getSession();
		try {
			list=session.selectList("borrow.getBorrowList",borrow);
		} catch (Exception e) {
			System.out.println("获取列表时异常....");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	/**
	 * 获取总数量
	 * @param borrow
	 * @return
	 */
	public int getBorrowCount(Borrow borrow){
		SqlSession session=getSession();
		int i=-1;
		try {
			i=session.selectOne("borrow.getBorrowCount",borrow);
		} catch (Exception e) {
			System.out.println("获取数量时异常......");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return i;
	}
	/**
	 * 获取单本书
	 */
	public Book getBook(Book book){
		Book b=new Book();
		SqlSession session=getSession();
		try {
			b=session.selectOne("borrow.getBook",book);
		} catch (Exception e) {
			System.out.println("获取书籍是否被借走时异常.....");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return b;
	}
	
	/**
	 * 获取单本书
	 */
	public Borrow getBorrow(Borrow borrow){
		Borrow b=new Borrow();
		SqlSession session=getSession();
		try {
			b=session.selectOne("borrow.getBorrow",borrow);
		}catch (Exception e) {
			System.out.println("获取单条借阅信息时异常.....");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return b;
	}
	
	/**
	 * 插入数据
	 * @param borrow
	 * @return
	 */
	public int insertBorrow(Borrow borrow){
		int i=-1;
		SqlSession session=getSession();
		try {
			i=session.insert("borrow.insertBorrow",borrow);
			session.commit();
		} catch (Exception e) {
			System.out.println("插入数据时异常.....");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return i;
	}
	/**
	 * 删除
	 * @return
	 */
	public int deleteBorrow(Borrow borrow){
		int i=-1;
		SqlSession session=getSession();
		try {
			i=session.delete("borrow.deleteBorrow",borrow);
			session.commit();
		} catch (Exception e) {
			System.out.println("删除数据时异常.....");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return i;
	}
	/**
	 * 更新状态 ,完成借书
	 * @return
	 */
	public int updateStatus(Borrow borrow){
		int i=-1;
		SqlSession session=getSession();
		try {
			Book book=new Book();
			book.setId(borrow.getBookId());
			if(borrow.getStatus()==2){//修改书籍表的userId
				book.setStatus(1);
				i=session.update("borrow.updateBook",book);
			}else if(borrow.getStatus()==3) {
				book.setStatus(0);
				i=session.update("borrow.updateBook",book);
			}
			i=session.update("borrow.updateStatus",borrow);
			session.commit();
		} catch (Exception e) {
			System.out.println("更新状态时异常.....");
			e.printStackTrace();
		}finally{
			session.close();
		}
		return i;
	}
	
}
