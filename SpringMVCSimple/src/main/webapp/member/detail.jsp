<%@page import="com.academy.springmvcsimple.domain.Emp"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
	Emp emp = (Emp) request.getAttribute("emp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원등록</title>
<%@ include file="/inc/header.jsp"%>
</head>
<body>
	<div class="card card-info">
		<div class="card-header">
			<h3 class="card-title">사원등록</h3>
		</div>
		<!-- /.card-header -->
		<!-- form start -->
		<form class="form-horizontal">
			<div class="card-body">
				<div class="form-group row">
					<label for="inputEmail3" class="col-sm-2 col-form-label">사원명</label>
					<div class="col-sm-10">
						<input type="email" class="form-control" name="ename" value="<%=emp.getEname()%>">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">희망급여</label>
					<div class="col-sm-10">
						<input type="number" class="form-control" name="sal" value="<%=emp.getSal()%>">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">입사일</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="hiredate" value="<%=emp.getHiredate()%>">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">희망부서</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="dept.dname" value="<%=emp.getDept().getDname()%>">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">부서위치</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" name="dept.loc" value="<%=emp.getDept().getLoc()%>">
					</div>
				</div>
			</div>
			<!-- /.card-body -->
			<div class="card-footer">
				<button type="button" class="btn btn-info" onClick="location.href='/member/regist.jsp';">사원 등록</button>
				<button type="submit" class="btn btn-default float-right">Cancel</button>
			</div>
			<!-- /.card-footer -->
		</form>
	</div>
<%@ include file="/inc/footer.jsp"%>
</body>
</html>