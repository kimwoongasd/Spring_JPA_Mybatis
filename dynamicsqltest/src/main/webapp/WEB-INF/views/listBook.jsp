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
	<form action="listBook?keyword=${keyword }&category=${category}">
	<select id="category" name="category">
		<option value="bookname">도서명</option>
		<option value="publisher">출판사</option>
	</select>
		<input type="search" id="keyword" name="keyword">
		<input type="submit" value="검색">
	</form>
	<table border="1" width="80%">
		<tr>
			<th><a href="listBook?sColumn=bookid&keyword=${keyword }&category=${category}">도서번호</a></th>
			<th><a href="listBook?sColumn=bookname&keyword=${keyword }&category=${category}">도서명</a></th>
			<th><a href="listBook?sColumn=price&keyword=${keyword }&category=${category}">가격</a></th>
			<th><a href="listBook?sColumn=publisher&keyword=${keyword }&category=${category}">출판사</a></th>
		</tr>
		
		<c:forEach var="b"  items="${list }">
			<tr>
				<td>${b.bookid }</td>
				<td>
					<a href="detailBook?bookid=${b.bookid }">${b.bookname }</a>
				</td>
				<td>${b.price }</td>
				<td>${b.publisher }</td>
			</tr>			
		</c:forEach>
	</table>
	
	<a href="insertBoard">추가하기</a>
</body>
</html>