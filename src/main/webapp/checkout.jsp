<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>Duy Anh LT WEB</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Style CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link
            href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i"
            rel="stylesheet">
    <!-- FontAwesome CSS -->
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/jquery-ui.css" type="text/css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
        .search-bg {
            position: relative; /* Giữ vị trí tương đối cho gợi ý */
        }

        /* Danh sách gợi ý */
        .suggestions-list {
            position: absolute; /* Định vị độc lập */
            top: 100%; /* Hiển thị ngay bên dưới thanh input */
            left: 0;
            right: 0;
            background-color: white; /* Nền trắng cho danh sách */
            z-index: 10000000000000; /* Đảm bảo nằm trên navigation */
            border: 1px solid #ddd;
            border-radius: 4px;
            max-height: 300px; /* Giới hạn chiều cao */
            overflow-y: auto; /* Thêm scroll nếu danh sách quá dài */
            display: none; /* Mặc định ẩn */
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            list-style: none;
            padding: 0;
            margin: 0;
        }

        /* Hiển thị danh sách gợi ý */
        .suggestions-list.active {
            display: block;
        }

        /* Các mục trong danh sách gợi ý */
        .suggestions-list li {
            padding: 10px;
            cursor: pointer;
            border-bottom: 1px solid #f1f1f1;
        }

        .suggestions-list li:hover {
            background-color: #f9f9f9;
        }

        /*  CSS FORM  */
        .modal {
            display: flex; /* Ẩn modal ban đầu */
            position: fixed;
            z-index: 1000;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
        }

        .modal-content {
            background-color: #fff;
            padding: 30px;
            border-radius: 8px;
            text-align: center;
            position: relative;
            width: 90%;
            max-width: 400px;
        }

        .modal-content img {
            width: 50px;
            margin-bottom: 20px;
        }

        .modal-content h3 {
            margin: 0;
            font-size: 24px;
            color: #28a745;
        }

        .modal-content p {
            margin-top: 10px;
            font-size: 16px;
            color: #555;
        }

        .btn-close {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #d9534f;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .btn-close:hover {
            background-color: #c9302c;
            /*CSS form xác nhận thành công */
        }

        #navigation li:hover > ul > li {
            height: 50px;
            border: 1px solid white;
        }

        #navigation ul li.has-sub ul li a {
            color: #fff; /* Màu chữ trắng để nổi trên nền xanh */
            text-decoration: none; /* Bỏ gạch chân */
            display: block;
            font-size: 14px; /* Kích thước chữ */
        }

        .vnpay-button {
            background-color: #d7302f; /* màu đỏ đặc trưng VNPAY */
            color: white;
            border: none;
            padding: 12px 20px;
            font-size: 16px;
            font-weight: bold;
            border-radius: 5px;
            cursor: pointer;
            display: flex;
            align-items: center;
            gap: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s;
            margin-top: 30px;
        }

        .vnpay-button:hover {
            background-color: #b22222; /* màu đậm hơn khi hover */
        }

        .vnpay-logo {
            width: 24px;
            height: 24px;
        }

    </style>
</head>

<body>
<!-- top-header-->
<div class="top-header">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-7 col-sm-6 hidden-xs">
                <p class="top-text">Flexible Delivery, Fast Delivery.</p>
            </div>
            <div class="col-lg-4 col-md-5 col-sm-6 col-xs-12">
                <ul>
                    <li>+084 123 4567</li>
                    <li>nhom21@laptrinhweb.com</li>
                </ul>
            </div>
        </div>
        <!-- /.top-header-->
    </div>
</div>
<!-- header-section-->
<div class="header-wrapper">
    <div class="container">
        <div class="row">
            <!-- logo -->
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-8">
                <div class="logo">
                    <a href="index.html"><img src="images/logo.png" alt="">
                    </a>
                </div>
            </div>
            <!-- /.logo -->
            <!-- search -->
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <form action="SearchOnBox" method="post">
                    <div class="search-bg">
                        <!-- <input type="text"placeholder="Search Here"
                        id="searchBox" class="search-box" autocomplete="off"> -->
                        <input type="text" class="form-control"
                               placeholder="Tìm kiếm sản phẩm..." id="searchBox"
                               class="search-box" autocomplete="off" list="product-suggestions"
                               name="searchOnBox" value="${required}"> <input
                            type="hidden" id="pageNumber" name="page" value="1">
                        <button type="Submit">
                            <i class="fa fa-search"></i>
                        </button>
                        <ul id="product-suggestions" class="suggestions-list">
                            <!-- Gợi ý sẽ được thêm bằng JavaScript -->
                        </ul>
                    </div>
                </form>
            </div>
            <!-- /.search -->
            <!-- account -->
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
                <div class="account-section">
                    <ul>
                        <c:if test="${empty sessionScope.khachHang}">
                            <li><a href="noAccount.jsp" class="title hidden-xs">Tài
                                khoản</a></li>
                            <li class="hidden-xs">|</li>
                            <li><a href="login-form.jsp" class="title hidden-xs">Đăng
                                Nhập</a></li>
                        </c:if>
                        <c:if test="${not empty sessionScope.khachHang}">
                            <li><a
                                    href="http://localhost:8080/MobileWebApp/account-login?userID=${sessionScope.khachHang.userID}"
                                    class="title hidden-xs">Hi <c:out
                                    value="${sessionScope.khachHang.userName}"/></a>|
                            </li>
                            <li><a href="http://localhost:8080/MobileWebApp/dang-xuat"
                                   class="title hidden-xs">Log out </a></li>
                            <li><a
                                    href="load-page-favorite-list?userID=${sessionScope.khachHang.userID}"><i
                                    class="fa fa-heart"></i><sup class="cart-quantity">${soLuongSanPhamLike}</sup></a>
                            </li>
                            <li><a href="go-to-cart" class="title"><i
                                    class="fa fa-shopping-cart"></i><sup class="cart-quantity">${soLuongSP}</sup></a>
                            </li>
                        </c:if>
                    </ul>
                </div>
                <!-- /.account -->
            </div>
            <!-- search -->
        </div>
    </div>
    <!-- navigation -->
    <div class="navigation">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <!-- navigations-->
                    <div id="navigation">
                        <ul>
                            <li class="active"><a href="LoadDataMain">Trang chủ</a></li>
                            <li><a href="http://localhost:8080/MobileWebApp/load-product?page=1">Điện thoại</a></li>
                            <li class="has-sub"><a href="load-accessories">Phụ kiện</a>
                                <ul>
                                    <li><a href="load-accessories?type=op-lung&page=1">Ốp lưng - Bao da</a></li>
                                    <li><a href="load-accessories?type=sac-cap&page=1">Sạc - Cáp</a></li>
                                    <li><a href="load-accessories?type=tai-nghe&page=1">Tai nghe</a></li>
                                    <li><a href="load-accessories?type=pin-sac-du-phong&page=1">Pin sạc dự phòng</a>
                                    </li>
                                    <li><a href="load-accessories?type=dan-man-hinh&page=1">Dán màn hình</a></li>
                                    <li><a href="load-accessories?type=hub-chuyen-doi&page=1">Hub chuyển đổi</a></li>
                                </ul>
                            </li>
                            <li><a href="go-to-blog">Thông tin</a></li>
                            <li><a href="go-to-about">Bài viết</a></li>
                            <li><a href="http://localhost:8080/MobileWebApp/go-to-contactus">Liên hệ, hỗ trợ</a></li>
                        </ul>
                    </div>
                </div>
                <!-- /.navigations-->
            </div>
        </div>
    </div>
</div>

<!-- /.page-header-->
<!-- checkout -->
<div class="content">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-8 col-sm-8 col-xs-12">
                <div class="box checkout-form">
                    <!-- checkout - form -->
                    <div class="box-head">
                        <h2 class="head-title">Thông tin của bạn</h2>
                    </div>
                    <div class="box-body">
                        <div class="row">
                            <form action="xac-nhan-thanh-toan" method="post">
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only" for="name"></label> <input
                                            name="ho" type="text" class="form-control" placeholder="Họ"
                                            required value="${Ho}">
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only" for="name"></label> <input
                                            id="ten" name="name" type="text" class="form-control"
                                            placeholder="Tên" required value="${Ten}">
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only" for="email"></label>
                                        <input id="email" name="email" type="text"
                                               class="form-control" placeholder="Email address" required
                                               value="${Mail}">
                                    </div>
                                </div>
                                <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only" for="phone"></label> <input
                                            id="phone" name="phone" type="text" class="form-control"
                                            placeholder="Phone" required value="${Phone}">
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only"></label> <input
                                            name="diaChi" type="text" class="form-control"
                                            placeholder="Địa chỉ nhận hàng" required value="${diaChi}">
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only" for="textarea"></label>
                                        <textarea class="form-control" id="textarea" name="textarea"
                                                  rows="4" placeholder="Ghi chú">${note}</textarea>
                                    </div>
                                    <button class="btn btn-primary ">Xác nhận thanh toán</button>
                                </div>

<%--                                Tỉnh, thành phố --%>
<%--                                //----ffaay nữa--%>
                            <%--    <label for="provinceSelect">Tỉnh/Thành phố</label>
                                <select id="provinceSelect">
                                    <option value="">-- Chọn tỉnh --</option>
                                </select>

&lt;%&ndash;                                //--- rương tụ lấy tên huyện (document.getByid.text...&ndash;%&gt;
                                <label for="districtSelect">Quận/Huyện</label>
                                <select id="districtSelect">
                                    <option value="">-- Chọn huyện --</option>
                                </select>


&lt;%&ndash;                                //--get text của cái select này lấy tên xã&ndash;%&gt;
                                <label for="wardSelect">Xã/Phường</label>
                                <select id="wardSelect" onchange="updateShippingFee()">
                                    <option value="">-- Chọn xã --</option>
                                </select>

&lt;%&ndash;                                cái này lấy phí vận chuyển&ndash;%&gt;
                                <label>Phí vận chuyển (VNĐ)</label>
                                <input type="text" id="shippingFee" class="readonly" value="0" readonly>
--%>
<%--                                <label>Tiền hàng (VNĐ)</label>--%>
<%--                                <input type="text" id="productPrice" class="readonly" value="100000" readonly>--%>

<%--                                <label>Tổng tiền (VNĐ)</label>--%>
<%--                                <input type="text" id="totalPrice" class="readonly" value="100000" readonly>--%>


                            </form>
                            <%--								<form method="POST" action="${pageContext.request.contextPath}/VNPay/vnpay_pay.jsp">--%>
                            <%--									<input type="hidden" name="amount" value="1000000"/>--%>
                            <%--									<button type="submit">Test VNPAY</button>--%>
                            <%--								</form>--%>

                            <%--								<form method="POST" action="ThanhToanVNPAY">--%>
                            <%--									<input type="hidden" name="amount" value="${totalAmount}"/>--%>
                            <%--									<button type="submit">Test VNPAY</button>--%>
                            <%--								</form>--%>
                            <form method="POST" action="ThanhToanVNPAY">
                                Tỉnh, thành phố --%>
                                <%--                                //----ffaay nữa--%>
                                <label for="provinceSelect">Tỉnh/Thành phố</label>
                                <select id="provinceSelect" name = "province">
                                    <option value="">-- Chọn tỉnh --</option>
                                </select>

                                <%--                                //--- rương tụ lấy tên huyện (document.getByid.text...--%>
                                <label for="districtSelect">Quận/Huyện</label>
                                <select id="districtSelect" name = "dictrict">
                                    <option value="">-- Chọn huyện --</option>
                                </select>


                                <%--                                //--get text của cái select này lấy tên xã--%>
                                <label for="wardSelect">Xã/Phường</label>
                                <select id="wardSelect" onchange="updateShippingFee()" name="commue">
                                    <option value="">-- Chọn xã --</option>
                                </select>

                                <%--                                cái này lấy phí vận chuyển--%>
                                <label>Phí vận chuyển (VNĐ)</label>
                                <input type="text" id="shippingFee" class="readonly" value="0" readonly name="shipfee">

                                <input type="hidden" name="amount" value="${totalAmount}"/>
                                <input type="hidden" name="orderID" value="${orderID}"/>
                                <button type="submit" class="vnpay-button">
                                    <img src="images/vnpay_logo.png" alt="VNPAY" class="vnpay-logo"/>
                                    THANH TOÁN BẰNG VNPAY
                                </button>
                            </form>


                            <div class="checkbox alignright mt20">
                                <label> <a href="go-to-cart"><span>Quay Lại?</span></a>
                                </label>
                            </div>
                            <!-- /.checkout-form -->
                        </div>
                    </div>
                </div>
            </div>
            <!-- product total -->
            <div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
                <div class="box mb30">
                    <div class="box-head">
                        <h3 class="head-title">Đơn hàng của bạn</h3>
                    </div>
                    <div class="box-body">
                        <div class="table-responsive">
                            <!-- product total -->

                            <div class="pay-amount ">
                                <table class="table mb20">
                                    <thead class=""
                                           style="border-bottom: 1px solid #e8ecf0; text-transform: uppercase;">
                                    <tr>
                                        <th><span>Sản phẩm</span></th>
                                        <th><span>Giá tiền</span></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="orderDetails"
                                               items="${sessionScope.listItem.getList()}">
                                        <tr>
                                            <th><span>${orderDetails.product.name}</span></th>
                                            <td><fmt:formatNumber
                                                    value="${orderDetails.unitPrice * orderDetails.quantity}"
                                                    type="currency"/></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                    <tbody>
                                    <tr>
                                        <th><span>Tổng thanh toán</span></th>
                                        <td><fmt:formatNumber value="${totalAmount}"
                                                              type="currency"/></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.product total -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- footer -->
<div class="footer">
    <div class="container">
        <div class="row">
            <!-- footer-company-links -->
            <!-- footer-contact links -->
            <div class=" col-lg-3 col-md-3 col-sm-3 col-xs-12">
                <div class="footer-widget">
                    <h3 class="footer-title">Thông tin hỗ trợ</h3>
                    <div class="contact-info">
                        <span class="contact-icon"><i class="fa fa-map-marker"></i></span>
                        <span class="contact-text">Phường Linh Trung, Thủ Đức<br>Thành
								phố Hồ Chí Minh, Việt Nam - 1955
							</span>
                    </div>
                    <div class="contact-info">
                        <span class="contact-icon"><i class="fa fa-phone"></i></span> <span
                            class="contact-text">+084-123-4567 / 89</span>
                    </div>
                    <div class="contact-info">
                        <span class="contact-icon"><i class="fa fa-envelope"></i></span>
                        <span class="contact-text">nhom21@ltweb.com</span>
                    </div>
                </div>
            </div>
            <!-- /.footer-useful-links -->
            <div class=" col-lg-3 col-md-3 col-sm-3 col-xs-12">
                <div class="footer-widget">
                    <h3 class="footer-title">Tiện ích</h3>
                    <ul class="arrow">
                        <li><a href="index.html">Home </a></li>
                        <li><a href="product-list.html">Mobie</a></li>
                        <li><a href="about.html">About</a></li>
                        <li><a href="blog-default.html">Blog</a></li>
                        <li><a href="contact-us.html">Contact</a></li>
                    </ul>
                </div>
            </div>
            <!-- /.footer-useful-links -->
            <!-- footer-policy-list-links -->
            <div class=" col-lg-3 col-md-3 col-sm-3 col-xs-12">
                <div class="footer-widget">
                    <h3 class="footer-title">Chính sách</h3>
                    <ul class="arrow">
                        <li><a href="#">Thanh toán</a></li>
                        <li><a href="#">Hủy, trả hàng</a></li>
                        <li><a href="#">Giao hàng và vận chuyển</a></li>
                        <li><a href="#">Chính sách bảo mật</a></li>
                    </ul>
                </div>
            </div>
            <!-- /.footer-policy-list-links -->
            <!-- footer-social links -->
            <div class=" col-lg-3 col-md-3 col-sm-3 col-xs-12">
                <div class="footer-widget">
                    <h3 class="footer-title">Liên lạc với chúng tôi</h3>
                    <div class="ft-social">
							<span><a href="#" class="btn-social btn-facebook"><i
                                    class="fa fa-facebook"></i></a></span> <span><a href="#"
                                                                                    class="btn-social btn-twitter"><i
                            class="fa fa-twitter"></i></a></span>
                        <span><a href="#" class="btn-social btn-googleplus"><i
                                class="fa fa-google-plus"></i></a></span> <span><a href="#"
                                                                                   class=" btn-social btn-linkedin"><i
                            class="fa fa-linkedin"></i></a></span>
                        <span><a href="#" class=" btn-social btn-pinterest"><i
                                class="fa fa-pinterest-p"></i></a></span> <span><a href="#"
                                                                                   class=" btn-social btn-instagram"><i
                            class="fa fa-instagram"></i></a></span>
                    </div>
                </div>
            </div>
            <!-- /.footer-social links -->
        </div>
    </div>
    <!-- tiny-footer -->
    <div class="tiny-footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                    <div class="payment-method alignleft">
                        <ul>
                            <li><a href="#"><i class="fa fa-cc-paypal fa-2x"></i></a></li>
                            <li><a href="#"><i class="fa fa-cc-mastercard  fa-2x"></i></a></li>
                            <li><a href="#"><i class="fa fa-cc-visa fa-2x"></i></a></li>
                            <li><a href="#"><i class="fa fa-cc-discover fa-2x"></i></a></li>
                        </ul>
                    </div>
                    <p class="alignright">
                        Copyright © All Rights Reserved 2020 Template Design by <a
                            href="https://easetemplate.com/" target="_blank"
                            class="copyrightlink">Nhom 21</a>
                    </p>
                </div>
            </div>
        </div>
        <!-- /. tiny-footer -->
    </div>
</div>
<c:if test="${kiemTra == false}">
    <div class="modal" id="successModal">
        <div class="modal-content">
            <img
                    src="https://tse1.mm.bing.net/th?id=OIP.jZnEX7kzfh_5H-lln_XraAHaDt&pid=Api&P=0&h=180"
                    alt="Notify Icon" style="width: 100px; height: 50px"/>
            <h3>${notify}</h3>
            <button class="btn-close" onclick="closeModal()" name="action"
                    value="xacThuc">Đóng
            </button>
        </div>
    </div>
</c:if>
<c:if test="${kiemTra == true}">
    <div class="modal" id="successModal">
        <div class="modal-content">
            <img
                    src="https://tse1.mm.bing.net/th?id=OIP.jZnEX7kzfh_5H-lln_XraAHaDt&pid=Api&P=0&h=180"
                    alt="Notify Icon" style="width: 100px; height: 50px"/>
            <h3>${notify}</h3>
            <button class="btn-close" onclick="closeModal()" name="action"
                    value="xacThuc">Đóng
            </button>
            <a href="go-to-don-hang?page=1">
                <button class="btn-close">Quản lý đơn hàng</button>
            </a>
        </div>
    </div>
</c:if>
<!-- /.footer -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js" type="text/javascript"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/menumaker.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.sticky.js"></script>
<script type="text/javascript" src="js/sticky-header.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript">
    function closeModal() {
        document.getElementById("successModal").style.display = "none";

    }

</script>
<script type="text/javascript">
    const searchBox = document.getElementById("searchBox");
    const suggestionsList = document.getElementById("product-suggestions");

    // Xử lý khi người dùng nhập từ khóa
    searchBox.addEventListener("input", function () {
        const keyword = this.value.trim();

        // Nếu từ khóa có ít nhất 1 ký tự
        if (keyword.length > 1) {
            console.log(keyword);
            console.log(`URL Fetch: SearchServlet?ans=` + keyword);
            fetch(`SearchServlet?ans=` + keyword)
                .then(response => response.json())
                .then(data => {
                    // Xóa các gợi ý cũ
                    suggestionsList.innerHTML = "";

                    // Thêm các gợi ý mới
                    data.forEach(product => {
                        const suggestionItem = document.createElement("li");
                        suggestionItem.textContent = product.name;
                        suggestionItem.addEventListener("click", function () {
                            searchBox.value = product.name; // Gán sản phẩm được chọn vào thanh tìm kiếm
                            suggestionsList.innerHTML = ""; // Xóa gợi ý
                            suggestionsList.classList.remove("active"); // Ẩn danh sách
                        });
                        suggestionsList.appendChild(suggestionItem);
                    });

                    // Hiển thị danh sách gợi ý
                    suggestionsList.classList.add("active");
                });

        } else {
            // Ẩn danh sách nếu không có từ khóa
            suggestionsList.innerHTML = "";
            suggestionsList.classList.remove("active");
        }
    });

    // Ẩn danh sách khi nhấp ra ngoài
    document.addEventListener("click", function (e) {
        if (!searchBox.contains(e.target) && !suggestionsList.contains(e.target)) {
            suggestionsList.innerHTML = "";
            suggestionsList.classList.remove("active");
        }
    });

</script>

<script>
    const token = "";
    const shopId = "";

    //--2 cái này là id huyện và id xã (nơi gửi)
    const fromDistrictId = 1442;
    const fromWardCode = "21211";

    //---này là loại hình (hàng nhẹ)
    const serviceTypeId = 2;

    //---api lấy các tỉnh (kèm id)
    document.addEventListener("DOMContentLoaded", function () {
        // Load tỉnh
        fetch("https://online-gateway.ghn.vn/shiip/public-api/master-data/province", {
            method: "GET",
            headers: {
                "Token": token
            }
        })
            .then(res => res.json())
            .then(data => {
                const provinces = data.data.slice(4); // bỏ 4 tỉnh test
                const select = document.getElementById("provinceSelect");

                provinces.forEach(p => {
                    const option = document.createElement("option");
                    option.value = p.ProvinceID;
                    option.textContent = p.ProvinceName;
                    select.appendChild(option);
                });
            });

        // Khi chọn tỉnh -> load huyện
        //---api get huyện
        document.getElementById("provinceSelect").addEventListener("change", function () {
            document.getElementById("wardSelect").selectedIndex = 0; // reset chọn xã
            updateShippingFee();

            const provinceId = parseInt(this.value);
            const districtSelect = document.getElementById("districtSelect");
            districtSelect.innerHTML = '<option value="">-- Chọn huyện --</option>';
            document.getElementById("wardSelect").innerHTML = '<option value="">-- Chọn xã --</option>';

            if (!provinceId) return;

            fetch("https://online-gateway.ghn.vn/shiip/public-api/master-data/district", {
                method: "POST",
                headers: {
                    "Token": token,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ province_id: provinceId })
            })
                .then(res => res.json())
                .then(data => {
                    const districts = data.data;
                    districts.forEach(d => {
                        const option = document.createElement("option");
                        option.value = d.DistrictID;
                        option.textContent = d.DistrictName;
                        districtSelect.appendChild(option);
                    });
                });
        });

        //---api xã
        // lấy được huyện và xã thì có id huyện id xã, đ làm body cho api get giá tiền
        // Khi chọn huyện -> load xã
        document.getElementById("districtSelect").addEventListener("change", function () {
            document.getElementById("wardSelect").selectedIndex = 0; // reset chọn xã
            updateShippingFee();

            const districtId = parseInt(this.value);
            const wardSelect = document.getElementById("wardSelect");
            wardSelect.innerHTML = '<option value="">-- Chọn xã --</option>';

            if (!districtId) return;

            fetch("https://online-gateway.ghn.vn/shiip/public-api/master-data/ward", {
                method: "POST",
                headers: {
                    "Token": token,
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ district_id: districtId })
            })
                .then(res => res.json())
                .then(data => {
                    const wards = data.data;
                    wards.forEach(w => {
                        const option = document.createElement("option");
                        option.value = w.WardCode;
                        option.textContent = w.WardName;
                        wardSelect.appendChild(option);
                    });
                });
        });
    });

    //=======api tình phí
    //----đây là api giá tiền
    function updateShippingFee() {
        const districtId = parseInt(document.getElementById("districtSelect").value);
        const wardCode = document.getElementById("wardSelect").value;
        // const productPrice = parseInt(document.getElementById("productPrice").value) || 0;

        // Nếu chưa chọn xã => không tính phí
        if (!districtId || !wardCode) {
            document.getElementById("shippingFee").value = 0;
            // document.getElementById("totalPrice").value = productPrice;
            return;
        }

        console.log("huyện = ", districtId);
        console.log("xã = ", wardCode);


        // Call API tính phí
        fetch("https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee", {
            method: "POST",
            headers: {
                "Token": token,
                "ShopId": shopId,
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                service_type_id: serviceTypeId,
                from_district_id: fromDistrictId,
                from_ward_code: fromWardCode,
                to_district_id: districtId,
                to_ward_code: wardCode,
                //---fix cứng cân nặng, dài, rộng của sản phẩm
                length: 20,
                width: 15,
                height: 10,
                weight: 5000,
                insurance_value: 0,
                coupon: null
            })
        })
            .then(res => res.json())
            .then(data => {
                if (data.code === 200 && data.data && data.data.total) {
                    const fee = data.data.total;
                    document.getElementById("shippingFee").value = fee;
                    // document.getElementById("totalPrice").value = productPrice + fee;
                } else {
                    alert("Không tính được phí vận chuyển!");
                    document.getElementById("shippingFee").value = 0;
                    // document.getElementById("totalPrice").value = productPrice;
                }
            })
            .catch(err => {
                console.error("Lỗi khi gọi API GHN:", err);
                document.getElementById("shippingFee").value = 0;
                // document.getElementById("totalPrice").value = productPrice;
            });
    }
    //=========
</script>

</body>


</html>