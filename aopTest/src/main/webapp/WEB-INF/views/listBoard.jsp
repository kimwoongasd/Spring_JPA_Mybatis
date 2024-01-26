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
<h2>게시물 목록</h2>
	<hr>
	<table border="1" width="80%">
		<tr>
			<th>글번호</th>
			<th>글제목</th>
			<th>작성자</th>
			<th>등록일</th>
		</tr>
		
		<c:forEach var="b"  items="${list }">
			<tr>
				<td>${b.no }</td>
				
				<td>
					<c:if test="${b.b_level > 0 }">
						<c:forEach var="i" begin="1" end="${b.b_level }">
							&nbsp;&nbsp;
						</c:forEach>
						<img src="images/re.png" width="20" height="20">
					</c:if>
					<a href="detailBoard?no=${b.no }">${b.title }</a>
				</td>
				<td>${b.writer }</td>
				<td>${b.regdate }</td>
			</tr>			
		</c:forEach>
	</table>
	<c:forEach var="j" begin="1" end="${totalPage }">
		<a href="listBoard?pageNum=${j }">${j }</a>
	</c:forEach>
	<a href="insertBoard">추가하기</a>
</body>
</html>