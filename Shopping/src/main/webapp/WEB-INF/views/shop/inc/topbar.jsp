<%@page import="com.academy.shopping.model.domain.TopCategory"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<TopCategory> topCategoryList = (List)request.getAttribute("topCategoryList");
%>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>

    <!-- Offcanvas Menu Begin -->
    <div class="offcanvas-menu-overlay"></div>
    <div class="offcanvas-menu-wrapper">
        <div class="offcanvas__close">+</div>
        <ul class="offcanvas__widget">
            <li><span class="icon_search search-switch"></span></li>
            <li><a href="#"><span class="icon_heart_alt"></span>
                <div class="tip">2</div>
            </a></li>
            <li><a href="/shop/cart/list"><span class="icon_bag_alt"></span>
                <div class="tip">2</div>
            </a></li>
        </ul>
        <div class="offcanvas__logo">
            <a href="/shop"><img src="/static/shop/img/logo.png" alt=""></a>
        </div>
        <div id="mobile-menu-wrap"></div>
        <div class="offcanvas__auth">
             <a href="/shop/member/loginform">Login</a>
            <a href="/shop/member/registform">Register</a>
        </div>
    </div>
    <!-- Offcanvas Menu End -->

    <!-- Header Section Begin -->
    <header class="header">
        <div class="container-fluid">
            <div class="row">
                <div class="col-xl-3 col-lg-2">
                    <div class="header__logo">
                        <a href="/shop"><img src="/static/shop/img/logo.png" alt=""></a>
                    </div>
                </div>
                <div class="col-xl-6 col-lg-7">
                    <nav class="header__menu">
                        <ul>
                            <!-- <li class="active"><a href="./index.html">Home</a></li> -->
                            <%for(int i=0; i<topCategoryList.size(); i++) { %>
                            <%TopCategory topCategory= topCategoryList.get(i); %>
                            <li><a href="/shop/product?topcategory_id=<%=topCategory.getTopcategory_id()%>"><%=topCategory.getCategory_name() %></a></li>
                            <%} %>
                            <li><a href="/shop/product">Shop</a></li>
                            <li><a href="./blog.html">Blog</a></li>
                            <li><a href="./contact.html">Contact</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <div class="header__right">
                        <div class="header__right__auth">
                        	<%if(session.getAttribute("member")==null) { // 로그인 안한경우%>
                        	<a href="/shop/member/loginform">Login</a>
           					 <a href="/shop/member/registform">Register</a>
                        	<%} else { // 로그인 한 경우 %>
                            <a href="javascript:logout()">Logout</a>
           					 <%} %>
                        </div>
                        <ul class="header__right__widget">
                            <li><span class="icon_search search-switch"></span></li>
                            <li><a href="#"><span class="icon_heart_alt"></span>
                                <div class="tip">2</div>
                            </a></li>
                            <li><a href="/shop/cart/list"><span class="icon_bag_alt"></span>
                                <div class="tip">2</div>
                            </a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="canvas__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    <!-- Header Section End -->
    <script>
    	function logout() {
    		if(confirm("로그아웃 하시겠습니까?")) {
    			location.href="/shop/member/logout";
    		}
    	}
    </script>