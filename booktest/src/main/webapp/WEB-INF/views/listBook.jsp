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
<table>
	<thead>
		<th>å��ȣ</th>
		<th>å�̸�</th>
		<th>����</th>
		<th>���ǻ�</th>
	</thead>
	<tbody>
		<c:forEach var="b" items="${list }">
			<tr>
				<td>${b.bookid }</td>
				<td>${b.bookname }</td>
				<td>${b.price }</td>
				<td>${b.publisher }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>