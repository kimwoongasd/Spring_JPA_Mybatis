package com.example.demo.service;

import java.util.List;

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
	
	public List<BookVO> findAll() {
		return dao.findAll();
	}
	
	// pk에 해당하는 레코드가 있으면 update 수행
	// 그렇지 않으면 insert를 수행
	public void save(BookVO b) {
		dao.save(b);
	}
}
