package com.xd.dao;

import java.util.List;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.xd.model.Book;

@Repository
public class BookDao extends SqlSessionDaoSupport{
	public List<Book> getListBook(){
		List<Book> list=this.getSqlSession().selectList("book.getListBook",null);
		return list;
	}
	
	
	//public static void main(String[] args) {
		//SqlSession session=this.get// .openSession();
		/*try {
			List<Book> list=session.selectList("book.getListBook",null);
			for(Book book:list){
				System.out.println("书名:"+book.getName());
			}
		} catch (Exception e) {
			System.out.println("main异常....");
			e.printStackTrace();
		}finally{
			session.close();
		}
	}*/

}
