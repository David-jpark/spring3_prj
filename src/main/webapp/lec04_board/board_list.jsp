<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="100%">
<c:forEach items="${KEY_BOARDLIST}" var="bvo">
<tr>	
	<td>${bvo.seq}</td>
	<td><a href = "/board_detail?seq=${bvo.seq}">${bvo.title}</a></td>
	<td>${bvo.regdate}</td>
</tr>
</c:forEach>
</table>
<p><br>
<button type="button" onclick="location.href='/board_list'">동해물과백두산이마르고닳도록하느님이보우하사우리나라만세</button>
</body>
</html>