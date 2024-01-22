<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h2>목록</h2>
<hr>
<table>
	<thead>
		<th>번호</th>
		<th>이름</th>
		<th>지역</th>
		<th>추가</th>
	</thead>
	<tbody>
		<c:forEach var="d" items="${list }">
			<tr>
				<td>${d.dno }</td>
				<td>${d.dname }</td>
				<td>${d.dloc }</td>
				<td>
				<a href="updateDept?dno=${d.dno }">수정</a>
				<a href="deleteDept?dno=${d.dno }">삭제</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a href="insertDept">등록</a>
</body>
</html>