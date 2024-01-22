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
	<h2>부서목록</h2>
	<hr>
	<a href="insertDept">부서등록</a>
	<table>
	<thead>
		<th>번호</th>
		<th>이름</th>
		<th>지역</th>
		<th>수정/삭제</th>
	</thead>
	<tbody>
		<c:forEach var="d" items="${list }">
			<tr>
				<td>${d.dno }</td>
				<td>${d.dname }</td>
				<td>${d.dloc }</td>
				<td>
					<a href="updateDept?dno=${d.dno }&dname=${d.dname}&dloc=${d.dloc}">수정</a>
					<a href="deleteDept?dno=${d.dno }">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>