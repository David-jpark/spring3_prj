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

파일 업로드 테스트
<br>
	<!-- 단일 파일 업로드 -->
	<!-- 파일 업로드에서는 enctype(인코딩타입)을 multipart/form-data로 반드시 설정 -->
	<form action="/file_upload" method="post" enctype="multipart/form-data">
		파일 선택 : <input type="file" name="ufile">
		<input type="submit" value="전송">
	</form>
	
	<br><hr><br>
	
	<!-- 복수 파일 업로드-->
	<form action="/file_upload2" method="post" enctype="multipart/form-data">
		파일 선택 : <input type="file" multiple="multiple" name="ufiles"> 
		<input type="submit" value="전송">
	</form>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script> $(function() { }); </script>
</body>
</html>