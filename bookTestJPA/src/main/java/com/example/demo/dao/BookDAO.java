package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.BookVO;

@Repository
public interface BookDAO extends JpaRepository<BookVO, Integer> {

	// 도서명 순으로 정렬하는 쿼리 메소드 추가
	public List<BookVO> findAllByOrderByBookname();
	public List<BookVO> findAllByOrderByBookid();

	public List<BookVO> findAllByOrderByPrice();

	public List<BookVO> findAllByOrderByPublisher();

	// equal로 동작한다
//	public List<BookVO> findByBooknameLike(String name);

	

	// 검색
	// Containing, Like는 int, integer가 안된다
	public List<BookVO> findByBooknameContaining(String bookname);

	public List<BookVO> findByBookid(int bookid);

	public List<BookVO> findByPrice(int price);

	public List<BookVO> findByPublisherContaining(String publisher);

	public List<BookVO> findByBooknameContainingOrderByBookname(String bookname);

	public List<BookVO> findByBookidOrderByBookname(int bookid);

	public List<BookVO> findByPriceOrderByBookname(int price);

	public List<BookVO> findByPublisherContainingOrderByBookname(String publisher);

	public List<BookVO> findByBooknameContainingOrderByBookid(String bookname);

	public List<BookVO> findByBookidOrderByBookid(int bookid);

	public List<BookVO> findByPriceOrderByBookid(int price);

	public List<BookVO> findByPublisherContainingOrderByBookid(String publisher);

	public List<BookVO> findByBooknameContainingOrderByPrice(String bookname);

	public List<BookVO> findByBookidOrderByPrice(int bookid);

	public List<BookVO> findByPriceOrderByPrice(int price);

	public List<BookVO> findByPublisherContainingOrderByPrice(String publisher);

	public List<BookVO> findByBooknameContainingOrderByPublisher(String bookname);

	public List<BookVO> findByBookidOrderByPublisher(int bookid);

	public List<BookVO> findByPriceOrderByPublisher(int price);

	public List<BookVO> findByPublisherContainingOrderByPublisher(String publisher);
}
