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
SPRING REST TEST<hr>
1. String -- String<br>
<input type="text" name="ename">
<input type="button" id="str-str" value="str-str"><hr>

2. JSON -- String<br>
<input type="text" name="ename">
<input type="button" id="json-str" value="json-str"><hr>

3. String -- JSON<br>
<input type="text" name="ename" id = "ename">
<input type="button" id="str-json" value="str-json"><hr>

4. JSON -- JSON<br>
<input type="text" name="ename" id = "ename2">
<input type="button" id="json-json" value="json-json"><hr>

5. 일반적 형태(String -- JSON)<br>
<form id = "regForm">
<input type="text" name="title" id = "title">
<input type="text" name="regid" id = "regid">
<input type="button" id="writeButton" value="write"><hr>
</form>

6. REST Controller<br>
<input type="text" name="ename3" id = "ename3">
<input type="button" id="rest-str-str" value="rest-str-str"><hr>
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
		method : "POST",
		url : "${pageContext.request.contextPath}/ctl_str_str",
		data : "ename="+ ename_val ,
		error : function(myval){ console.log("에러"+myval); },
		success : function(myval){ 
							console.log("1.Spring 응답 : "+myval);
							//myval = JSON.parse(myval);
							//console.log("변환 후 성공"+myval);
							//makeTable(myval);
						
			}
		
		});
	});
	
	$("#json-str").click(  function(){
		var objData = {"title":"asssd","regid":"hong"};
		
		$.ajax({
			method : "POST",
			url : "${pageContext.request.contextPath}/ctl_json_str",
			contentType : "application/json; charset=UTF-8", //*핵심
			data : JSON.stringify(objData) ,
			error : function(myval){ console.log("에러"+myval); },
			success : function(myval){
				console.log("2. Spring 응답 : "+myval);
				//makeTable(myval);
			}
			
		});
	});
	
	$("#str-json").click(  function(){
		var ename_val = $("#ename").val();
	$.ajax({
		method : "POST",
		url : "${pageContext.request.contextPath}/ctl_str_json",
		data : "ename="+ ename_val ,
		dataType : "json" ,
		error : function(myval){ console.log("에러"+myval); },
		success : function(myval, textStatus, xhr){ //상태나 헤더를 찍어볼 수 있다.
			if (xhr.status == 200){
				console.log("3. Spring 응답 : "+myval["message"]);
			}
		}
		
		});
	});
	
	
	$("#json-json").click(  function(){
		var objData = {"title":"asssd","regid":"hong"};
		
		$.ajax({
			method : "POST",
			url : "${pageContext.request.contextPath}/ctl_json_json",
			contentType : "application/json; charset=UTF-8", //*핵심
			data : JSON.stringify(objData) ,
			error : function(myval){ console.log("에러"+myval); },
			success : function(myval){
				console.log("4. Spring 응답 : "+myval);
				//makeTable(myval);
			}
			
		});
	});
	
	$("#writeButton").click(  function(){
		var sendFormData = $("#regForm").serialize();// title=aaa&regid=kim
		$.ajax({
			method : "POST",
			url : "${pageContext.request.contextPath}/ctl_normal",
			data : sendFormData,
			error : function(myval){ console.log("에러"+myval); },
			success : function(myval){
				console.log(myval);
				//makeTable(myval);
			}
			
		});
	});
	
	//-------------------------------------------------------------------------
	//------------------------REST Controller----------------------------------
	
	$("#rest-str-str").click(  function(){
		var ename_val = $("#ename").val();
	$.ajax({
		method : "POST",
		url : "${pageContext.request.contextPath}/restctl_str_str",
		data : "ename="+ ename_val ,
		error : function(myval){ console.log("에러"+myval); },
		success : function(myval){ 
							console.log(myval);
							
						
			}
		
		});
	});
});


</script>
</body>
</html>