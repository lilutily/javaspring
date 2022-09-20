<%@page import="com.academy.springmvcsimple.domain.Notice"%>
<%@page import="java.util.List"%>
<%@page import="com.academy.springmvcsimple.util.Pager"%>
<%@page import="com.academy.springmvcsimple.model.repository.NoticeDAO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%! 
		Pager pager =new Pager();
%>
<%
	List<Notice> boardList= (List)request.getAttribute("boardList");
	out.print("게시물 수 : " + boardList.size());
	pager.init(boardList, request); // 공식이 자동계산
	
	// jsp의 내장객체 중 application 내장객체의 생명력을 테스트해보자
	// 이름 그대로 application(웹 어플리케이션) 은 Tomcat서버를 가동할때 생성되어
	// 프로그램이 끝날때까지 즉 Tomcat 서버를 종료할때까지 생명력을 유지할 수 있다.
	// application scope
	application.setAttribute("id","batman");
	out.print(application.getAttribute("id"));
	out.print(application.getAttribute("nick"));
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
  <%
  		int curPos=pager.getCurPos();
  		int num=pager.getNum();
  %>
	<%for(int i=1; i<=pager.getPageSize(); i++) { %>
	<%if(num<1) break; %>
	<%Notice notice= boardList.get(curPos++); %>
  <tr>
    <td><%=num-- %></td>
    <td>
    	<a href="/board/content?notice_id=<%=notice.getNotice_id()%>"><%=notice.getTitle() %></a>
    </td>
    <td><%=notice.getWriter()%></td>
    <td><%=notice.getRegdate()%></td>
    <td><%=notice.getHit()%></td>
  </tr>
	 <% }%>
 <tr>
 	<td colspan="5" style= "text-align:center">
 		<%for(int i=pager.getFirstPage(); i<=pager.getLastPage(); i++ ) { %>
 		<%if(i>pager.getTotalPage()) break; %>
 		[<%=i %>]
		<%} %>
 	</td>
 </tr>
 <tr>
 	<td colspan="5"><button onClick="location.href='/board/regist.jsp';">글작성</button></td>
 </tr>
</table>

</body>
</html>
