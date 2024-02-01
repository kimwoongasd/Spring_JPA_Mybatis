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

//@Service
//@Setter
public class BookService2 {
	
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
		List<BookVO> list = null;
		
		if (keyword != null && !keyword.equals("")) {
			if (catgory.equals("bookname")) {
				list = dao.findByBooknameContaining(keyword);
			} else if (catgory.equals("bookid")){
				list = dao.findByBookid(Integer.parseInt(keyword));
			}else if (catgory.equals("price")) {
				list = dao.findByPrice(Integer.parseInt(keyword));
			} else if (catgory.equals("publisher")){
				list = dao.findByPublisherContaining(keyword);
			}
		} else {
			list = dao.findAllByOrderByBookname();
		}
	
		return list;
	}

	// pk에 해당하는 레코드가 있으면 update 수행
	// 그렇지 않으면 insert를 수행
	public void save(BookVO b) {
		dao.save(b);
	}
}
