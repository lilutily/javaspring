<%@page import="com.academy.shopping.model.domain.OrderDetail"%>
<%@page import="org.apache.xmlbeans.impl.soap.Detail"%>
<%@page import="com.academy.shopping.model.domain.OrderSummary"%>
<%@page import="com.academy.shopping.model.domain.SubCategory"%>
<%@page import="com.academy.shopping.model.domain.Product"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
		OrderSummary orderSummary=(OrderSummary)request.getAttribute("orderSummary");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Advanced form elements</title>

<%@ include file="../inc/header_link.jsp" %>
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
        <!-- /.row -->
        <div class="row">
          <div class="col-12">
            <div class="card">
              <div class="card-header">
                <h3 class="card-title">주문 상세 정보</h3>

                <div class="card-tools">
                  <div class="input-group input-group-sm" style="width: 150px;">
                    <input type="text" name="table_search" class="form-control float-right" placeholder="Search">

                    <div class="input-group-append">
                      <button type="submit" class="btn btn-default">
                        <i class="fas fa-search"></i>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- /.card-header -->
              <div class="card-body table-responsive p-0">
              <table class="table table-hover text-nowrap">
              		<thead>
	              		<tr>
	              			<th>주문번호</th>
	              			<th>주문일시</th>
	              			<th>구매자이름</th>
	              		</tr>
              		</thead>
              		<tbody>
              			<tr>
	              			<th><%=orderSummary.getOrdersummary_id() %></th>
	              			<th><%=orderSummary.getBuydate() %></th>
	              			<th><%=orderSummary.getMember().getCustomer_name() %></th>
	              		</tr>
              		</tbody>
              </table>
                <table class="table table-hover text-nowrap">
                
                  <thead>
                   
                    <tr>
                      <th>NO</th>
                      <th>주문번호</th>
                      <td>이미지</td>
                      <th>상품명</th>
                      <th>총결제금액</th>
                      <th>구매개수</th>
                    </tr>
                    
                  </thead>
                  
                  <tbody>
                  <%for(int i=0; i<orderSummary.getOrderDetailList().size(); i++) { %>
                  <%OrderDetail orderDetail= orderSummary.getOrderDetailList().get(i);%>
                    <tr>
                      <td><%=i %></td>
                      <td><%=orderDetail.getProduct().getSubcategory().getCategory_name()%></td>
                      <td><img src="/static/data/<%=orderDetail.getProduct().getProduct_img()%> "width="45px"></td>
                      <td><%=orderDetail.getProduct().getProduct_name() %></td>
                      <td><%=orderDetail.getProduct().getDiscount()%></td>
                      <td><%=orderDetail.getEa()%></td>
                    </tr>
                    <%} %>
                  </tbody>
                </table>
                <button class="btn btn-primary" onClick="location.href='/admin/product/registform';">상품등록</button>
                <button class="btn btn-primary" onClick="showExcel();">엑셀등록</button>
                <div style="display:none" id="excel-area">
                	<form id="excel-form">
               	 		<input type="file" name="excel">
                		<button type="button" class="btn btn-info" onClick="registExcel()">등록</button>
                	</form>
                </div>
              </div>
              <!-- /.card-body -->
            </div>
            <!-- /.card -->
          </div>
        </div>
        <!-- /.row -->
        <!-- SELECT2 EXAMPLE -->
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

// 가려져 있던 엑셀 등록폼을 등장시키자
function showExcel() {
	$("#excel-area").css({
		"display":"block"
	});	
}
// 엑셀파일을 이용한 상품일괄 등록
function registExcel() {
	if($("input[name='excel']").val()=="") {
		alert("선택한 파일이 없습니다");
		return;
	}
	if(confirm("엑셀로 등록할까요?")) {
	$("#excel-form").attr({
		"action":"/admin/product/excel",
		"method":"post",
		"enctype":"multipart/form-data"
	});
	$("#excel-form").submit();
	}
}


$(function() {
	getTopList();
	
	$($("select")[0]).change(function() {
		//alert("당신이 선택한 아이템의 value 값은 : " + $(this).val());
		getSubList($(this).val());
	});
	// 파일 선택 컴포넌트에 이벤트 연결
	$("input[name='excel']").change(function() {
		// 선택한 파일구하기
		console.log($(this).val());
		var ext=getExt($(this).val()); // xls (97~03) , xlsx(최신판)
		if(ext!="xls" && ext!="xlsx") {
			alert("엑셀 선택 할 것");
		} 
	});
});
</script>
</body>
</html>
