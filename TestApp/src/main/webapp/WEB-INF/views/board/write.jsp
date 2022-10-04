<%@page import="java.util.List"%>
<%@page import="com.academy.testapp.model.demain.Board"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%
	List<Board> boardList=(List)request.getAttribute("boardList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://unpkg.com/react@18/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@18/umd/react-dom.development.js" crossorigin></script>
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>
<script>

// 게시판 목록을 비동기로 가져와서, 동적으로 디자인을 출력하되(React를 이용)
function getList() {
	$.ajax({
		url:"/board/getlist",
		type:"get",
		success:function(result, status, xhr) {
			alert(result)
			ReactDOM.render(document.getElementById('listArea'));
		}
	});
	
}

function regist() {
	var formArray=$("form").serializeArray();
	var json={}; //empty
	for(var i=0; i<formArray.length; i++) {
		json[formArray[i].name]=formArray[i].value;
	}
	console.log("전송전 json구성 : "+json);
	$.ajax({
		url:"/board/regist",
		type:"post",
		contentType:"application/json;charset=utf-8",
		data:JSON.stringify(json), // convert json to String
		success:function(result, status ,xhr) {
			alert(result.msg);
			getList();
		},
		error:function(xhr, status, error) {
			alert(error.msg);
		}
	});
}

</script>
</head>
<body>
	<form>
	<table width="70%" align="center" border="1px">
		<tr>
			<td>제목</td>
			<td><input type="text" name="title"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="button" onClick="regist()">등록</button>
				<button type="button" onClick="getList()">목록</button>
			</td>
		</tr>
	</table>
	</form>
	<div id="listArea">
		<form>
		<%for(int i=0; i<boardList.size(); i++) { %>
		<%Board board=boardList.get(i); %>
	<table width="70%" align="center" border="1px">
		<tr>
			<td>No</td>
			<td><input type="text" name="title" value="<%=i%>"></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" value="<%=board.getTitle()%>"></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><input type="text" name="writer" value="<%=board.getWriter()%>"></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content"><%=board.getContent() %></textarea></td>
		</tr>
	</table>
	<%} %>
	</form>
	</div>
</body>
</html>