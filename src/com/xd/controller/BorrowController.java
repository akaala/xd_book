package com.xd.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xd.model.Borrow;
import com.xd.service.BorrowService;

@Controller
@RequestMapping("borrow.it")
public class BorrowController {

	private BorrowService borrowService;
	public BorrowController(){
		this.borrowService=new BorrowService();
	}
	/**
	 * 获取borrow列表
	 * @param borrow
	 * @return
	 */	
	@RequestMapping(params="action=getBorrowList",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getBorrowList(Borrow borrow){
		List<Borrow> list=borrowService.getBorrowList(borrow);
		int totalCount=borrowService.getBorrowCount(borrow);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("totalCount", totalCount);
		map.put("currentPage", borrow.getcurrentPage());
		map.put("list", list);
		return map;
	}
	/**
	 * 获取我的borrow列表
	 * @param borrow
	 * @return
	 */	
	@RequestMapping(params="action=getMyBorrowList",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getMyBorrowList(Borrow borrow,HttpServletRequest request){
		List<Borrow> list=borrowService.getMyBorrowList(borrow,request);
		int totalCount=borrowService.getMyBorrowCount(borrow,request);
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("totalCount", totalCount);
		map.put("currentPage", borrow.getcurrentPage());
		map.put("list", list);
		return map;
	}
	
	
	
	/**
	 * 插入数据
	 * @param borrow
	 * @return
	 */
	@RequestMapping(params="action=insertBorrow",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> insertBorrow(Borrow borrow,HttpServletRequest request){
		return borrowService.insertBorrow(borrow,request);
	}
	/**
	 * 删除申请,不借书
	 * @return
	 */
	@RequestMapping(params="action=deleteBorrow",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> deleteBorrow(Borrow borrow){
		return borrowService.deleteBorrow(borrow);
	}
	/**
	 * 更新状态 ,完成借书
	 * @return
	 */
	@RequestMapping(params="action=updateStatus",method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> updateStatus(Borrow borrow,HttpServletRequest request){
		return borrowService.updateStatus(borrow,request);
	}
	
	
}
