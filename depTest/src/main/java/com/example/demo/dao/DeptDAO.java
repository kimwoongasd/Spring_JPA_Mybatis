package com.example.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.demo.vo.DeptVO;

@Repository 
public class DeptDAO {
	public int deleteDept(int dno) {
		int re = -1;
		String sql = "delete dept where dno=?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "c##som", "1234");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dno);
			re = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("삭제");
		}
		
		return re;
	}
	
	public int updateDept(DeptVO d) {
		int re = -1;
		String sql = "update dept set dname=?, dloc=? where dno=?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "c##som", "1234");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, d.getDname());
			pstmt.setString(2, d.getDloc());
			pstmt.setInt(3, d.getDno());
			
			re = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("수정 " + e.getMessage());
		}
		
		return re;
	}
	
	public DeptVO findDept(int dno) {
		DeptVO v = new DeptVO();
		String sql = "select * from dept where dno=?";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "c##som", "1234");
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dno);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				v.setDno(rs.getInt(1));
				v.setDname(rs.getString(2));
				v.setDloc(rs.getString(3));
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("상세 " + e.getMessage());
		}
		
		return v;
	}
	
	public int insertDept(DeptVO d) {
	      String sql = "insert into dept values(?,?,?)";
	      int re = 0;
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "c##som", "1234");
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, d.getDno());
	         pstmt.setString(2, d.getDname());
	         pstmt.setString(3, d.getDloc());
	         re = pstmt.executeUpdate();
	         pstmt.close();
	         conn.close();
	      } catch (Exception e) {
	         System.out.println("insertDept 예외발생 : "+e.getMessage());
	      }
	      return re;
	   }
	
	public ArrayList<DeptVO> findAll() {
		ArrayList<DeptVO> list = new ArrayList<>();
		String sql = "select * from dept";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
					"c##som",
					"1234");
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				list.add(new DeptVO(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3)));
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.println("dept 목록 " + e.getMessage());
		}
		
		return list;
	}
}
