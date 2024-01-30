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
	<c:if test="${id != null}">
		${id }님이 로그인하였습니다
		<a href="/logout">로그아웃</a>
	</c:if>
	<c:if test="${id == null}">
		${id }님이 로그인하였습니다
		<a href="/login">로그아웃</a>
	</c:if>
</body>
</html>