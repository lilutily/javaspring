<%@page import="com.academy.shopping.model.domain.Product"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Product product = (Product)request.getAttribute("product");

	// 현재 상품의 최상위 카테고리 가져오기
	int topcategory_id=product.getSubcategory().getTopcategory().getTopcategory_id();
	//out.print(topcategory_id);
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Advanced form elements</title>

<%@ include file="../inc/header_link.jsp" %>
<style>
.col-md-9 *{
	margin: 2px;
}
</style>
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
<%@ include file="../inc/topbar.jsp" %>  
<%@ include file="../inc/sidebar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>상품목록</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">Advanced Form</li>
            </ol>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- SELECT2 EXAMPLE -->
        <div class="card card-primary">
        <form>
        	<input type="hidden" name="product_id" value="<%=product.getProduct_id()%>">
        	<input type="hidden" name="product_img" value="<%=product.getProduct_img()%>">
          <div class="card-header">
            <h3 class="card-title">상품등록</h3>
            <div class="card-tools">
              <button type="button" class="btn btn-tool" data-card-widget="collapse">
                <i class="fas fa-minus"></i>
              </button>
              <button type="button" class="btn btn-tool" data-card-widget="remove">
                <i class="fas fa-times"></i>
              </button>
            </div>
          </div>
          <!-- /.card-header -->
          <div class="card-body">
            <div class="row">
            	<!-- 좌측 3 영역차지 bedin -->
            	<div class="col-md-3">
            		<div class="col-md-12">
	                	<div class="form-group">
	                 		 <label>상위 카테고리</label>
	                 		 <select name="topcategory.topcategory_id" class="form-control select" style="width: 100%;" size="7">
	                 		 </select>
	              	  	</div>
             		 </div>
               		<div class="col-md-12">
               		 	<div class="form-group">
                 			 <label>하위 카테고리</label>
                  			<select name="subcategory.subcategory_id" class="form-control select" style="width: 100%;" size="7">
                  			</select>
             		  </div>
            		</div>
          	    </div>
            	<!-- 좌측 3 영역 차지 end -->
              	<!-- 우측 9 영역 차지 begin -->
              	
            	<div class="col-md-9">
            		<input type="text" class="form-control" value="<%=product.getProduct_name() %>" name="product_name">
            		<input type="text" class="form-control" value="<%=product.getBrand() %>" name="brand">
            		<input type="number" class="form-control" value="<%=product.getPrice() %>" name="price">
            		<input type="number" class="form-control" value="<%=product.getDiscount() %>" name="discount">
            		<textarea class="form-control" name="memo"><%=product.getMemo() %></textarea>
            		<textarea id="summernote" class="form-control" name="detail"><%=product.getDetail() %></textarea>
            		<input type="file" class="form-control" name="photo">
            		<button type="button" class="btn btn-info" onClick="editProduct()">상픔수정</button>
            		<button type="button"  class="btn btn-info" onClick="deleteProduct()">상품삭제</button>
            		<button type="button"  class="btn btn-info" onClick="location.href='/admin/product/list';">목록보기</button>
            	</div>
            	
            	<!-- 우측 9 영역 차지 end -->
            </div>
            <!-- /.row -->
          </div>
          <!-- /.card-body -->
          <div class="card-footer">
          	원하시는 아이템을 선택 후 삭제 및 수정이 가능합니다.
          </div>
          </form>
        </div>
        <!-- /.card -->
      </div>
      <!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="float-right d-none d-sm-block">
      <b>Version</b> 3.2.0
    </div>
    <strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->


<%@ include file="../inc/footer_link.jsp" %>
<script>
function getTopList() {
	$.ajax({
		url:"/rest/admin/topcategory",
		type:"get",
		success:function(result, status,xhr) {
			printTopList(result);
		}
	});
}

function printTopList(jsonList) {	
	var sel =$($("select")[0]);
	$(sel).empty(); //기존의 아이템을 초기화
	var tag ="";
	for(var i = 0; i<jsonList.length; i++) {
		var topcategory = jsonList[i]; //상위 카테고리 json
		tag+="<option value=\""+topcategory.topcategory_id+"\">"+topcategory.category_name+"</option>";
	}
	$(sel).append(tag);
	
	$(sel).val(<%=topcategory_id%>);
	getSubList(<%=topcategory_id%>);
}

//상위 카테고리 비동기 등록요청
function registTop() {
	$.ajax({
		url:"/rest/admin/topcategory",
		type:"post",
		data:{
			category_name:$($("input[name='category_name'][0]")).val()
		},
		success:function(result, status, xhr) {
			getTopList();
		},
		error:function(xhr,status, error) {
			alert(status + ","+error);
		}
	});
}
//하위 카테고리에 대한 비동기 등록요청
//주의 )반드시 상위 카테고리가 하나라도 선택이 되어 있어야 한다. (유효성 체크)
function registSub() {
	if($($("select")[0]).prop('selectedIndex') == -1) {
		alert("좌측 영역에서 상위 카테고리를 한개라도 선택하세요");
		 return;
	}
	//비동기 요청
	$.ajax({
		url:"/rest/admin/subcategory",
		type:"post",
		data:{
			"category_name":$($("input[name='category_name']")[1]).val(),
			"topcategory.topcategory_id":$($("select")[0]).val()
			},
		success:function(result, status, xhr) {
			console.log(result);
			getSubList($($("select")[0]).val());
		},
			error:function(xhr, status, error) {
			
			}
	});
	
}
function getSubList(topcategory_id) {
	$.ajax({
		url:"/rest/admin/subcategory/"+topcategory_id,
		type:"get",
		success:function(result, status, xhr) {
			printSubList(result);
		},
		error:function(xhr, status, error) {
			
		}
	});
}

function printSubList(jsonList) {
	var sel =$($("select")[1]);
	$(sel).empty(); //기존의 아이템을 초기화
	var tag ="";
	for(var i = 0; i<jsonList.length; i++) {
		var obj = jsonList[i]; //상위 카테고리 json
		tag+="<option value=\""+obj.subcategory_id+"\">"+obj.category_name+"</option>";
	}
	$(sel).append(tag);
	
	// 생성된 옵션들을 대상으로 선택효과
	$(sel).val(<%=product.getSubcategory().getSubcategory_id()%>);
}

// 상품 등록요청
function registProduct() {
	if(confirm("상품을 등록하시겠습니까?")) {
		$("form").attr({
			"action":"/admin/product/regist",
			"method":"post",
			"enctype":"multipart/form-data"
		});
		$("form").submit();
	}	
}

// 비동기 전송시, json으로 key-value를 일일이 form을 포기하고 작성해야 하는건 너무 불편하다
// 시리얼화 시켜, 편의성을 높이자
function deleteProduct() {
	// 기존의 폼 양식을 전송할 수 있도록 쪼개자 (직렬화-분해시킴) , key-value의 쌍으로 분리시킴
	var formArray=$("form").serializeArray();
	
	// 서버에 전송시 json으로 보내기
	var json={};
	for(var i=0; i<formArray.length; i++) {
		json[formArray[i].name]=formArray[i].value;
	}
	console.log(json);
	
	$.ajax({ 
		url:"/rest/admin/product/delete",
		type:"post",
		contentType:"application/json;charset=utf-8", // 서버에게 이 자료가 json형태라는 것을 알려줌(헤더에서)
		data:JSON.stringify(json), // json은 자체로 객체이므로, 전송하려면 문자열화 시켜야한다
		success:function(result, status, xhr) {
			alret(result);
			location.href="/admin/product/list";
		}
	}); 
}

// 수정요청 - 비동기+파일업로드
//FormData - json만으로는 보낼 수 없었던 바이너리 파일까지도 보낼 수 있다
function editProduct() {
	// 기존폼을 시리얼: key-value쌍으로 구성
	var formArray=$("form").serializeArray();
	//console.log(formArray);
	//json 대신 바이너리 파일을 포함할 수 있는 FormData를 이용하자
	var formData= new FormData();
	
	//formData.append(전송파라미터명, 전송데이터);
	for(var i=0; i<formArray.length; i++) {
		formData.append(formArray[i].name, formArray[i].value);
	}
	// 특히 input type="file" 컴포넌트는 텍스트가 아니므로, 실제 선택한 파일을 포함...
	formData.append("photo", $("input[name='photo']")[0].files[0]);
	
	//ajax 전송
	$.ajax({
		url:"/rest/admin/product/update",
		type:"post",
		data:formData,
		enctype:"multipart/form-data",
		contentType:false, // 문자열화 시킴 방지(바이너리 파일이 포함될 경우 이 속성을 false로준다)
		processData:false, // 문자열화 시킴 방지(바이너리 파일이 포함될 경우 이 속성을 false로준다)
		success:function(result,status,xhr){
			alert(result);
		}
	});
}

$(function() {
	 $('#summernote').summernote({  // summernote 가져오기
		 height:200
	 });
	 
	getTopList(); // 상위 카테고리 가져오기
	
	$($("select")[0]).change(function() {
		//alert("당신이 선택한 아이템의 value 값은 : " + $(this).val());
		getSubList($(this).val());
	});
});


</script>
</body>
</html>
