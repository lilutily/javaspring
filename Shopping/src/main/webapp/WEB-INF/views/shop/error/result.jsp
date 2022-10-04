<%@page import="com.academy.shopping.model.domain.SubCategory"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ashion Template">
    <meta name="keywords" content="Ashion, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Ashion | Template</title>

   <%@ include file="../inc/css.jsp" %>
</head>

<body>
<%-- <%@ include file="../inc/topbar.jsp" %> --%>
 <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="./index.html"><i class="fa fa-home"></i> Home</a>
                        <span>Shop</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->
    <!-- Shop Cart Section Begin -->
    <section class="shop-cart spad" style="background:yellow">
        <div class="container">
            <div class="row">
            	이용 불가능. <p>
            	<%
            		RuntimeException e = (RuntimeException)request.getAttribute("e");
            		out.print(e.getMessage());
            	%>
            </div>
        </div>
    </section>
    <!-- Shop Cart Section End -->

<%-- <%@ include file="../inc/insta.jsp" %> --%>
<%@ include file="../inc/footer.jsp" %>
<%@ include file="../inc/search.jsp" %>

<!-- Js Plugins -->
<%@ include file="../inc/plugin.jsp" %>
<script>
function login() {
	$.ajax({
		url:"/rest/member/login",
		type:"post",
		data: {
			customer_id: $("#customer_id").val(),
			customer_pass:$("#customer_pass").val()
		},
		success:function(result, status, xhr) {
			//alert(result.msg);
			if(result.code==1) { // 성공하면 페이지를 새로고침하여 login버튼을 logout으로 전환
				location.href="/shop";
			}
		}
	});
}
$(function(){
	// 회원등록 버튼에 이벤트 연결
	$($("form button")[0]).click(function() { // 로그인
		login();
	});
	$($("form button")[1]).click(function() { // 가입
		location.href="/shop/member/registform";
		
	});
});
</script>
</body>

</html>