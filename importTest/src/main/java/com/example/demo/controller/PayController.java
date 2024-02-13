package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PayDAO;
import com.example.demo.entity.Pay;
import com.example.demo.service.PayService;

import lombok.Setter;

@RestController
@Setter
public class PayController {
	
	@Autowired
	private PayService ps;
	
	@PostMapping("/payok")
	public String payok(Pay p) {
		
		System.out.println("결제변호 : " + p.getImp_uid());
		System.out.println("주문번호 : " + p.getMerchant_uid());
		System.out.println("구매가격 : " + p.getPaid_amount());
		System.out.println("카드 승인번호 " + p.getApply_num());
		ps.insert(p);
		
		return "ok";
	}
	
//	@PostMapping("/payok")
//	public String payok(String imp_uid, String merchant_uid, String paid_amount, String apply_num) {
//		
//		System.out.println("결제변호 : " + imp_uid);
//		System.out.println("주문번호 : " + merchant_uid);
//		System.out.println("구매가격 : " + paid_amount);
//		System.out.println("카드 승인번호 " + apply_num);
//		Pay p = new Pay(imp_uid, merchant_uid, paid_amount, apply_num);
//		ps.insert(p);
//		
//		return "ok";
//	}
}
