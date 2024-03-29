package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.vo.BookVO;

@Repository
public class BookDAO {
	public ArrayList<BookVO> findAll() {
		ArrayList<BookVO> list = new ArrayList<>();
		String sql = "select * from book";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
					"c##som",
					"1234");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				list.add(new BookVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getString(4)));
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
}
