<%@ page import="entity.CustomerProductEntity" %>
<%@ page import="entity.CustomerEntity" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.png"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/linearicons-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/MagnificPopup/magnific-popup.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css"
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!--===============================================================================================-->
</head>
<body class="animsition">
    <!-- Header -->
    <header class="header-v2">
        <!-- Header desktop -->
        <div class="container-menu-desktop trans-03">
            <div class="wrap-menu-desktop">
                <nav class="limiter-menu-desktop p-l-45">

                    <!-- Logo desktop -->
                    <a href="#" class="logo">
                        <img src="images/icons/logo-01.png" alt="IMG-LOGO">
                    </a>

                    <!-- Menu desktop -->
                    <div class="menu-desktop">
                        <ul class="main-menu">
                            <li class="active-menu">
                                <a href="Home.jsp">Home</a>
                            </li>

                            <li class="label1" data-label1="hot">
                                <a href="productList?">Shop</a>
                            </li>

                            <li>
                                <a href="login?action=CheckCookie">Cart</a>
                            </li>

                            <li>
                                <a href="about.jsp">About</a>
                            </li>

                            <%--							<li>--%>
                            <%--								<a href="contact.jsp">Contact</a>--%>
                            <%--							</li>--%>
                        </ul>
                    </div>

                    <!-- Icon header -->
                    <div class="wrap-icon-header flex-w flex-r-m h-full">

                        <div class="flex-c-m h-full p-l-18 p-r-25 bor5">
                            <div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 icon-header-noti js-show-cart" data-notify="2">
                                <i class="zmdi zmdi-shopping-cart"></i>
                            </div>
                        </div>

                        <div class="flex-c-m h-full p-lr-19">
                            <div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-sidebar">
                                <i class="zmdi zmdi-menu"></i>
                            </div>
                        </div>
                    </div>
                </nav>
            </div>
        </div>

        <!-- Header Mobile -->
        <div class="wrap-header-mobile">
            <!-- Logo moblie -->
            <div class="logo-mobile">
                <a href="Home.jsp"><img src="images/icons/logo-01.png" alt="IMG-LOGO"></a>
            </div>

            <!-- Icon header -->
            <div class="wrap-icon-header flex-w flex-r-m h-full m-r-15">
                <div class="flex-c-m h-full p-r-10">
                    <div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 js-show-modal-search">
                        <i class="zmdi zmdi-search"></i>
                    </div>
                </div>

                <div class="flex-c-m h-full p-lr-10 bor5">
                    <div class="icon-header-item cl2 hov-cl1 trans-04 p-lr-11 icon-header-noti js-show-cart" data-notify="2">
                        <i class="zmdi zmdi-shopping-cart"></i>
                    </div>
                </div>
            </div>

            <!-- Button show menu -->
            <div class="btn-show-menu-mobile hamburger hamburger--squeeze">
                    <span class="hamburger-box">
                        <span class="hamburger-inner"></span>
                    </span>
            </div>
        </div>


        <!-- Menu Mobile -->
        <div class="menu-mobile">
            <ul class="main-menu-m">
                <li>
                    <a href="Home.jsp">Home</a>
                    <!--					<ul class="sub-menu-m">-->
                    <!--						<li><a href="index.html">Homepage 1</a></li>-->
                    <!--						<li><a href="Home.jsp">Homepage 2</a></li>-->
                    <!--						<li><a href="home-03.html">Homepage 3</a></li>-->
                    <!--					</ul>-->
                    <span class="arrow-main-menu-m">
                            <i class="fa fa-angle-right" aria-hidden="true"></i>
                        </span>
                </li>

                <li>
                    <a href="product.jsp" class="label1 rs1" data-label1="hot">Cart</a>
                </li>

                <li>
                    <a href="shoping-cart.jsp">Cart</a>
                </li>

                <li>
                    <a href="about.jsp">About</a>
                </li>

                <%--				<li>--%>
                <%--					<a href="contact.jsp">Contact</a>--%>
                <%--				</li>--%>
            </ul>
        </div>

        <!-- Modal Search -->
        <div class="modal-search-header flex-c-m trans-04 js-hide-modal-search">
            <div class="container-search-header">
                <button class="flex-c-m btn-hide-modal-search trans-04 js-hide-modal-search">
                    <img src="images/icons/icon-close2.png" alt="CLOSE">
                </button>

                <form class="wrap-search-header flex-w p-l-15">
                    <button class="flex-c-m trans-04">
                        <i class="zmdi zmdi-search"></i>
                    </button>
                    <input class="plh3" type="text" name="search" placeholder="Search...">
                </form>
            </div>
        </div>
    </header>

    <!-- Menu -->
    <aside class="wrap-sidebar js-sidebar">
        <div class="s-full js-hide-sidebar"></div>

        <div class="sidebar flex-col-l p-t-22 p-b-25">
            <div class="flex-r w-full p-b-30 p-r-27">
                <div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-sidebar">
                    <i class="zmdi zmdi-close"></i>
                </div>
            </div>

            <div class="sidebar-content flex-w w-full p-lr-65 js-pscroll">
                <ul class="sidebar-link w-full">
                    <li class="p-b-13">
                        <a href="Home.jsp" class="stext-102 cl2 hov-cl1 trans-04">
                            Home
                        </a>
                    </li>

                    <%--					<li class="p-b-13">--%>
                    <%--						<a href="register.jsp" class="stext-102 cl2 hov-cl1 trans-04">--%>
                    <%--							My Wishlist--%>
                    <%--						</a>--%>
                    <%--					</li>--%>

                    <li class="p-b-13">
                        <a href="login?action=CheckUser" class="stext-102 cl2 hov-cl1 trans-04">
                            My Account
                            <c:if test="${isLoggedIn eq true}">
                                <span class="useraccount"> 123@gmail.com </span>
                            </c:if>
                        </a>
                    </li>

                    <%--					<li class="p-b-13">--%>
                    <%--						<a href="shoping-cart.jsp" class="stext-102 cl2 hov-cl1 trans-04">--%>
                    <%--							Track Oder--%>
                    <%--						</a>--%>
                    <%--					</li>--%>

                    <li class="p-b-13">
                        <a href="contact.jsp" class="stext-102 cl2 hov-cl1 trans-04">
                            Help & FAQs
                        </a>
                    </li>
                    <form action="login" method="post">
                        <input type="hidden" name="action" value="logout">
                        <c:if test="${isLoggedIn eq true}">
                            <button class="Btn">
                                <div class="sign"><svg viewBox="0 0 512 512"><path d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"></path></svg></div>
                                <div class="text">Logout</div>
                            </button>
                        </c:if>
                    </form>
                </ul>

                <%--				<div class="sidebar-gallery w-full p-tb-30">--%>
                <%--					<span class="mtext-101 cl5">--%>
                <%--						@ CozaStore--%>
                <%--					</span>--%>

                <%--					<div class="flex-w flex-sb p-t-36 gallery-lb">--%>
                <%--						<!-- item gallery sidebar -->--%>
                <%--						<div class="wrap-item-gallery m-b-10">--%>
                <%--							<a class="item-gallery bg-img1" href="images/gallery-01.jpg" data-lightbox="gallery" --%>
                <%--							style="background-image: url('images/gallery-01.jpg');"></a>--%>
                <%--						</div>--%>

                <%--						<!-- item gallery sidebar -->--%>
                <%--						<div class="wrap-item-gallery m-b-10">--%>
                <%--							<a class="item-gallery bg-img1" href="images/gallery-02.jpg" data-lightbox="gallery" --%>
                <%--							style="background-image: url('images/gallery-02.jpg');"></a>--%>
                <%--						</div>--%>

                <%--						<!-- item gallery sidebar -->--%>
                <%--						<div class="wrap-item-gallery m-b-10">--%>
                <%--							<a class="item-gallery bg-img1" href="images/gallery-03.jpg" data-lightbox="gallery" --%>
                <%--							style="background-image: url('images/gallery-03.jpg');"></a>--%>
                <%--						</div>--%>

                <%--						<!-- item gallery sidebar -->--%>
                <%--						<div class="wrap-item-gallery m-b-10">--%>
                <%--							<a class="item-gallery bg-img1" href="images/gallery-04.jpg" data-lightbox="gallery" --%>
                <%--							style="background-image: url('images/gallery-04.jpg');"></a>--%>
                <%--						</div>--%>

                <%--						<!-- item gallery sidebar -->--%>
                <%--						<div class="wrap-item-gallery m-b-10">--%>
                <%--							<a class="item-gallery bg-img1" href="images/gallery-05.jpg" data-lightbox="gallery" --%>
                <%--							style="background-image: url('images/gallery-05.jpg');"></a>--%>
                <%--						</div>--%>

                <%--						<!-- item gallery sidebar -->--%>
                <%--						<div class="wrap-item-gallery m-b-10">--%>
                <%--							<a class="item-gallery bg-img1" href="images/gallery-06.jpg" data-lightbox="gallery" --%>
                <%--							style="background-image: url('images/gallery-06.jpg');"></a>--%>
                <%--						</div>--%>

                <%--						<!-- item gallery sidebar -->--%>
                <%--						<div class="wrap-item-gallery m-b-10">--%>
                <%--							<a class="item-gallery bg-img1" href="images/gallery-07.jpg" data-lightbox="gallery" --%>
                <%--							style="background-image: url('images/gallery-07.jpg');"></a>--%>
                <%--						</div>--%>

                <%--						<!-- item gallery sidebar -->--%>
                <%--						<div class="wrap-item-gallery m-b-10">--%>
                <%--							<a class="item-gallery bg-img1" href="images/gallery-08.jpg" data-lightbox="gallery" --%>
                <%--							style="background-image: url('images/gallery-08.jpg');"></a>--%>
                <%--						</div>--%>

                <%--						<!-- item gallery sidebar -->--%>
                <%--						<div class="wrap-item-gallery m-b-10">--%>
                <%--							<a class="item-gallery bg-img1" href="images/gallery-09.jpg" data-lightbox="gallery" --%>
                <%--							style="background-image: url('images/gallery-09.jpg');"></a>--%>
                <%--						</div>--%>
                <%--					</div>--%>
                <%--				</div>--%>
            </div>
        </div>
    </aside>

    <!-- Cart -->
    <div class="wrap-header-cart js-panel-cart">
        <div class="s-full js-hide-cart"></div>

        <div class="header-cart flex-col-l p-l-65 p-r-25">
            <div class="header-cart-title flex-w flex-sb-m p-b-8">
				<span class="mtext-103 cl2">
					Your Cart
				</span>

                <div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart">
                    <i class="zmdi zmdi-close"></i>
                </div>
            </div>

            <div class="header-cart-content flex-w js-pscroll">
                <ul class="header-cart-wrapitem w-full">

<%--                    <%CustomerEntity user = (CustomerEntity) session.getAttribute("user");%>--%>
<%--                        <% List<CustomerProductEntity> customerCart = (List<CustomerProductEntity>) session.getAttribute("cart"); %>--%>
<%--                        <c:if test="${customerCart != null}">--%>
<%--                            <% for (CustomerProductEntity product : customerCart) { %>--%>
                                <li class="header-cart-item flex-w flex-t m-b-12">
                                    <div class="header-cart-item-img">
<%--                                        <% String itemCartVariable = "images/item-cart-01" + /*product.getProductId() +*/ ".jpg"; %>--%>
                                        <img src="images/item-cart-01" alt="IMG">
                                    </div>

                                    <div class="header-cart-item-txt p-t-8">
                                        <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
                                            White Shirt Pleat

                                        </a>

                                        <span class="header-cart-item-info">
                                        1 x $19.00
                                    </span>
                                    </div>
                                </li>

<%--                            <% } %>--%>
<%--                        </c:if>--%>
<%--
                    <li class="header-cart-item flex-w flex-t m-b-12">
                        <div class="header-cart-item-img">
                            <img src="images/item-cart-02.jpg" alt="IMG">
                        </div>

                        <div class="header-cart-item-txt p-t-8">
                            <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
                                Converse All Star
                            </a>

                            <span class="header-cart-item-info">
								1 x $39.00
							</span>
                        </div>
                    </li>

                    <li class="header-cart-item flex-w flex-t m-b-12">
                        <div class="header-cart-item-img">
                            <img src="images/item-cart-03.jpg" alt="IMG">
                        </div>

                        <div class="header-cart-item-txt p-t-8">
                            <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">
                                Nixon Porter Leather
                            </a>

                            <span class="header-cart-item-info">
								1 x $17.00
							</span>
                        </div>
                    </li>
                </ul>--%>

                <div class="w-full">
                    <div class="header-cart-total w-full p-tb-40">
                        Total: $75.00
                    </div>

                    <div class="header-cart-buttons flex-w w-full">
                        <a href="cart?" class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-r-8 m-b-10">
                            View Cart
                        </a>

                        <a href="shoping-cart.jsp" class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10">
                            Check Out
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

