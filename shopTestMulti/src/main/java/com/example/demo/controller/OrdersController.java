package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Member;
import com.example.demo.service.CartService;
import com.example.demo.service.GoodsService;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class OrdersController {
	
	@Autowired
	private CartService cs;
	
	@Autowired
	private GoodsService gs;
	
	@GetMapping("/orders/checkout/{gnos}")
	public String checkout(@PathVariable("gnos") String gnos, Model model, HttpSession session) {
		String id = ((Member)session.getAttribute("m")).getId();
		String[] gno = gnos.split(",");
		List<Cart> list = new ArrayList<Cart>();
		for (String x : gno) {
			list.add(cs.findByIdAndGno(id, Integer.parseInt(x)));
		}
		
		String name = "";
		// 총 금액 임의로 설정
		int total = 0;
		for (Cart c: list) {
			Goods g = gs.getGoods(c.getGno());
			total += c.getQty() * g.getPrice();
			
			if (name.equals("")) {
				name = g.getItem() + "외 " + list.size() + "건";
			}
		}
		
		model.addAttribute("list", list);
		model.addAttribute("total", total);
		model.addAttribute("name", name);
		
		return "/orders/checkout";
	}
}
