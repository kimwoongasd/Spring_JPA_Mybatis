package com.example.demo.util;

import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.example.demo.vo.EmpVO;

@Component // 저절로 스캔
public class EmpUtil {
	public String getHTML(EmpVO e) {
		String str = "";
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH) + 1;
		
		str += "<h2>"+year+"년 "+month+"월 </h2>";
		str += "<hr>";
		str += "<table border='1'>";
		str += "<tr>";
		str += "<td>사원번호</td>";
		str += "<td>이름</td>";
		str += "<td>부서번호</td>";
		str += "<td>급여</td>";
		str += "<td>수당</td>";
		str += "<td>실수령액</td>";
		str += "</tr>";
		str += "<tr>";
		str += "<td>"+e.getEno()+"</td>";
		str += "<td>"+e.getEname()+"</td>";
		str += "<td>"+e.getDno()+"</td>";
		str += "<td>"+e.getSalary()+"</td>";
		str += "<td>"+e.getComm()+"</td>";
		str += "<td>"+(e.getComm()+e.getSalary())+"</td>";
		str += "</tr>";
		str += "</table>";
		
		return str;
	}
}
