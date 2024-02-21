package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookDAO;
import com.example.demo.entity.Book;

import lombok.Setter;

@Service
@Setter
public class BookService {
	
	@Autowired
	private BookDAO dao;
	
	public List<Book> listBook(){
		return dao.findAll();
	}
	
	public void insertBook(Book b) {
		int bookid = dao.getNExtNo();
		b.setBookid(bookid);
		dao.save(b);
	}
	
	public void updateBook(Book b) {
		dao.save(b);
	}
	
	public void deleteBook(int bookid) {
		dao.deleteById(bookid);
	}
}
