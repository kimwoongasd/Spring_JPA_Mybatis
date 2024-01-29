package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.example.demo.db.DBManager;
import com.example.demo.vo.DeptVO;

@Repository
public class DeptDAO {
	
	public List<DeptVO> findAll() {
//		DBManager db = new DBManager();
//		List<DeptVO> list = db.findAll();
//		model.addAttribute("list", list);
		return DBManager.findAll();
	}

}
