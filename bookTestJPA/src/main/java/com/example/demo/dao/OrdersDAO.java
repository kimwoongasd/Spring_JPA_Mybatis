package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.OrdersVO;

import jakarta.transaction.Transactional;

@Repository
public interface OrdersDAO extends JpaRepository<OrdersVO, Integer> {
	
	@Query(value = "select nvl(count(*),0)+1 from orders", nativeQuery = true)
	public int getNextNo();
	
	// insert, delete, update 데이터 변경이 있는 경우 위 2개 어노케이션 사용
	@Modifying
	@Transactional
	@Query(value = "insert into orders o(o.orderid, o.custid, o.bookid, o.saleprice, o.orderdate) values(:#{#o.orderid}, :#{#o.customer.custid}, :#{#o.book.bookid}, :#{#o.saleprice}, sysdate)", nativeQuery = true)
	public void insert(@Param("o") OrdersVO o);
	
}
