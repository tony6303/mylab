<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>list.jsp</h1>
<table>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>내용</th>
	</tr>
	<c:forEach var="post" items="${ posts}">
		<tr>
		<td>${post.id }</td>
		<td>${post.title }</td>
		<td>${post.content }</td>
	</tr>
	</c:forEach>
	
</table>
</body>
</html>