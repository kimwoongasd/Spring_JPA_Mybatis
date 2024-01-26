package com.example.demo.dao;


import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.AopVO;


@Repository
public class AopDAO {
	
	public int insertAop(AopVO a) {
		return DBManager.insertAop(a);
	}
}
