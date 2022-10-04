<%@page import="com.academy.testapp.model.demain.Board"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%
	List<Board> boardList=(List)request.getAttribute("boardList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="70%" align="center" border="1px">
		<thead>
			<tr>
				<th>NO</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
		<%for(int i=0; i<boardList.size(); i++) { %>
		<%Board board=boardList.get(i); %>
			<tr>
				<th><%=i %></th>
				<th><%=board.getTitle() %></th>
				<th><%=board.getWriter() %></th>
				<th><%=board.getRegdate() %></th>
				<th><%=board.getHit() %></th>
			</tr>
			<%} %>
			<tr>
				<td colspan="5">
					<button onClick="location.href='/board/writeform';">글등록</button>
				</td>
			</tr>
		</tbody>
		
	</table>
</body>
</html>