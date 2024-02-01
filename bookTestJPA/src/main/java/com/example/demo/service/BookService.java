package com.example.demo.service;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookDAO;
import com.example.demo.entity.BookVO;

import lombok.Setter;

@Service
@Setter
public class BookService {
	
	@Autowired
	private BookDAO dao;
	
	public void delete(int bookid) {
		dao.deleteById(bookid);
	}
	
	public BookVO findById(int bookid) {
		BookVO b = null;
		// jpa의 findById는 VO를 반환하지 않고
		// VO를 Optional로 포장해서 반환
		Optional<BookVO> o = dao.findById(bookid);
		if (o.isPresent()) {
			b = o.get();
		}
		
		return b;
	}
	
	public List<BookVO> findAll(HashMap<String, String> map) {
		String keyword = map.get("keyword");
		String catgory = map.get("catgory");
		String order = map.get("order");
		List<BookVO> list = null;
		
		if (keyword != null && !keyword.equals("")) {
			String methodName = "findBy"+catgory;
			
			if(order != null) {
				methodName += "OrderBy"+order;
			}
			
			try {
				Class<?> cls = 
					Class.forName(dao.getClass().getName());
				if(catgory.equals("Bookname") || catgory.equals("Publisher")) {
					StringBuffer sb = new StringBuffer(methodName);
					int i = sb.length();
					if(sb.indexOf("OrderBy") != -1) {
						i = sb.indexOf("OrderBy");
					}
					methodName = sb.insert(i, "Containing").toString();					
					Method method = 
							cls.getDeclaredMethod(methodName, String.class);			
							list = (List<BookVO>)method.invoke(dao, keyword);
				}else {
					Method method = 
							cls.getDeclaredMethod(methodName, Integer.class);							
							list = (List<BookVO>)method.invoke(dao, new Integer(keyword) );
				}
		
			} catch (Exception e) {
				System.out.println(e.getMessage()); 
			}
		} else {
			String methodName = "findAllByOrderBy";
			
			if(order != null) {
				methodName += order;
			}else {
				methodName += "Bookname";
			}
			try {
				Class<?> cls = 
					Class.forName(dao.getClass().getName());
					Method method = 
							cls.getDeclaredMethod(methodName);			
							list = (List<BookVO>)method.invoke(dao);										
			}catch (Exception e) {
				System.out.println("예외발생:"+e.getMessage());
			}
		}
		
//		String methodName = "";
//		try {
//			Class<?> cls = Class.forName(dao.getClass().getName());
//			Method method = null;
//			if (keyword != null && !keyword.equals("")) {
//				if (catgory.equals("Bookname")) {
//					methodName = "findByBooknameContaining";
//					method = cls.getDeclaredMethod(methodName, String.class);
//					list = (List<BookVO>)method.invoke(dao, keyword);
//				} else if (catgory.equals("Bookid")){
//					methodName = "findByBookid";
//					method = cls.getDeclaredMethod(methodName, Integer.class);
//					list = (List<BookVO>)method.invoke(dao, Integer.parseInt(keyword));
//				}else if (catgory.equals("Price")) {
//					methodName = "findByPrice";
//					method = cls.getDeclaredMethod(methodName, Integer.class);
//					list = (List<BookVO>)method.invoke(dao, Integer.parseInt(keyword));
//				} else if (catgory.equals("Publisher")){
//					methodName = "findByPublisherContaining";
//					method = cls.getDeclaredMethod(methodName, String.class);
//					list = (List<BookVO>)method.invoke(dao, keyword);
//				}
//			} else {
//				return dao.findAllByOrderByBookname();
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
	
		return list;
	}
	
	// pk에 해당하는 레코드가 있으면 update 수행
	// 그렇지 않으면 insert를 수행
	public void save(BookVO b) {
		dao.save(b);
	}
}
