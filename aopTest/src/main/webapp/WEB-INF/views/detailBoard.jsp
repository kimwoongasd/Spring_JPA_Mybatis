<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
번호 : ${b.no }<br>
제목 : ${b.title }<br>
글쓴이 : ${b.writer }<br>
내용 : <textarea rows="10" cols="80">${b.content }</textarea> <br>
작성일 :${b.regdate }<br>
조회수 : ${b.hit }<br>
이미지 : ${b.fname }<br>
ip : ${b.ip }<br>
<a href="insertBoard?no=${b.no }">답글작성</a>
<a href="listBoard">목록으로</a>
<a href="">수정</a>
<a href="">삭제</a>
</body>
</html>