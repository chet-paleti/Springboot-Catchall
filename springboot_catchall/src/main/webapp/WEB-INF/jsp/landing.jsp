<%@page import="org.apache.catalina.Server"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

This is a welcome page !
<br>
<%

String fname = "racket.jpg";


%>

<!-- <img id=robin src="${pageContext.request.contextPath}/racket.jpg" width="500" height="500""> -->

<img id=robin-2 src="pic" width="500" height="500"">
<br>
<a href='pic'>Test</a>
<br>
<form method="POST" enctype="multipart/form-data" action="save_pic">
<input type="file" name="file" />
<input type="submit" value="Upload" />
</form>


</body>
</html>