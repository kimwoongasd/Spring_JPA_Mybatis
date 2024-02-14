package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cart;

@Repository
public interface CartDAO extends JpaRepository<Cart, Integer> {

	@Query(value = "select nvl(max(no), 0) + 1 from cart", nativeQuery = true)
	public int getNextNo();
	
	@Query(value = "select * from cart where id=?1", nativeQuery = true)
	public List<Cart> findMy(String id);
		
	//@Query(value = "select * from cart where id=?1 and gno=?2", nativeQuery = true)
	public Cart findByIdAndGno(String id, int gno);
	
}
