package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDAO;
import com.example.demo.entity.Board;

import lombok.Setter;

@Service
@Setter
public class BoardService {
	@Autowired
	private BoardDAO dao;
	
	public int getTotalRecord() {
//		return dao.getTotalRecord();
		return (int)dao.count();
	}
	
	public void insert(Board b) {
		dao.insert(b);
	}
	
	public int getNextNo() {
		return dao.getNextNo();
	}
	
	public List<Board> findAll(int start, int end){
		return dao.selectAll(start, end);
	}
	
	public Board getById(int no) {
		Board b = null;
		Optional<Board> o = dao.findById(no);
		if (o.isPresent()) {
			b = o.get();
		}
		return b;
	}
	
	public void updateStep(int b_ref, int b_step) {
		dao.updateStep(b_ref, b_step);
	}
}

