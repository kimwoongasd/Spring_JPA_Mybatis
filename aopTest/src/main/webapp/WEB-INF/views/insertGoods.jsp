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
<h2>상품 등록</h2>
<hr>
<form action="insertGoods" method="post" enctype="multipart/form-data">
	상품번호 : <input type="number" name="no" value="${no }" readonly="readonly"><br>
	상품명 : <input type="text" name="name"><br>
	가격 : <input type="number" name="price"><br>
	수량 : <input type="number" name="qty"><br>
	상품사진 : <input type="file" name="uploadFile"><br>
	<input type="submit" value="등록">
	<input type="reset" value="다시입력">
</form>
</body>
</html>