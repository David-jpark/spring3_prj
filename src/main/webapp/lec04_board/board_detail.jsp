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


<table width=100% border=1>
<c:set var="rlist" value="${KEY_REPLYLIST}" />
<c:forEach var="rvo" items="${rlist}">
	<c:if test="${rvo.reply != null}">
		<tr>
			<td>
			댓글 번호: ${rvo.rseq}
			댓글 내용: ${rvo.reply}
			아이디: ${rvo.regid}
			작성일: ${rvo.regdate}
			<button onclick="showEditForm('${rvo.reply}', '${rvo.rseq}')">수정</button>
			<font color=red><a href="/reply_del?seq=${bvo.seq}&rseq=${rvo.rseq}">[X]</a></font>
			</td>
		</tr>
	</c:if>
</c:forEach>
</table>
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

<form method="post" id = "reply" action="${pageContext.request.contextPath}/reply_insert">
<input type=hidden name=seq value="${bvo.seq}">
<table width=100% border=1>
<tr>
	<td>
		<input type="text" size=100 name="reply" id ="reply">
		<input id = "replyInsertBtn" type="submit" value="댓글등록">
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
	
</body>
</html>


