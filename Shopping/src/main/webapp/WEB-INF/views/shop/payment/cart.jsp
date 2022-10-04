<%@page import="com.academy.shopping.model.util.CurrencyFormatter"%>
<%@page import="com.academy.shopping.restcontroller.admin.Cart"%>
<%@page import="com.academy.shopping.model.domain.SubCategory"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<% 
	List<Cart> carList=(List)request.getAttribute("cartList");	
%>
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
<%@ include file="../inc/topbar.jsp" %>
 <!-- Breadcrumb Begin -->
    <div class="breadcrumb-option">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb__links">
                        <a href="/shop"><i class="fa fa-home"></i> Home</a>
                        <span>Shop</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb End -->
    <!-- Shop Cart Section Begin -->
    <section class="shop-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shop__cart__table">
                        <table>
                            <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                    <th>Total</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            	<form id="cart-form">
                            	<%int total=0; %>
	                                	<%for(int i=0; i<carList.size(); i++) { %>
	                                	<%Cart cart=carList.get(i); %>
	                                	<input type="hidden" name="product_id" value="<%=cart.getProduct_id() %>">
	                                	<tr>
	                                    <td class="cart__product__item">
	                                        <img src="/static/data/<%=cart.getProduct_img() %>" alt="" width="65px">
	                                        <div class="cart__product__item__title">
	                                            <h6><%=cart.getProduct_name() %></h6>
	                                            <div class="rating">
	                                                <i class="fa fa-star"></i>
	                                                <i class="fa fa-star"></i>
	                                                <i class="fa fa-star"></i>
	                                                <i class="fa fa-star"></i>
	                                                <i class="fa fa-star"></i>
	                                            </div>
	                                        </div>
	                                    </td>
	                                    <td class="cart__price"><%=CurrencyFormatter.getCurrency(cart.getDiscount()) %></td>
	                                    <td class="cart__quantity">
	                                        <div class="pro-qty">
	                                            <input type="text" name="quantity" value="<%=cart.getQuantity() %>">
	                                        </div>
	                                    </td>
	                                    <td class="cart__total"><%=CurrencyFormatter.getCurrency( cart.getDiscount()*cart.getQuantity())%></td>
	                                    <td class="cart__close"><span class="icon_close" onClick="delCart(<%=cart.getProduct_id()%>);"></span></td>
	                                </tr>
	                                	<%
	                                		total +=cart.getDiscount()*cart.getQuantity();
	                                	%>
	                                    <%} %>
                        		</form>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn">
                        <a href="#">Continue Shopping</a>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6 col-sm-6">
                    <div class="cart__btn update__btn">
                        <a href="javascript:updateCart()"><span class="icon_loading"></span> Update cart</a>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="discount__content">
                        <h6>Discount codes</h6>
                        <form action="#">
                            <input type="text" placeholder="Enter your coupon code">
                            <button type="submit" class="site-btn">Apply</button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-4 offset-lg-2">
                    <div class="cart__total__procced">
                        <h6>Cart total</h6>
                        <ul>
                        	
                            <li>구매금액 <span>$<%=CurrencyFormatter.getCurrency(total) %> </span></li>
                            <li>최종결제금 <span>$ <%=CurrencyFormatter.getCurrency(total) %></span></li>
                        </ul>
                        <a href="javascript:goCheckout()" class="primary-btn">주문하기</a>
                    </div>
                </div>
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
function updateCart() {
	if(confirm("장바구니를 갱신하시겠어여?")) {
		$("#cart-form").attr({
			action:"/shop/cart/update",
			method:"post"
		});
		$("#cart-form").submit();
	}		
}
function goCheckout() {
	if(confirm("주문하시겠습니까?")) {
		location.href="/shop/checkout";
	}
}

function delCart(product_id) {
	if(confirm("삭제하시겠어요?")) {
		location.href="/shop/cart/delete?product_id="+product_id;
	}		
}
</script>
</body>

</html>