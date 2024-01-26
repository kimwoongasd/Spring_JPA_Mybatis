package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.BookVO;

@Repository
public class BookDAO {
	public List<BookVO> findAll(String sColumn, String category, String keyword){
		return DBManager.listBook(sColumn, category, keyword);
	}
	
	public BookVO findByNo(int no) {
		return DBManager.detailBook(no);
	}
}
