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

<c:forEach var="user" items="${users}">

<h3>
${user.name}
</h3>

<p>
${user.email}
<br>
${user.addr.get("street")} , ${user.addr.get("suite")}
<br>
${user.addr.get("city")}
<br>
</p>
</c:forEach>

</body>
</html>