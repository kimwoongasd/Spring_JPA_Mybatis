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
<form method="post" action="insertBoard" enctype="multipart/form-data">
<input type="hidden" name="no" value="${no }">
	제목 : <input type="text" name="title"><br>
	작성자 : <input type="text" name="writer"><br>
	비밀번호 : <input type="password" name="pwd"><br>
	내용 : <textarea rows="10" cols="80" name="content"></textarea><br>
	상품사진 : <input type="file" name="uploadFile"><br>
	<input type="submit" value="등록">
	<input type="reset" value="다시입력">
</form>
</body>
</html>