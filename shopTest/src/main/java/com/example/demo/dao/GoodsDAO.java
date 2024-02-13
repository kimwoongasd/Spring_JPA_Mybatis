package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Goods;

@Repository
public interface GoodsDAO extends JpaRepository<Goods, Integer> {
	
	@Query(value = "select nvl(max(no), 0) + 1 from goods", nativeQuery = true)
	public int nextNo();
}
