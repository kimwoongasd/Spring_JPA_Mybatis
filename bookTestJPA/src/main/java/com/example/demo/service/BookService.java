package com.example.demo.service;

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
	
	public List<BookVO> findAll() {
		return dao.findAll();
	}
	
	// pk에 해당하는 레코드가 있으면 update 수행
	// 그렇지 않으면 insert를 수행
	public void save(BookVO b) {
		dao.save(b);
	}
}
