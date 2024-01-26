package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.EmpVO;

public class DBManager {
	private static SqlSessionFactory factory;
	
	static {
		try {
			String resource = "com/example/demo/db/SqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static List<EmpVO> empList() {
		List<EmpVO> list = null;
		SqlSession session = factory.openSession();
		list = session.selectList("emp.listEmp");
		
		return list;
	}
}
