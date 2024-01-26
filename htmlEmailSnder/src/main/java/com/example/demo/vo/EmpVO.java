package com.example.demo.vo;

import java.util.Date;

import lombok.Data;

@Data
public class EmpVO {
	private int eno;
	private String ename;
	private String job;
	private Date hiredate;
	private int salary;
	private int dno;
	private int mgr;
	private int comm;
	private String jumin;
	private String email;
}
