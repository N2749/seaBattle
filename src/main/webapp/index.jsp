<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	String userName = null;
	String userId = null;
	Cookie[] cookies = request.getCookies();

	if (cookies == null) return;
	
	for (Cookie cookie : cookies) {
		if (cookie.getName().equals("userName")) {
			userName = cookie.getValue();
		}
	}
	%>
	<h3>
		May the odds be on your side
		<%=userName%>
	</h3>
</body>
</html>