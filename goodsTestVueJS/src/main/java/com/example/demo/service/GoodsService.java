package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.entity.Goods;

import lombok.Setter;

@Service
@Setter
public class GoodsService {

	@Autowired
	private GoodsDAO dao;
	
	public void insertGoods(Goods g) {
		g.setNo(dao.getNextNo());
		dao.save(g); 
	}
	
	public List<Goods> listGoods(){
		return dao.findAll();
	}
	
	public void updateGoods(Goods g) {
		dao.save(g);
	}
	
	public void deleteGoods(Goods g) {
		dao.deleteById(g.getNo());
	}
}
