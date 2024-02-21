package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "book")
public class Book {
	@Id
	private int bookid;
	private String bookname;
	private int price;
	private String publisher;
}
