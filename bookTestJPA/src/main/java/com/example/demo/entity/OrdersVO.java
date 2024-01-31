package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="orders")
public class OrdersVO {
	@Id
	private int orderid;
	
	@ManyToOne // 차수 관게 설정
	@JoinColumn(name="custid", insertable = true, updatable = true) // insert, update문 가능
	private CustomerVO customer;
	
	@ManyToOne
	@JoinColumn(name = "bookid", insertable = true, updatable = true)
	private BookVO book;
	
	private int saleprice;
	private Date orderdate;
}
