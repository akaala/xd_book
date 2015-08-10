package com.xd.dao.borrow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.taglibs.standard.lang.jstl.Literal;

import com.xd.dao.SqlSessionDao;
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
	 * 删除申请,不借书
	 * @return
	 */
	public int deleteApplication(Borrow borrow){
		int i=-1;
		SqlSession session=getSession();
		try {
			i=session.delete("borrow.deleteApplication",borrow);
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
