<%@ page language="java"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Tables - SB Admin</title>
        <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
         <script>
        function showEditForm(reply, rseq) {
            // 수정할 댓글의 내용을 수정 폼에 설정
            document.getElementById('editReplyContent').value = reply;
            document.getElementById('editReplySeq').value = rseq;

            // 수정 폼을 표시
            document.getElementById('editReplyForm').style.display = 'block';
        }

        function hideEditForm() {
            // 수정 폼을 숨김
            document.getElementById('editReplyForm').style.display = 'none';
        }
    </script>
    </head>
<body>
<!-- --------------------------------------- 게시물 상세보기 ------------------------------- -->
<c:set var="bvo" value="${KEY_BOARDVO}" />
<form id="boardForm" >
<input type="hidden" name="seq" value="${bvo.seq}">
<input type="hidden" name="regid" value="${bvo.regid}">

<table border="1" width="100%">
<tr>
   <th width="20%">글번호</th>
   <td width="80%">${bvo.seq}</td>
</tr>
<tr>
      <th>작성자</th>
      <td>${bvo.regid}</td>
</tr>
<tr>
      <th>작성일</th>
      <td><input type="text" name="regdate" value="${bvo.regdate}" readonly></td>
</tr>
<tr>
      <th>제목</th>
      <td><input type="text" name="title" size=60  value="${bvo.title}"></td>
</tr>
<tr>
      <th>내용</th>
      <td><textarea name="contents" cols="80" rows="6">${bvo.contents}</textarea></td>
</tr>
<tr>
	<td colspan=2 align="center">
		<input type="button" id="uptButton" value="수정">
		<input type="button" id="delButton" value="삭제">
		<input type="button" id="listButton" value="목록">
	</td>
</table>
</form>
<!-- --------------------------------------- 댓글목록 ------------------------------- -->

<div id= "replyListDiv">
<table width=100% border=1>
</table>
</div>

<div id="editReplyForm" style="display:none;">
    <form action="/reply_update" method="post">
        <input type="hidden" name="seq" value="${bvo.seq}" />
        <input type="hidden" id="editReplySeq" name="rseq" />
        <p>수정할 댓글: <textarea id="editReplyContent" name="reply"></textarea></p>
        <button type="submit">저장</button>
        <button type="button" onclick="hideEditForm()">취소</button>
    </form>
</div>
<!-- --------------------------------------- 댓글등록 ------------------------------- -->

<!-- <form method="post" action="/reply_insert_rest"> -->
<form method = "post" id = "replyInsertForm">
<input type=hidden name=seq value="${bvo.seq}">
<table width=100% border=1>
<tr>
	<td>
		<input type="text" size=100 name="reply" id ="reply">
		<input id = "replyInsertBtn" type="button" value="댓글등록">
	</td>
</tr>
</table>
</form>

    <script>
		$(function() {
			//---------------------------------------------------------
			// <form> 제어하기
			//---------------------------------------------------------
			//$(".btn.btn-primary.btn-block").click()~~
			
			$("#uptButton").click(  function(){
				alert("수정");
				$("#boardForm").attr("method","post");
				$("#boardForm").attr("action","${pageContext.request.contextPath}/board_update");
				$("#boardForm").submit();
				return true;
			});
			$("#delButton").click(  function(){
				alert("삭제");
				$("#boardForm").attr("method","post");
				$("#boardForm").attr("action","${pageContext.request.contextPath}/board_del");
				$("#boardForm").submit();
				return true;
			});
			$("#listButton").click(  function(){
				location.href = "${pageContext.request.contextPath}/board_list";
			});            
			
			//------------------------------------------------------------
			
		});
		
	</script>
	<script>
$(function(){
		function makeTable() {
			//화면 준비되면 댓글목록 바로 가져오기 :::  REST
			var seq = ${bvo.seq};
			$.ajax({
				method      : "POST",
		        url         : "${pageContext.request.contextPath}/reply_list_rest",
		        contentType : "application/x-www-form-urlencoded; charset=UTF-8",
		        data 		: "seq=${bvo.seq}",
		  		error 	    : function(myval){ console.log("에러:" + myval);   },
		  		success     : function(myval){ 
		  										console.log("성공:" + myval);  //ResponseEntity<>(rlist, HttpStatus.OK)   
		  										
		  										//------------------------------form
		  										//var htmlStr = "<form><table border=1 width=50%>";
		  										//$.map( myval, function( MYval, MYidx ) {
		  											//htmlStr += "<tr><td><input type='button value='[x]' class='replyDelBtn2' data-seq='"+ MYval.seq+"' data-rseq='"+ MYval.rseq + "'>" + MYval.reply + "</td></tr>";
		  											//console.log(MYval["title"] + "," + MYval["regid"] + "," + MYidx);
		  										//});
		  										//htmlStr += "</table></form>";
		  										//$("#replyListDiv").empty();
		  										//$("#replyListDiv").html(htmlStr);
		  										//-------------------------------href
		  										var htmlStr = "<table border=1 width=50%>";
		  										$.map( myval, function( MYval, MYidx ) {
		  											htmlStr += "<tr><td><font color=red><a href='#' class='replyDelBtn' data-seq='"+ MYval.seq+"' data-rseq='"+ MYval.rseq + "'>[X]</a></font>" + MYval.reply + "</td></tr>";
		  											console.log(MYval["title"] + "," + MYval["regid"] + "," + MYidx);
		  										});
		  										htmlStr += "</table>";
		  										$("#replyListDiv").empty();
		  										$("#replyListDiv").html(htmlStr);
		  										
		  									}
			});
		}
		
		//화면이 실행되자마자 댓글목록 가져오기 :: REST
		makeTable();
		
	//댓글입력 :: REST
	$("#replyInsertBtn").click(  function(){
		var sendFormData = $("#replyInsertForm").serialize();
		$.ajax({
			method : "POST",
			url : "${pageContext.request.contextPath}/reply_insert_rest",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : sendFormData ,
			error : function(myval){ console.log("에러"+myval); },
			success : function(myval){
				console.log(myval);
				makeTable();
			}
			
		});
	});
	
	//댓글삭제
	//<a href='#' class='replyDelBtn' data-seq='"+ MYval.seq+"' data-rseq='"+ MYval.rseq + "'>
	// id는 고유하기때문에 id를 쓰기보다 class를 쓰는게 좋다.
	$(document).on("click", ".replyDelBtn", function(){
		var seq =$(this).attr("data-seq");
		var rseq =$(this).data("rseq"); //data-rseq 
		$.ajax({
			method : "POST",
			url : "${pageContext.request.contextPath}/reply_del_rest",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : "seq="+seq+ "&rseq="+rseq ,
			error : function(myval){ console.log("에러"+myval); },
			success : function(myval){
				console.log(myval);
				makeTable();
			}
			
		});
	});
	// 댓글 삭제 (form)
	$(document).on("click", ".replyDelBtn", function(){
		var seq =$(this).attr("data-seq");
		var rseq =$(this).data("rseq"); //data-rseq 
		$.ajax({
			method : "POST",
			url : "${pageContext.request.contextPath}/reply_del_rest",
			contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			data : "seq="+seq+ "&rseq="+rseq ,
			error : function(myval){ console.log("에러"+myval); },
			success : function(myval){
				console.log(myval);
				makeTable();
			}
			
		});
	});
});
</script>
</body>
</html>


