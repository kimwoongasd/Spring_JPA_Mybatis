package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVO;

@Repository
public class MemberDAO {
	
	
	public MemberVO findByID(String id) {
		return DBManager.findByID(id);
	}
	
	public int insert(MemberVO m) {
		return DBManager.insertMember(m);
	}
}
