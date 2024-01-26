package com.example.demo.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 매개변수를 가지는 생성자를 생성해줌
@NoArgsConstructor // 기본생성자도 생성
public class AopVO {
	private int no;
	private String uri;
	private String ip;
	private String method;
	private String request;
	private long delay;
}
