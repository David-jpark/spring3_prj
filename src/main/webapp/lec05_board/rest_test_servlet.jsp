<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>  
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
REST TEST<hr>
1. String -- String<br>
<input type="text" name="ename">
<input type="button" id="str-str" value="str-str"><hr>

2. JSON -- String<br>
<input type="text" name="ename">
<input type="button" id="json-str" value="json-str"><hr>

3. String -- JSON<br>
<input type="text" name="ename" id = "ename">
<input type="button" id="str-json" value="str-json"><hr>

3. JSON -- JSON<br>
<input type="text" name="ename" id = "ename2">
<input type="button" id="json-json" value="json-json"><hr>

<div id ="resultDIV"></div>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
$(function() {
	//----공통 테이블 그리기----
	function makeTable(myval) {
		
		var htmlStr = "<table border=1 width=50%><tr><th>제목</th><th>글쓴이</th></tr>";
		// 테이블 만들기
		$.map(myval, function( MYval, MYidx ) { //function( val, 변수)
			htmlStr +="<tr><td>"+ MYval["title"] +"</td><td>"+ MYval["regid"] +"</td></tr>" 
			console.log(MYval["title"] +","+ MYval["regid"]+ ","+ MYidx);
		});
		htmlStr +="</table>";
		$("#resultDIV").empty();
		$("#resultDIV").html(htmlStr);
	}
	
	$("#str-str").click(  function(){
		var ename_val = $("#ename").val();
	$.ajax({
		method : "GET",
		url : "${pageContext.request.contextPath}/RestServletTest",
		data : "ename="+ ename_val ,
		error : function(myval){ console.log("에러"+myval); },
		success : function(myval){ 
							console.log("변환 전 성공"+myval);
							myval = JSON.parse(myval);
							console.log("변환 후 성공"+myval);
			
							makeTable(myval);
							//$("#resultDIV").html("<b>"+myval[0]["title"]+"</b>")
			}
		
		});
	});
	
	$("#json-str").click(  function(){
		var objData = {"title":"asssd","regid":"hong"};
		
		$.ajax({
			method : "POST",
			url : "${pageContext.request.contextPath}/RestServletTest",
			contentType : "application/json; charset=UTF-8", //*핵심
			data : JSON.stringify(objData) ,
			error : function(myval){ console.log("에러"+myval); },
			success : function(myval){
				console.log("변환 전 성공"+myval);
				myval = JSON.parse(myval);
				console.log("변환 후 성공"+myval);
				//makeTable(myval);
			}
			
		});
	});
	
	$("#str-json").click(  function(){
		var ename_val = $("#ename").val();
	$.ajax({
		method : "POST",
		url : "${pageContext.request.contextPath}/RestServletTest",
		data : "ename="+ ename_val ,
		dataType : "json" ,
		error : function(myval){ console.log("에러"+myval); },
		success : function(myval){ 
				console.log(myval);			
				makeTable(myval);
		}
		
		});
	});
	
	
	$("#json-json").click(  function(){
		var objData = {"title":"asssd","regid":"hong"};
		
		$.ajax({
			method : "POST",
			url : "${pageContext.request.contextPath}/RestServletTest",
			contentType : "application/json; charset=UTF-8", //*핵심
			data : JSON.stringify(objData) ,
			error : function(myval){ console.log("에러"+myval); },
			success : function(myval){
				console.log(myval);
				makeTable(myval);
			}
			
		});
	});
});


</script>
</body>
</html>