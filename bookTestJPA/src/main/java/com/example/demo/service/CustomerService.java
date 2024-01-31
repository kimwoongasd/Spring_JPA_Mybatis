package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.entity.CustomerVO;

import lombok.Setter;

@Service
@Setter
public class CustomerService {
	
	@Autowired
	private CustomerDAO dao;
	
	public int getNextNo() {
		return dao.getNextNo();
	}
	
	public void delete(int custid) {
		dao.deleteById(custid);
	}
	
	public CustomerVO findById(int custid) {
		// optional 외 다른방법
		return dao.getById(custid);
	}
	
	public void save(CustomerVO c) {
		dao.save(c);
	} 
	
	public List<CustomerVO> findAll(){
		List<CustomerVO> list = null;
		list = dao.findAll();
		
		return list;
	}
}
