<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2>부서목록</h2>
<hr>
<table border="1">
	<thead>
		<th>부서번호</th>
		<th>부서명</th>
		<th>부서위치</th>
	</thead>
	<tbody>
		<c:forEach var="d" items="${list }">
		<tr>
			<td>${d.dno }</td>
			<td>${d.dname }</td>
			<td>${d.dloc }</td>
		</tr>	
		</c:forEach>
	</tbody>
</table>
</body>
</html>