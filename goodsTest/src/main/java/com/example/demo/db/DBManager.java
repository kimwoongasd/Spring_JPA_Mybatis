package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
