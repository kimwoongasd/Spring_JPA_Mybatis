package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CartDAO;
import com.example.demo.entity.Cart;

import lombok.Setter;

@Service
@Setter
public class CartService {

	@Autowired
	private CartDAO dao;
	
	public int getNextNo() {
		return dao.getNextNo(); 
	}
	
	public List<Cart> findMy(String id){
		return dao.findMy(id);
	}
	
	public void insert(Cart c) {
		String id = c.getId();
		int gno = c.getGno();
		Cart old = dao.findByIdAndGno(id, gno);
		
//		c.setNo(dao.getNextNo());
		c.setQty(1);
		
		// 장바구니에 이미 담겨져 있을 경우 수량 +1 
		if (old != null) {
			c.setQty(old.getQty() + 1);
			c.setNo(old.getNo());
		}
		
		dao.save(c);
	}
}
