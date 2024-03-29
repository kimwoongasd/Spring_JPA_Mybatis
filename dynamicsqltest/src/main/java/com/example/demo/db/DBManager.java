package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.AopVO;
import com.example.demo.vo.BookVO;
import com.example.demo.vo.DeptVO;

public class DBManager {
	// 마이바티즈 설정파일의 sql을 요청하기 위한 담당자
	private static SqlSessionFactory factory;
	
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			// 파일 읽어옴
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println("db 매니저 " +e.getMessage());
		}
	}
	
	public static int insertAop(AopVO a) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.insert("aop.insertAop", a);
		session.close();
		
		return re;
	}
	
	public static BookVO detailBook(int bookid) {
		BookVO b = new BookVO();
		SqlSession session = factory.openSession();
		b = session.selectOne("book.finByNo", bookid);
		return b;
	}
	
	public static List<BookVO> listBook(String sColumn, String category, String keyword) {
		List<BookVO> list = null;
		SqlSession session = factory.openSession();
		HashMap<String, String> map = new HashMap<>();
		map.put("category", category);
		map.put("sColumn", sColumn);
		map.put("keyword", keyword);
		
		list = session.selectList("book.findAll", map);
		session.close();
		
		return list;
	}
	
	public static List<DeptVO> findAll() {
		List<DeptVO> list = null;
		// 마이바티스 설정파일의 sql을 요청하기 위하여 sqlSession을 얻어온다
		SqlSession session = factory.openSession();
		// selectList(1, 2) : 파라미터가2개 필요할시
		// where이나 insert등 넣을 값이 필요할 경우
		// 여러개의 값을 전달해야한다면 map을 통해서 전달한다
		list = session.selectList("dept.findAll");
		session.close();
		
		return list;
	}
	
	public static int insert(DeptVO d) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.insert("dept.insert", d);
		// 데이터베이스에 변동이 있는 sql(insert, update, delete)인 경우
		// commit를 해야 반영이 된다
		session.commit();
		session.close();
		
		return re;
	}
	
	public static DeptVO findByDno(int dno) {
		DeptVO d = new DeptVO();
		SqlSession session = factory.openSession();
		// 한개를 받아올 때는 selectOne
		d = session.selectOne("dept.findByDno", dno);
		session.close();
		return d;
	}
	
	public static int update(DeptVO d) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.update("dept.update", d);
		session.commit();
		session.close();
		
		return re;
	}
	
	
	public static int delete(int dno) {
		int re = -1;
		SqlSession session = factory.openSession();
		re = session.delete("dept.delete", dno);
		session.commit();
		session.close();
		
		return re;
	}
}
