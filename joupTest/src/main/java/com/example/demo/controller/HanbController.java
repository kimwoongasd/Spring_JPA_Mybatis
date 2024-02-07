package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.NewBook;

@RestController
public class HanbController {

//	@GetMapping("/newbook")
//	public String newBook() {
//		String base = "https://www.hanbit.co.kr";
//		String url = "https://www.hanbit.co.kr/store/books/new_book_list.html";
//		String res = "";
//		try {
//			// jsoup을 통해서 doc객체 생성
//			Document doc = Jsoup.connect(url).get();
//			// 찾고자 하는 Elements를 찾아서 검색 
//			// 배열을 반환
//			Elements list = doc.select(".book_tit");
//			
//			for (Element e: list) {
//				// 첫번째 자식 Element를 반환
//				Element a = e.firstElementChild();
//				// attr로 속성 접근
//				String link = base + a.attr("href");
//				String text = a.text();
//				
//				System.out.println(link);
//				res += "<a href='"+link+"'>"+text+"</a><br>";
//				
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		return res;
//	}

//	@GetMapping("/newbook")
//	public HashMap<String, String> newBook(Model model) {
//		HashMap<String, String> map = new HashMap<String, String>();
//		String base = "https://www.hanbit.co.kr";
//		String url = "https://www.hanbit.co.kr/store/books/new_book_list.html";
//		try {
//			// jsoup을 통해서 doc객체 생성
//			Document doc = Jsoup.connect(url).get();
//			// 찾고자 하는 Elements를 찾아서 검색 
//			// 배열을 반환
//			Elements list = doc.select(".book_tit");
//			
//			for (Element e: list) {
//				// 첫번째 자식 Element를 반환
//				Element a = e.firstElementChild();
//				// attr로 속성 접근
//				String link = base + a.attr("href");
//				String text = a.text();
//				
//				map.put(text, link);
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		
//		model.addAttribute("map", map);
//		return map;
//	}

	@GetMapping("/newbook")
	public List<NewBook> newBook(Model model) {
		List<NewBook> bookList = new ArrayList<NewBook>();
		String base = "https://www.hanbit.co.kr";
		// 이 홈페이지의 최대 페이지 수는 86페이지
		int i = 1;
		try {
//			String url2 = "https://www.hanbit.co.kr/store/books/new_book_list.html?page=86";
			// 페이지 넘어서 조회시 오류 확인
//			Connection conn = Jsoup.connect(url2);
//			System.out.println(conn);
//			Document doc2 = Jsoup.connect(url2).get();
//			Elements list2 = doc2.select(".book_tit");
			// list2의 null값을 확인하려 했지만 값이 들어가 있음
			// size를 통해 확인
//			if (list2.size() == 0) {
//				System.out.println("86페이지 없음");
//			}
			
			while (true) {
				String url = "https://www.hanbit.co.kr/store/books/new_book_list.html?page=" + i;

				// jsoup을 통해서 doc객체 생성
				Document doc = Jsoup.connect(url).get();
				// 찾고자 하는 Elements를 찾아서 검색
				// 배열을 반환
				Elements list = doc.select(".book_tit");
				if (doc.selectFirst(".no-list") != null) {
					break;
				}

				for (Element e : list) {
					// 첫번째 자식 Element를 반환
					Element a = e.firstElementChild();
					// attr로 속성 접근
					String link = base + a.attr("href");
					String text = a.text();

					bookList.add(new NewBook(text, link));
				}
				i++;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return bookList;
	}
	
	@GetMapping("/getWating")
	public int getWating() {
		int cnt = 0;
//		String url = "http://mpllc-seat.sen.go.kr/seatinfo/Seat_Info/1_count.asp";
		String url = "http://mpllc-seat.sen.go.kr/seatinfo/";
		
		try {
			Document doc = Jsoup.connect(url).get();
//			
			Elements iframe = doc.select("iframe");
			url += iframe.get(0).attr("src");
			doc = Jsoup.connect(url).get();
//			Elements list = doc.select(".wating_f");
			Element e = doc.select(".wating_f").get(0);
			cnt = Integer.parseInt(e.text());
			System.out.println(cnt);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return cnt;
	}
}
