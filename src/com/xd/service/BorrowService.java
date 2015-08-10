package com.xd.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xd.dao.borrow.BorrowDao;
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
	 * 获取总数量
	 * @param borrow
	 * @return
	 */
	public int getBorrowCount(Borrow borrow){
		return borrowDao.getBorrowCount(borrow);
	}
	/**
	 * 插入数据
	 * @param borrow
	 * @return
	 */
	public Map<String, String> insertBorrow(Borrow borrow,HttpServletRequest request){
		 map=new HashMap<String, String>();
		 HttpSession session=request.getSession();
		 User user=(User)session.getAttribute("user");
		 borrow.setUserId(user.getId());
		 borrow.setStatus(3);
		int i=borrowDao.insertBorrow(borrow);
		if(i>0){
			map.put("status","success");
			map.put("msg","操作成功");
		}else {
			map.put("status","error");
			map.put("msg","操作失败");
		}
		return map;
	}
	/**
	 * 删除申请,不借书
	 * @return
	 */
	public Map<String, String> deleteApplication(Borrow borrow){
		 map=new HashMap<String, String>();
		 int i=borrowDao.deleteApplication(borrow);
		 if(i>0){
				map.put("status","success");
				map.put("msg","操作成功");
			}else {
				map.put("status","error");
				map.put("msg","操作失败");
			}
			return map;
	}
	/**
	 * 更新状态 ,完成借书
	 * @return
	 */
	public Map<String, String> updateStatus(Borrow borrow){
		map=new HashMap<String, String>();
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

