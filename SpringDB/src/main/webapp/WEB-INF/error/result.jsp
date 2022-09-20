<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러처리 페이지</title>
</head>
<body>
	<h1>이용 불가능</h1>
	<%=request.getAttribute("msg") %>
</body>
</html>