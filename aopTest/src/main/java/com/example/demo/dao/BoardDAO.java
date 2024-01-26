package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.BoardVO;

@Repository
public class BoardDAO {
	public List<BoardVO> listBoard(String pageNum) {
		System.out.println("게시글 목록 동작");
		return DBManager.listBoard(pageNum);
	}
	
	public BoardVO detailBoard(int no) {
		return DBManager.detailBoard(no);
	}
	
	public int boardNextNo() {
		return DBManager.boardNextNo();
	}
	
	public int insertBoard(BoardVO b) {
		return DBManager.insertBoard(b);
	}
	
	public void updateStep(int b_ref, int b_step) {
		DBManager.updateStep(b_ref, b_step);
	}
	
	public int maxRecord() {
		return DBManager.maxRecord();
	}
 }
