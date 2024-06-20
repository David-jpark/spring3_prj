<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
<!-- <select name="search_gubun" id="search_gubun">
	<option value='regid'>아이디</option>
	<option value='title'>제목</option>
</select> -->

<input type="text" name="search_str" id="search_str">
<input type="button" id="searchBtn" value="검색">
<div id= "searchResDiv"></div><br>

<table border="1" width="100%">
<c:forEach items="${KEY_BOARDLIST}" var="bvo">
<tr>	
	<td>${bvo.seq}</td>
	<td><a href = "/board_detail?seq=${bvo.seq}">${bvo.title}</a></td>
	<td>${bvo.regdate}</td>
</tr>
</c:forEach>
</table>

<script>
$(function(){
			//검색어 입력시 관련 내용 자동완성 :: REST
	$("#search_str").on('input', function() {
		if($("#search_str").val() != "") {
		$.ajax({
			method      : "POST",
	        url         : "${pageContext.request.contextPath}/board_search",
	        contentType : "application/x-www-form-urlencoded; charset=UTF-8",
	        data 		: "search_str="+ $("#search_str").val() ,
	  		error 	    : function(myval){ console.log("에러:" + myval);   },
	  		success     : function(myval){ 
	  										console.log("성공:" + myval);
	  										
	  										var htmlStr = "<ul>";
	  										$.map( myval, function( MYval, MYidx ) {
	  											htmlStr += "<li>" + MYval.title+"</li>";
	  										}); //select * from board where title like '%검색어%'
	  											htmlStr += "</ul>";
	  										$("#searchResDiv").empty();
	  										$("#searchResDiv").html(htmlStr);
	  										
				}
			
			});
		} else {
			$("#searchResDiv").empty();
		}
	});
});
</script>
<p><br>
<button type="button" onclick="location.href='/board_list'">동해물과백두산이마르고닳도록하느님이보우하사우리나라만세</button>
</body>
</html>