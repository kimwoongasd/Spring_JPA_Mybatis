package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.MemberVO;

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
	
	public static MemberVO findByID(String id) {
		MemberVO m = null;
		SqlSession session = factory.openSession();
		m = session.selectOne("member.findByID", id);
		session.close();
		return m;
	}
	
	public static int insertMember(MemberVO m){
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.insert("member.insert", m);
		session.close();
		return re;
	}
}
