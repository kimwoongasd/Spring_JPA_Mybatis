package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BoardVO;
import com.example.demo.vo.GoodsVO;

public class DBManager {
	private static SqlSessionFactory factory;
	
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static int maxRecord() {
		int record = 0;
		SqlSession session = factory.openSession();
		record = session.selectOne("board.maxRecord");
		return record;
	}
	
	public static int updateStep(int b_ref, int b_step) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		
		// 복수일 경우 map에 넣어 보낸다
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("b_ref", b_ref);
		map.put("b_step", b_step);
		session.update("board.updateStep", map);
		session.close();
		
		return re;
	}
	
	public static int boardNextNo() {
		int no = 1;
		SqlSession session = factory.openSession();
		no = session.selectOne("board.boardNextNo");
		
		return no;
	}
	
	public static int insertBoard(BoardVO b) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.insert("board.insertBoard", b);
		session.close();
		
		return re;
	}
	
	public static BoardVO detailBoard(int no) {
		BoardVO b = new BoardVO();
		SqlSession session = factory.openSession();
		b = session.selectOne("board.detailBoard", no);
		session.close();
		
		return b;
	}
	
	public static List<BoardVO> listBoard(String pageNum) {
		List<BoardVO> list = null;
		int start = 0;
		int end = 0;
		int num = 1;
		
		if (pageNum != null && !pageNum.equals("")) {
			num = Integer.parseInt(pageNum);
		}
		
		start = (num - 1) * 10 + 1;
		end = num * 10;
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		
		SqlSession session = factory.openSession();
		list = session.selectList("board.listBoard", map);
		session.close();
		return list;
	}
	
	
	public static List<GoodsVO> findAll() {
		List<GoodsVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("goods.findAll");
		session.close();
		return list;
	}
	
	public static int nextNo() {
		int no = 0;
		SqlSession session = factory.openSession();
		no = session.selectOne("goods.nextNo");
		session.close();
		
		return no;
	}
	
	public static int insert(GoodsVO g) {
		int re = -1;
		// true를 주면 따로 커밋을 하지 않아도 된다
		SqlSession session = factory.openSession(true);
		re = session.insert("goods.insert", g);
		session.close();
		
		return re;
	}
	
	public static GoodsVO detail(int no) {
		GoodsVO g = new GoodsVO();
		SqlSession session = factory.openSession();
		g = session.selectOne("goods.detail", no);
		session.close();
		
		return g;
	}
	
	public static int update(GoodsVO g) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.update("goods.update", g);
		session.close();
		
		return re;
	}
	
	public static int delete(int no) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re= session.delete("goods.delete", no);
		session.close();
		
		return re;
	}
}
