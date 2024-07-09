<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script src="https://accounts.google.com/gsi/client" async defer></script>
<script>
  function signOut() {
            $.ajax({
                url: '/logout',
                type: 'POST',
                success: function() {
                    console.log('User logged out');
                    window.location.href = '/loginForm'; // 로그아웃 후 리디렉션
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error('Error:', textStatus, errorThrown);
                }
            });
        }
</script>
        
</head>
<body>
<h1>My Page</h1>
<pre>${MY_userInfo}</pre>
<button onclick="signOut()">Logout</button>
    
</body>
</html>