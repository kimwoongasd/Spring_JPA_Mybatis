package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="goods")
public class GoodsVO {
	
	@Id
	private int no;
	private String name;
	private int price;
	private int qty;
	private String fname; 
}
