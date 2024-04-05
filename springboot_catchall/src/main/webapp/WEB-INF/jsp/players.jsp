<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<c:forEach var="player" items="${playerlist}">

<h3>

${player.player_name}

</h3>

</c:forEach>

<select name="myList">
<c:forEach var="player" items="${playerlist}">
<option value="${player.id}">${player.player_name}</option>
</c:forEach>
</select>








</body>
</html>