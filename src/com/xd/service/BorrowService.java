package com.xd.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xd.dao.borrow.BorrowDao;
import com.xd.model.Book;
import com.xd.model.Borrow;
import com.xd.model.User;

public class BorrowService {
	private BorrowDao borrowDao;
	public BorrowService(){
		this.borrowDao=new BorrowDao();
	}
	Map<String,String> map=null;
	/**
	 * 获取borrow列表
	 * @param borrow
	 * @return
	 */
	public List<Borrow> getBorrowList(Borrow borrow){
		borrow.setPageStart(borrow.getcurrentPage());
		
		return borrowDao.getBorrowList(borrow);
	}
	
	/**
	 * 获取myborrow列表
	 * @param borrow
	 * @return
	 */
	public List<Borrow> getMyBorrowList(Borrow borrow,HttpServletRequest request){
		borrow.setPageStart(borrow.getcurrentPage());
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		borrow.setUserId(user.getId());
		
		return borrowDao.getBorrowList(borrow);
	}
	
	/**
	 * 获取总数量
	 * @param borrow
	 * @return
	 */
	public int getBorrowCount(Borrow borrow){
		return borrowDao.getBorrowCount(borrow);
	}
	/**
	 * 获取总数量
	 * @param borrow
	 * @return
	 */
	public int getMyBorrowCount(Borrow borrow,HttpServletRequest request){
		borrow.setPageStart(borrow.getcurrentPage());
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		borrow.setUserId(user.getId());
		return borrowDao.getBorrowCount(borrow);
	}
	/**
	 * 插入数据
	 * @param borrow
	 * @return
	 */
	public Map<String, String> insertBorrow(Borrow borrow,HttpServletRequest request){
		 map=new HashMap<String, String>();
		 /**
		  * 1.先检查书是否被借走
		  * 2.检查本人有没有已经申请了这本书
		  * 3.插入申请记录
		  */
		 
		 
		 
		 Book book1=new Book();
		 book1.setId(borrow.getBookId());		 
		 Book bk=borrowDao.getBook(book1);
		 if(bk.getStatus()==1){//被借走
			 map.put("status","error");
			map.put("msg","该书已被借走");
		 }else if(bk.getStatus()==0){//没借走
			 HttpSession session=request.getSession();
			 User user=(User)session.getAttribute("user");
			 borrow.setOperatorId(user.getId());//经办人
			 borrow.setUserId(user.getId());
			 borrow.setStatus(1);
			 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			 String dateStr=df.format(new Date());// new Date()为获取当前系统时间
	  		borrow.setAppTime(dateStr);
			borrow.setBorrowTime(dateStr);
			borrow.setBackTime(dateStr);
			int i=borrowDao.insertBorrow(borrow);
			if(i>0){
				map.put("status","success");
				map.put("msg","申请成功,可以找管理员拿书了.");
			}else {
				map.put("status","error");
				map.put("msg","操作失败");
			}
		 }else {
			 map.put("status","error");
			 map.put("msg","系统无法判断该书是否被借走,申请失败");
		}
		return map;
	}
	
	/**
	 * 删除申请
	 * @return
	 */
	public Map<String, String> deleteBorrow(Borrow borrow){
		 map=new HashMap<String, String>();
		 
		 Borrow bw=new Borrow();
		 bw=borrowDao.getBorrow(borrow);
		 if(bw.getStatus()!=1){
			 map.put("status","success");
			 map.put("msg","该条记录已经不是申请状态,无法删除!");
		 }else{
			 int i=borrowDao.deleteBorrow(borrow);
			 if(i>0){
				 map.put("status","success");
				 map.put("msg","操作成功");
			 }else {
				 map.put("status","error");
				 map.put("msg","操作失败");
			 }
		 }
			return map;
	}
	/**
	 * 更新状态 ,完成借书
	 * @return
	 */
	public Map<String, String> updateStatus(Borrow borrow,HttpServletRequest request){
		map=new HashMap<String, String>();
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		borrow.setOperatorId(user.getId());//经办人
		int i=borrowDao.updateStatus(borrow);
		if(i>0){
			map.put("status","success");
			map.put("msg","操作成功");
		}else {
			map.put("status","error");
			map.put("msg","操作失败");
		}
		return map;
	}
}

