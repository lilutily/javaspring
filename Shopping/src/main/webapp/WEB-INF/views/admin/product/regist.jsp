<%@ page contentType="text/html;charset=UTF-8"%>
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
            		<input type="text" class="form-control" placeholder="상품명" name="product_name">
            		<input type="text" class="form-control" placeholder="브랜드" name="brand">
            		<input type="number" class="form-control" placeholder="가격" name="price">
            		<input type="number" class="form-control" placeholder="할인가격" name="discount">
            		<textarea class="form-control" placeholder="간략설명" name="memo"></textarea>
            		<textarea id="summernote" class="form-control" placeholder="간략설명" name="detail"></textarea>
            		<input type="file" class="form-control" placeholder="상품 이미지 선택" name="photo">
            		<button type="button" class="btn btn-info" onClick="registProduct()">상픔등록</button>
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

$(function() {
	 $('#summernote').summernote({  // summernote 가져오기
		 height:200
	 });
	 
	getTopList(); // 상위 카테고리 가져오기
	getSubList(); // 하위 카테고리 가져오기
	
	$($("select")[0]).change(function() {
		//alert("당신이 선택한 아이템의 value 값은 : " + $(this).val());
		getSubList($(this).val());
	});
});


</script>
</body>
</html>
