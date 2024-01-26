<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>상품 수정</h2>
<hr>
<form action="updateGoods" method="post" enctype="multipart/form-data">
	상품번호 : <input type="number" name="no" value="${g.no }" readonly="readonly"><br>
	상품명 : <input type="text" name="name" value="${g.name }"><br>
	가격 : <input type="number" name="price" value="${g.price }"><br>
	수량 : <input type="number" name="qty" value="${g.qty }"><br>
	<input type="hidden" name="fname" value="${g.fname }">
	상품사진 : <input type="file" name="uploadFile"><br>
	<input type="submit" value="등록">
	<input type="reset" value="다시입력">
</form>
</body>
</html>