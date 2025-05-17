<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>DuyAnh LT WEB</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Style CSS -->
    <link href="css/style.css" rel="stylesheet">
    <!-- Google Fonts -->
    <link rel="stylesheet"
          href="fonts/fontawesome-free-5.15.4-web/css/all.min.css">
    <link
            href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i"
            rel="stylesheet">
    <!-- FontAwesome CSS -->
    <link href="css/font-awesome.min.css" rel="stylesheet">
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

        .order-stats-wrapper {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            max-width: 1000px;
            margin: 40px auto;
            padding: 30px;
            background-color: #f9f9f9;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }

        .order-stats-wrapper h2 {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 30px;
            font-size: 28px;
        }

        .order-labels {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            gap: 20px;
            margin-bottom: 30px;
        }

        .order-label {
            flex: 1 1 calc(50% - 20px);
            background-color: #ffffff;
            border-left: 5px solid #3498db;
            padding: 15px 20px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
        }

        .order-label h4 {
            margin: 0;
            font-size: 16px;
            color: #555;
        }

        .order-label p {
            font-size: 20px;
            color: #2c3e50;
            margin: 8px 0 0 0;
            font-weight: bold;
        }

        .order-table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        }

        .order-table th,
        .order-table td {
            padding: 12px 16px;
            text-align: left;
            border-bottom: 1px solid #eee;
        }

        .order-table th {
            background-color: #3498db;
            color: white;
            font-weight: normal;
        }

        .order-table tr:hover {
            background-color: #f2f2f2;
        }

        .action-button {
            background-color: #2ecc71;
            border: none;
            color: white;
            padding: 8px 14px;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .action-button:hover {
            background-color: #27ae60;
        }


        /*.modal-overlay {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: rgba(0, 0, 0, 0.4);
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 999;
        }

        .modal-content {
            background-color: white;
            width: 80%;
            max-height: 80%;
            padding: 20px;
            border-radius: 8px;
            position: relative;
        }

        .scroll-container {
            max-height: 400px;
            overflow-y: auto;
            border: 1px solid #ccc;
            margin-top: 10px;
        }

        .close-button {
            position: absolute;
            top: 8px;
            right: 15px;
            font-size: 24px;
            cursor: pointer;
            color: red;
        }
*/


        /* Modal bao phủ */
        .lichsu-modal-overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100vw;
            height: 100vh;
            background-color: rgba(0, 0, 0, 0.4);
            z-index: 1000;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        /* Nội dung modal */
        .lichsu-modal-content {
            background-color: white;
            width: 95vw;
            max-height: 90vh;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
            padding: 20px;
            overflow: hidden;
            position: relative;
            display: flex;
            flex-direction: column;
        }

        /* Nút đóng modal */
        .lichsu-close-button {
            position: absolute;
            top: 10px;
            right: 15px;
            font-size: 24px;
            color: #999;
            cursor: pointer;
        }

        /* Vùng cuộn */
        .lichsu-scroll-container {
            overflow-x: auto;
            overflow-y: auto;
            flex-grow: 1;
            border: 1px solid #ddd;
            margin-top: 15px;
            padding: 10px;
        }

        /* Bảng */
        .lichsu-order-table {
            width: 100%;
            border-collapse: collapse;
            min-width: 1100px; /* Đảm bảo có scroll ngang nếu dữ liệu nhiều cột */
        }

        .lichsu-order-table th,
        .lichsu-order-table td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
            white-space: nowrap;
        }

        .lichsu-order-table th {
            background-color: #f7f7f7;
            font-weight: bold;
        }

        /* Nút báo cáo */
        .lichsu-action-button {
            background-color: orange;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 15px;
            margin-top: 15px;
            align-self: flex-end;
        }

        /* Tiêu đề modal */
        .lichsu-modal-content h3 {
            text-align: center;
            margin-bottom: 10px;
        }



        .modal5 {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100vw;
            height: 100vh;
            background: rgba(0, 0, 0, 0.5);
            display: flex;
            align-items: center;
            justify-content: center;
            z-index: 1050; /* Đặt cao hơn navbar */
        }


        .modal5.show {
            visibility: visible;
            opacity: 1;
        }

        /* Nội dung modal */
        .modal-content5 {
            background: white;
            width: 90%;
            max-width: 600px;
            max-height: 80vh;
            overflow-y: auto;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            z-index: 1100; /* Đảm bảo form hiển thị trên cùng */
        }

        .modal-content5 img {
            width: 50px;
            margin-bottom: 20px;
        }

        .modal-content5 h3 {
            margin: 0;
            font-size: 24px;
            color: #28a745;
        }

        .modal-content5 p {
            margin-top: 10px;
            font-size: 16px;
            color: #555;
        }

        .btn-close5 {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #d9534f;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .btn-close5:hover {
            background-color: #c9302c;
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
                                    href="account-login?userID=${sessionScope.khachHang.userID}"
                                    class="title hidden-xs">Hi <c:out
                                    value="${sessionScope.khachHang.userName}"/></a>|
                            </li>
                            <li><a href="dang-xuat"
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
                            <li><a
                                    href="load-product?page=1">Điện
                                thoại</a></li>
                            <li><a href="go-to-blog">Thông tin</a></li>
                            <li><a href="go-to-about">Bài viết</a></li>
                            <li><a
                                    href="go-to-contactus">Liên
                                hệ, hỗ trợ</a></li>
                        </ul>
                    </div>
                </div>
                <!-- /.navigations-->
            </div>
        </div>
    </div>
</div>
<!-- /. header-section-->
<div class="page-header">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="page-breadcrumb">
                    <ol class="breadcrumb">
                        <li><a href="#">Trang chủ</a></li>
                        <li>Quản lí sản phẩm</li>
                    </ol>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- login-form -->

<div class="content">
    <div class="container">
        <div class="box">
            <div class="row-account">
                <div class="left-container">
                    <div class="user-infor">
                        <img src="avatar/${sessionScope.khachHang.imageAvatar}" alt="">
                        <span>${sessionScope.khachHang.getFullName()}</span>
                    </div>
                    <div class="side-bar-content">
                        <ul>
                            <a
                                    href="account-login?userID=${sessionScope.khachHang.userID}">
                                <li
                                        class="slide-bar "><i class="fa fa-edit"></i><span>Thông
											tin tài khoản</span></li>
                            </a>
                            <a href="go-to-don-hang?page=1">
                                <li class="slide-bar active"><i
                                        class="fas fa-money-check"></i><span>Quản lý đơn hàng</span></li>
                            </a>
                            <a href="go-to-phan-hoi?page=1">
                                <li class="slide-bar"><i
                                        class="fas fa-money-check"></i><span>Phản hồi</span></li>
                            </a>
                            <a href="go-to-so-du?userID=${sessionScope.khachHang.userID}">
                                <li
                                        class="slide-bar"><i class="fas fa-money-check"></i><span>Số
											dư</span></li>
                            </a>
                            <a href="go-to-nang-cap?userID=${sessionScope.khachHang.userID}">
                                <li
                                        class="slide-bar"><i class="fas fa-money-check"></i><span>Nâng
											cấp</span></li>
                            </a>
                            <c:if test="${sessionScope.khachHang.role.roleID == 3}">
                                <a
                                        href="go-to-quan-li-san-pham?userID=${sessionScope.khachHang.userID}&page=1">
                                    <li
                                            class="slide-bar"><i class="fas fa-money-check"></i><span>Quản
												lí sản phẩm</span></li>
                                </a>
                                <a
                                        href="go-to-quan-li-don-hang?userID=${sessionScope.khachHang.userID}&page=1">
                                    <li
                                            class="slide-bar"><i class="fas fa-money-check"></i><span>Quản
												lí đơn hàng</span></li>
                                </a>
                            </c:if>
                            <c:if test="${sessionScope.khachHang.role.roleID == 4}">
                                <a
                                        href="go-to-xu-li-don-hang?userID=${sessionScope.khachHang.userID}&page=1">
                                    <li
                                            class="slide-bar"><i class="fas fa-money-check"></i><span>Xử
												lí đơn hàng</span></li>
                                </a>
                            </c:if>
                            <!-- <a href="address-deliver.html"><li class="slide-bar"><i class="fas fa-map-marker-alt"></i><span> Địa chỉ nhận hàng</span></li></a> -->
                            <a
                                    href="http://localhost:8080/MobileWebApp/profile-reset-password?userID=${sessionScope.khachHang.userID}">
                                <li class="slide-bar"><i class="fas fa-lock"></i><span>
											Đổi mật khẩu</span></li>
                            </a>
                        </ul>
                    </div>
                </div>
<%--                <div class="order-stats-wrapper">--%>
<%--                    <h2>Đơn hàng đặt trong tháng</h2>--%>

<%--                    <div class="order-labels">--%>
<%--                        <div class="order-label">--%>
<%--                            <h4>Số tiền trong tháng</h4>--%>
<%--                            <p>${soTien1}₫</p>--%>
<%--                        </div>--%>
<%--                        <div class="order-label">--%>
<%--                            <h4>Số tiền tạm thời</h4>--%>
<%--                            <p>${soTien2}₫</p>--%>
<%--                        </div>--%>
<%--                        <div class="order-label">--%>
<%--                            <h4>Số đơn hàng duyệt</h4>--%>
<%--                            <p>${numOrders}</p>--%>
<%--                        </div>--%>
<%--                        <div class="order-label">--%>
<%--                            <h4>Số đơn hàng duyệt tạm thời</h4>--%>
<%--                            <p>${numOrders2}</p>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <table class="order-table">--%>
<%--                        <thead>--%>
<%--                        <tr>--%>
<%--                            <th>Mã đơn hàng</th>--%>
<%--                            <th>Tên khách hàng</th>--%>
<%--                            <th>Ngày đặt hàng</th>--%>
<%--                            <th>Tổng tiền</th>--%>
<%--                            <th>Tình trạng đơn hàng</th>--%>
<%--                            <th>Hành động</th>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>
<%--                        <tbody>--%>
<%--                        <c:forEach var="item" items="${listOrders}">--%>
<%--                            <tr>--%>
<%--                                <td>${item.orderID}</td>--%>
<%--                                <td>${item.user.userName}</td>--%>
<%--                                <td>${item.ordersDate}</td>--%>
<%--                                <td><fmt:formatNumber value="${item.totalAmount}" type="number" groupingUsed="true"/>--%>
<%--                                    VND--%>
<%--                                </td>--%>
<%--                                <td>${item.status}</td>--%>
<%--                                <td>--%>
<%--                                    <c:choose>--%>
<%--                                        <c:when test="${item.isCheck == 1}">--%>
<%--                                            <button class="action-button" style="background-color: red; color: white;"--%>
<%--                                                    disabled>Đã thống kê--%>
<%--                                            </button>--%>
<%--                                        </c:when>--%>
<%--                                        <c:otherwise>--%>
<%--                                            <a href="thong-Ke-Don-Hang?userID=${sessionScope.khachHang.userID}&orderID=${item.orderID}&page=${currentPage}">--%>
<%--                                                <button type="submit" class="action-button">Thống kê</button>--%>
<%--                                            </a>--%>
<%--                                        </c:otherwise>--%>
<%--                                    </c:choose>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>
<%--                        </tbody>--%>
<%--                        <button type="button" class="action-button" style="background-color: #007bff; color: white;"--%>
<%--                                onclick="xemLichSuThongKe(${sessionScope.khachHang.userID})">--%>
<%--                            Xem lịch sử thống kê--%>
<%--                        </button>--%>
<%--                    </table>--%>
<%--                    <div class="pagination">--%>
<%--                        <a--%>
<%--                                href="go-to-quan-li-san-pham?userID=${sessionScope.khachHang.userID}&page=${currentPage == 1 ? tongSoTrang : currentPage - 1}">Trước</a>--%>

<%--                        <c:forEach var="i" begin="1" end="${tongSoTrang}">--%>
<%--                            <a--%>
<%--                                    href="go-to-quan-li-san-pham?userID=${sessionScope.khachHang.userID}&page=${i}"--%>
<%--                                    class="${i == currentPage ? 'active' : ''}">${i}</a>--%>
<%--                        </c:forEach>--%>

<%--                        <a--%>
<%--                                href="go-to-quan-li-san-pham?userID=${sessionScope.khachHang.userID}&page=${currentPage == tongSoTrang ? 1 : currentPage + 1}">Sau</a>--%>
<%--                    </div>--%>
<%--                </div>--%>


<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>


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
        <%-- <!-- Modal hiển thị lịch sử -->
         <div id="lichSuThongKeModal" class="modal-overlay" style="display: none;">
             <div class="modal-content">
                 <span class="close-button" onclick="dongModal()">×</span>
                 <h3 style="text-align: center;">Lịch sử đơn hàng đã thống kê</h3>
                 <div class="scroll-container">
                     <table class="order-table">
                         <thead>
                         <tr>
                             <th>Mã đơn hàng</th>
                             <th>Tên khách hàng</th>
                             <th>Ngày đặt</th>
                             <th>Tổng tiền</th>
                             <th>Trạng thái</th>
                             <th>isCheck</th>
                             <th>isSendAdmin</th>
                             <th>idNV</th>
                             <th>Ngày thống kê</th>
                         </tr>
                         </thead>
                         <tbody id="lichSuBody">
                         <!-- Dữ liệu sẽ được thêm ở đây bằng JavaScript -->
                         </tbody>
                     </table>
                 </div>
                 <div style="margin-top: 15px; text-align: right;">
                     <button class="action-button" style="background-color: orange; color: white;"
                             onclick="baoCaoAdmin()">Báo cáo Admin
                     </button>
                 </div>
             </div>
         </div>--%>
        <div id="lichSuThongKeModal" class="lichsu-modal-overlay" style="display: none;">
            <div class="lichsu-modal-content">
                <span class="lichsu-close-button" onclick="dongModal()">×</span>
                <h3>Lịch sử đơn hàng đã thống kê</h3>
                <div class="lichsu-scroll-container">
                    <table class="lichsu-order-table">
                        <thead>
                        <tr>
                            <th>Mã đơn hàng</th>
                            <th>Tên khách hàng</th>
                            <th>Ngày đặt</th>
                            <th>Tổng tiền</th>
                            <th>Trạng thái</th>
                            <th>isCheck</th>
                            <th>isSendAdmin</th>
                            <th>idNV</th>
                            <th>Ngày thống kê</th>
                        </tr>
                        </thead>
                        <tbody id="lichSuBody">
                        <!-- Dữ liệu sẽ được thêm ở đây bằng JavaScript -->
                        </tbody>
                    </table>
                </div>
                <a href="bao-cao-cho-admin?userID=${sessionScope.khachHang.userID}&page=${currentPage}"><button class="lichsu-action-button">Báo cáo Admin</button></a>
            </div>
        </div>
        <c:if test="${check == true}">
        <div class="modal5" id="successModal5">
            <div class="modal-content5">
                <img
                        src="https://tse1.mm.bing.net/th?id=OIP.jZnEX7kzfh_5H-lln_XraAHaDt&pid=Api&P=0&h=180"
                        alt="Notify Icon" style="width: 100px; height: 50px" />
                <h3>${thongBao}</h3>
                <button class="btn-close5" onclick="closeModal()" name="action"
                        value="xacThuc">Đóng</button>
            </div>
        </div>
        </c:if>

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
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery.min.js" type="text/javascript"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/menumaker.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery.sticky.js"></script>
    <script type="text/javascript" src="js/sticky-header.js"></script>
    <script>
        function xemLichSuThongKe(userID) {
            fetch(`xemLichSuThongKe?userID=` + userID)
                .then(response => response.json())
                .then(data => {
                    console.log("Dữ liệu nhận được từ Servlet:");
                    console.log(data);
                    const tbody = document.getElementById('lichSuBody');
                    tbody.innerHTML = ""; // Clear cũ
                    data.forEach(order => {
                        const row = document.createElement('tr');
                        var oi = order.orderID;
                        var name = order.userName;
                        var date = order.orderDate;
                        var price = order.totalAmount;
                        var status = order.status;
                        var isCheck = order.isCheck;
                        var isSend = order.isSendAdmin;
                        var idNV = order.idNV;
                        var dateTK = order.dateTK;
                        row.innerHTML =
                            "<td>" + oi + "</td>" +
                            "<td>" + name + "</td>" +
                            "<td>" + date + "</td>" +
                            "<td>" + price + " VND</td>" +
                            "<td>" + status + "</td>" +
                            "<td>" + isCheck + "</td>" +
                            "<td>" + isSend + "</td>" +
                            "<td>" + idNV + "</td>" +
                            "<td>" + dateTK + "</td>";
                        tbody.appendChild(row);
                    });

                    // Hiển thị modal
                    document.getElementById('lichSuThongKeModal').style.display = "block";
                })
                .catch(err => {
                    alert("Lỗi khi tải lịch sử thống kê");
                    console.error(err);
                });
        }
        function closeModal() {
            document.getElementById("successModal5").style.display = "none";

        }

        function dongModal() {
            document.getElementById('lichSuThongKeModal').style.display = "none";
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
</body>
</html>

