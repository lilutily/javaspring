<%@page import="com.academy.springdb.model.domain.News"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%
	List<News> newsList=(List)request.getAttribute("newsList");
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 16px;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

.page-style {
	font-size:20px;
	font-weight:bold;
	color:red;
}
a {text-decoration:none;}
</style>
</head>
<body>

<table>
  <tr>
    <th width="7%">No</th>
    <th width="57%">기사제목</th>
    <th width="12%">작성자</th>
    <th width="12%">작성일</th>
    <th width="12%">조회수</th>
  </tr>
  <!--하나의 페이지에 너무 많은 데이터가 있을 경우, 원하는 크기로 분리하여 보여주는 기법을 페이징 처리라한다
  페이징 처리는 결국 데이터에 대한 산수계산이므로, 개발자마다 본인 스스로 로직을 개발해야함 -->
	
	<%for(int i=0; i<newsList.size(); i++) { %>
	<%News news= newsList.get(i); %>
  <tr>
    <td><%//=%></td>
    <td>
    	<a href="/news/content?news_id=<%=news.getNews_id()%>"><%=news.getTitle()%></a>
    	[<%=news.getCommentsList().size() %>]
    </td>
    <td><%=news.getWriter()%></td>
    <td><%=news.getRegdate()%></td>
    <td><%=news.getHit()%></td>
  </tr>
  <%} %>
 <tr>
 	<td colspan="5" style= "text-align:center">

 	</td>
 </tr>
 <tr>
 	<td colspan="5"><button onClick="location.href='/news/registform';">글작성</button></td>
 </tr>
</table>

</body>
</html>
