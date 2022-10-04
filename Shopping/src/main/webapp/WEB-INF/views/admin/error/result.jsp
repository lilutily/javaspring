<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background:yellow">
<%
	RuntimeException e=(RuntimeException)request.getAttribute("e");
	out.print("이용 불가능.<p>");
	out.print(e.getMessage());
 %>
</body>
</html>