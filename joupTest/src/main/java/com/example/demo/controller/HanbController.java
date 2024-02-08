package com.example.demo.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BookDAO;
import com.example.demo.vo.NewBook;
import com.example.demo.vo.Book;

import lombok.Setter;

@RestController
@Setter
public class HanbController {
	
	private String base = "https://www.hanbit.co.kr";
	@Autowired
	private BookDAO dao;
	
	public Book getInfo(String path) {
		Book s = new Book();
		String url = base + path;
		try {
			Document doc = Jsoup.connect(url).get();
			// 도서명 구하기
			Elements div = doc.select(".store_product_info_box").select("h3");
			String title = div.get(0).text();
			
			// 가격, 저자, 출판일 구하기
			Elements info = doc.select(".info_list");
			Elements li = info.get(0).getElementsByTag("li");
			
			String writer = li.get(0).getElementsByTag("span").text();
			String date;
			
			// 번역된 책인 경우 index 값 수정
			if (li.get(2).getElementsByTag("strong").text().equals("페이지 :")) {
				date = li.get(1).getElementsByTag("span").text();
			} else {
				date = li.get(2).getElementsByTag("span").text();
			}	
			
			Element p = doc.select(".pbr").get(0);
			int price = Integer.parseInt(p.text().replace(",", "").replace("원", ""));
			
			// p_code 구하기
			String p_code = path.substring(path.indexOf("=")+1);
			
			s.setP_code(p_code);
			s.setBookname(title);
			s.setWriter(writer);
			s.setPrice(price);
			s.setRegDate(date);
			
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(s);
		return s;
	}
	
	@GetMapping("/search")
	public String searchList(String keyword){
		String url = "https://www.hanbit.co.kr/store/books/new_book_list.html?keyWord="+keyword+"&searchKey=all";
		try {
			Document doc = Jsoup.connect(url).get();
			Elements titles = doc.select(".book_tit");
			for (Element e: titles) {
				Element a = e.firstElementChild();
				// 링크 주소 : /look.php?p_code=B7859887743
				String addr = a.attr("href");
				
				dao.save(getInfo(addr));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "ok";
	}
	
	@GetMapping("/downAll")
	public String downAll() {
		int i = 1;
		try {
			while (true) {
				String url = "https://www.hanbit.co.kr/store/books/new_book_list.html?page="+i;
				Document doc = Jsoup.connect(url).get();
				Elements elments = doc.select(".view_box");
				
				if (elments.size() == 0) {
					break;
				}
				
				for(Element e: elments) {
					Elements img = e.getElementsByTag("img");
					String src = img.get(img.size()-1).attr("src");
					String title = e.select(".book_tit").get(0).getElementsByTag("a").get(0).text();
					// 특수만지 있으면 저장 에러
					imageDownload(base + src, title.replaceAll("[/?!@#:'\']", "_"));
					
					// img태그에서 원하는 값 가져오는지 확인
//					Elements imgs = e.getElementsByTag("img");
//					for (Element img : imgs) {
//						String src = img.attr("src");
//					}
				}
					
				i++;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "ok";
	}
	
	
	public void imageDownload(String addr, String fname) {
		try {
			URL url = new URL(addr);
			InputStream is = url.openStream();
			FileOutputStream fos = new FileOutputStream("C:/data/"+fname+".jpg");
			FileCopyUtils.copy(is.readAllBytes(), fos);
			is.close();
			fos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	
	@GetMapping("/downImg")
	public String downImg() {
		String[] arr = {
				"/data/books/B7027415255_m.jpg",
				"/data/books/B9717903507_m.jpg",
				"/data/books/B7085900692_m.jpg"};
		String[] fname = {
				"퀵드로잉",
				"LEANHR",
				"내 안전은 내가 지켜요"
		};
		
		for (int i = 0; i <arr.length; i++) {
			imageDownload(arr[i], fname[i]);
		}	
		return "ok";
	}
	
	/*
	// 이미지 가져오기
	@GetMapping("/downImg")
	public String downImg() {
		// 이미지 주소
		String addr = base + "/data/books/B7027415255_m.jpg";
		try {
			URL url = new URL(addr);
			// 파일명 설정
			String fname = "퀵드로잉.jpg";
			InputStream is = url.openStream();
			FileOutputStream fos = new FileOutputStream("C:\\data\\"+fname);
			FileCopyUtils.copy(is.readAllBytes(), fos);
			is.close();
			fos.close();
			System.out.println("이미지 다운 완료");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "ok";
	}
	*/
	
	@GetMapping("/newbook")
	public List<NewBook> newBook(Model model) {
		List<NewBook> bookList = new ArrayList<NewBook>();
		
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
				
//				Elements images = doc.select(".thumb");
//				for (Element e:images) {
//					System.out.println(e.attr("src"));
//				}
				
				for (Element e : list) {
					// 첫번째 자식 Element를 반환
					Element a = e.firstElementChild();
					// attr로 속성 접근
					String link = base + a.attr("href");
					String text = a.text();

					bookList.add(new NewBook(text, link));
				}
				
//				for (int j = 0; j < list.size(); j++) {
//					Element a = list.get(j).firstElementChild();
//					// 도서 상세 주소
//					String link = base + a.attr("href");
//					// 도서명
//					String text = a.text();
//					
//					Element image = images.get(j);		
//					String addr = image.attr("src");
//					
//					imageDownload(base+addr, text.replaceAll("[/?!@#:'\']", ""));
//					System.out.println("이미지 다운 완료");
//					bookList.add(new NewBook(text, link));
//				}
				
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
}
