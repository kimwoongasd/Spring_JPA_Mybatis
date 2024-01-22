<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
상품번호 : ${g.no }<br>
상품명 : ${g.name }<br>
가격 : ${g.price }<br>
수량 : ${g.qty }<br>
이미지 : <img src="images/${g.fname }"/><br>
<a href="listGoods">목록으로</a>
<a href="updateGoods?no=${g.no }">수정</a>
<a href="deleteGoods?no=${g.no }">삭제</a>
</body>
</html>