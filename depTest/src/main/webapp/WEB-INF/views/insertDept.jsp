<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="insertDept" method="post">
	부서번호 : <input type="text" name="dno">
	부서이름 : <input type="text" name="dname">
	부서위치 : <input type="text" name="dloc">
	<input type="submit" value="등록">
	<input type="reset" value="다시입력">
</form>
</body>
</html>