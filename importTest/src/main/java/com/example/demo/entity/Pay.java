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
@Table(name = "pay")
public class Pay {
	@Id
	private String imp_uid;
	private String merchant_uid;
	private String paid_amount;
	private String apply_num;
}
