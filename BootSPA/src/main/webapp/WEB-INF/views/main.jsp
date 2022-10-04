<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body{margin:0px;}
#wrapper {
	width:100%;
	height:720px;
	margin:auto;
	overflow:hidden;
}
#input-area {
	width:20%;
	height:100%;
	background-color:yellow;
	float:left;
	
}

#list-area {
	width:60%;
	height:100%;
	background-color: cyan;
	float:left;
	overflow:scroll;
}
#detail-area {
	width:20%;
	height:100%;
	background-color: orange;
	float:left;
}
#input-area input,#detail-area input, #list-area input{
	width:97%;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://unpkg.com/react@18/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@18/umd/react-dom.development.js" crossorigin></script>
<script src="https://unpkg.com/@babel/standalone/babel.min.js"></script>

<script type="text/babel">

// 동기방식의 폼 전송
function regist() {
	$("#input-form").attr({
		action:"/board/regist",
		method:"post"
	});
	$("#input-form").submit();
}
// 비동기 방식의 기존폼을 이용한 Parameter 전송
function registBySerial() {
	var params=$("#input-form").serialize();
	console.log(params);
	
	// 이미 전송할 파라미터화가 완료되었으므로 , JSON으로 변환하지 말고 그냥 보내자
	$.ajax({
		url:"/rest/serial/board",
		type:"post",
		data:params,
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		success:function(result, status, xhr) {
			getList();
		},
		error:function(xhr, status, error) {
			alert(error.msg);
		}
	});
}
// 비동기 방식의 기존폼을 이용한 Json문자열 전송
function registByJson() {
	var formArray=$("#input-form").serializeArray();
	console.log(formArray);
	
	// 우리가 원하는 형태의 JSON으로 
	var json={};
	for(var i=0; i<formArray.length; i++) {
		json[formArray[i].name]=formArray[i].value;
	}
	console.log(json);
	
	// JSON 전송시 주의점 : JSON 객체 자체는 전송불가하므로, JSON문자열로 변환
	$.ajax({
		url:"/rest/json/board",
		type:"post",
		data:JSON.stringify(json),
		contentType:"application/json;charset=utf-8",
		success:function(result, status, xhr){
			getList();
		},
		error:function(xhr, status, error){
			alert(error.msg);
		}
	});
}
// 비동기 방식으로 목록 가져오기
function getList() {
	$.ajax({
		url:"/rest/board",
		type:"get",
		success:function(result, status, xhr) {
			console.log("서버로부터 받은 json 목록 : "+ result);
			printList(result); // json 배열을 넘겨주자
		}
	});
}
// 비동기 방식으로 한건의 데이터 가져오기
function getDetail(board_id) {
	$.ajax({
		url:"/rest/board/"+board_id,
		type:"get",
		success:function(result, status, xhr) {
			console.log(result);
			printBoard(result);
		}
	});
}
// 우측 영역의 폼에 한건 출력
function printBoard(board) {
	$("#detail-form input[name='board_id']").val(board.board_id);
	$("#detail-form input[name='title']").val(board.title);
	$("#detail-form input[name='writer']").val(board.writer);
	$("#detail-form textarea[name='content']").val(board.content);
}
/*----------------------------------
React를 이용한 UI처리
------------------------------------*/


function Row(props) {
	var link="javascript:getDetail("+props.board_id+")";
	return (
		<tr align="center">
			<td>{props.board_id}</td>
			<td><a href={link}>{props.title}</a></td>
			<td>{props.writer}</td>
			<td>{props.regdate}</td>
			<td>{props.hit}</td>
		</tr>
	);
}
function BoardTable(props) {
	var list=props.boardList;
	// tr을 반복한 콘텐츠 구성
	var tag=[]; // 여기에 tr을 모아두자
	for(var i=0; i<list.length; i++) {
		var obj=list[i]; // 게시물 한건 꺼내기
		tag.push(<Row board_id={obj.board_id} title={obj.title} writer={obj.writer} regdate={obj.regdate} hit={obj.hit}/>); // arraylist.add()와 동일
	}
	return (
		<table width="100%" border="1px">
			<thead>
				<tr>
					<th>No</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				{tag}
			</tbody>
		</table>
	);
}
// 화면에 테이블 출력함수
function printList(jsonArray) {
	var root=ReactDOM.createRoot(document.getElementById("list-area"));
	root.render(<BoardTable boardList={jsonArray}/>);
}

// 수정요청
function edit() {
	// 비동기 요청시 기존 폼을 이용하는 법(파라미터 방식 , json 전송)
	var params=$("#detail-form").serialize(); // 파라미터=값&파라미터=값... 형식의 쿼리스트링이 만들어짐
	console.log("수정값"+params);
	if(confirm("수정하시겠습니까?")) {
		$.ajax({
			url:"/rest/board",
			type:"put",
			data:params,
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(result, status, xhr){
				console.log(result);
				getList();
			},
			error:function(xhr, status, error) {
				alert(error);
			}
		});
	}
}

function del() {
	if(confirm("삭제하시겠어요?")) {
		$.ajax({
			url:"/rest/board?board_id="+$("#detail-form input[name='board_id']").val(),
			type:"delete",
			success:function(result, status, xhr) {
				getList();
			},
			error:function(xhr, status, error) {
				alert(error);
			}
		})
	}
}

$(function() {
	$($("#input-area button")[0]).click(function(){
		regist();
	});
});
$(function() {
	$($("#input-area button")[1]).click(function(){
		registBySerial();
	});
});
$(function() {
	$($("#input-area button")[2]).click(function(){
		registByJson();
	});

	// 상세보기 폼의 버튼 이벤트처리
	$($("#detail-area button")[0]).click(function(){ // 수정
		edit();
	});
	$($("#detail-area button")[1]).click(function(){ // 삭제
		del();
	});
	getList();
});
</script>
</head>
<body>
		<div id="wrapper">
			<div id="input-area">
				<form id="input-form">
					<input type="text" name="title" placeholder="제목입력">
					<input type="text" name="writer" placeholder="작성자입력">
					<textarea name="content" style="width:98%; height:150px;"></textarea>
					<button type="button">그냥등록</button>
					<button type="button">폼시리얼 등록</button>
					<button type="button">JSON전송</button>
				</form>
			</div>
			
			<div id="list-area">
			</div>
	
			<div id="detail-area">
				<form id="detail-form">
					<input type="hidden" name="board_id">
					<input type="text" name="title" placeholder="제목입력">
					<input type="text" name="writer" placeholder="작성자입력">
					<textarea name="content" style="width:98%; height:150px;"></textarea>
					<button type="button">수정</button>
					<button type="button">삭제</button>
				</form>
			</div>
		</div>
</body>
</html>