package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Board;

import jakarta.transaction.Transactional;

@Repository
public interface BoardDAO extends JpaRepository<Board, Integer> {
	
	@Query(value = "select b.* from "
			+ "(select a.*, rownum as rm from "
			+ "(select * from board order by b_ref desc, b_step) a) b "
			+ "where rm between ?1 and ?2", nativeQuery = true)
	public List<Board> selectAll(int start, int end);
	
	@Query(value = "select nvl(max(no),0) + 1 from Board", nativeQuery = true)
	public int getNextNo();
	
	@Query(value = "select count(*) from Board where writer = ?1", nativeQuery = true)
	public int getTotalMyRecord(String id);
	
	@Modifying	// 변경사항이 있을 겨우 사용
	@Transactional
	@Query(value = "insert into Board b(b.no, b.title, b.writer, b.pwd, b.content, b.regdate, b.hit, b.fname, b.b_ref, b.b_level, b.b_step) values(:#{#b.no},:#{#b.title},:#{#b.member.id}, :#{#b.pwd},:#{#b.content},sysdate,0,:#{#b.fname},:#{#b.b_ref},:#{#b.b_level}, :#{#b.b_step})", nativeQuery = true)
	public void insert(Board b);
	
	@Modifying
	@Transactional
	@Query(value = "update Board set b_step=b_step+1 where b_ref = ?1 and b_step > ?2", nativeQuery = true)
	public void updateStep(int b_ref, int b_step);
	
	@Modifying
	@Query(value = "update Board b set b.title=:#{#b.title}, b.content=:#{#b.content}, b.fname=:#{#b.fname} where b.no=:#{#b.no} and b.pwd=:#{#b.pwd}", nativeQuery = true)
	@Transactional
	public int updateBoard(Board b);
	
	
	@Modifying
	@Transactional
	@Query(value = "delete Board where no=?1 and pwd = ?2", nativeQuery = true)
	public int deleteBoard(int no, String pwd);
	
	@Query(value = "select b.* from "
			+ "(select a.*, rownum as rm from "
			+ "(select * from board where writer = ?1 order by b_ref desc, b_step) a) b "
			+ "where rm between ?2 and ?3", nativeQuery = true)
	public List<Board> mySelectAll(String id, int start, int end);
}
