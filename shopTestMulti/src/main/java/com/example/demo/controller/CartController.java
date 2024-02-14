package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Goods;
import com.example.demo.service.CartService;
import com.example.demo.service.GoodsService;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@Setter
public class CartController {

	@Autowired
	private CartService cs;
	
	@Autowired
	private GoodsService gs;
	
	@GetMapping("/cart/list")
	public String findAll(HttpSession session, Model model) {
		
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		String id = user.getUsername();
		
		List<Cart> list = cs.findMy(id);
//		List<Goods> g_list = null;
//		
//		HashMap<List<Cart>, List<Goods>> map = new HashMap<List<Cart>, List<Goods>>();
//		
//		for (Cart c: list) {
//			g_list.add(gs.getGoods(c.getGno()));
//		}
//		
//		map.put(list, g_list);
//		model.addAttribute("map", map);
		model.addAttribute("list", list);
		
		return "/cart/list";
	}
	
	@GetMapping("/cart/add/{no}")
	public String insert(@PathVariable("no") int no, HttpSession session) {
		
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		String id = user.getUsername();
		Cart c = new Cart();
		c.setId(id);
		c.setGno(no);
		cs.insert(c);
		
		return "/cart/add";
	}
}
