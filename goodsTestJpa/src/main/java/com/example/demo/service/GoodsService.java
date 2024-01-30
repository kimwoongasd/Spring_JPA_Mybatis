package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.entity.GoodsVO;

import lombok.Setter;

@Service
@Setter
public class GoodsService {
	@Autowired
	private GoodsDAO dao;
	
	public List<GoodsVO> findAll(){
		return dao.findAll();
	}
	
	public void save(GoodsVO g) {
		dao.save(g);
	}
}
