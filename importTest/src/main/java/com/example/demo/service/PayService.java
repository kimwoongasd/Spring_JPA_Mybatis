package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PayDAO;
import com.example.demo.entity.Pay;

import lombok.Setter;

@Service
@Setter
public class PayService {
	
	@Autowired
	private PayDAO dao;
	
	public void insert(Pay p) {
		dao.save(p);
	}
}
