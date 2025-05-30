<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="keywords" content="">
    <title>Duy LTW</title>
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
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
          integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <style>
        /* CSS cho modal */
        .modal {
            display: none;
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
        }

        .close-btn {
            position: absolute;
            top: 10px;
            right: 15px;
            font-size: 24px;
            cursor: pointer;
        }

        .close-btn:hover {
            color: #d9534f;
        }

        .search-bg {
            position: relative;
        }

        /* Danh sách gợi ý */
        .suggestions-list {
            position: absolute;
            top: 100%;
            left: 0;
            right: 0;
            background-color: white;
            z-index: 1000;
            border: 1px solid #ddd;
            border-radius: 4px;
            max-height: 300px;
            overflow-y: auto;
            display: none;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .suggestions-list.active {
            display: block;
        }

        .suggestions-list li {
            padding: 10px;
            cursor: pointer;
            border-bottom: 1px solid #f1f1f1;
        }

        .suggestions-list li:hover {
            background-color: #f9f9f9;
        }

        #navigation li:hover > ul > li {
            height: 50px;
            border: 1px solid white;
        }

        #navigation ul li.has-sub ul li a {
            color: #fff;
            text-decoration: none;
            display: block;
            font-size: 14px;
        }

        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 5px;
        }

        .alert-warning {
            color: #856404;
            background-color: #fff3cd;
            border-color: #ffeeba;
            padding: 10px;
            border-radius: 4px;
            margin-bottom: 15px;
        }

        .captcha-box {
            font-family: monospace;
            letter-spacing: 3px;
            min-width: 100px;
            background-color: #f8f9fa;
            padding: 5px 10px;
            border-radius: 4px;
            font-weight: bold;
        }
    </style>
</head>

<body>
<!-- header-section-->
<div class="top-header">
    <div class="container">
        <div class="row">
            <div class="col-lg-8 col-md-7 col-sm-6 hidden-xs">
                <p class="top-text">Flexible Delivery, Fast Delivery.</p>
            </div>
            <div class="col-lg-4 col-md-5 col-sm-6 col-xs-12">
                <ul>
                    <li>+03434 634 29</li>
                    <li>laptrinhweb@mail.com</li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="header-wrapper">
    <div class="container">
        <div class="row">
            <!-- logo -->
            <div class="col-lg-3 col-md-3 col-sm-3 col-xs-8">
                <div class="logo">
                    <a href="index.html"><img src="images/logo.png" alt=""> </a>
                </div>
            </div>
            <!-- /.logo -->
            <!-- search -->
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                <form action="SearchOnBox" method="post">
                    <div class="search-bg">
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
                            <li><a href="noAccount.jsp" class="title hidden-xs">Tài khoản</a></li>
                            <li class="hidden-xs">|</li>
                            <li><a href="login-form.jsp" class="title hidden-xs">Đăng Nhập</a></li>
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
                                    class="fa fa-shopping-cart"></i><sup class="cart-quantity">1</sup></a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <div class="navigation">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
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
            </div>
        </div>
    </div>
</div>

<div class="page-header">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                <div class="page-breadcrumb">
                    <ol class="breadcrumb">
                        <li><a href="#">Trang chủ</a></li>
                        <li>Đăng ký</li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="content">
    <div class="container">
        <div class="box sing-form">
            <div class="row">
                <div class="col-lg-offset-1 col-lg-5 col-md-offset-1 col-md-5 col-sm-12 col-xs-12 ">
                    <!-- form -->
                    <div class="box-body">
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-6 col-xs-12 mb20">
                                <h3 class="mb10">Tạo tài khoản</h3>
                                <p>Vui lòng điền đầy đủ các thông tin bên dưới</p>
                            </div>
                            <%
                                String sourceServlet = request.getAttribute("sourceServlet") + "";
                                sourceServlet = sourceServlet.equals("null") ? "" : sourceServlet;
                                String baoLoi = "";
                                String tenDangNhap = "";
                                String hoVaTen = "";
                                String email = "";
                                String phone = "";
                                String dateOfBirth = "";
                                String addRess = "";
                                String sex = "";
                                boolean kiemTra = false;
                                boolean xacThuc = false;
                                boolean mo = false;
                                boolean dong = false;
                                boolean xacThuc2 = false;
                                boolean xacThuc3 = false;
                                String message = "";
                                if (sourceServlet.equals("signUpController")) {
                                    String trangThaiDangKy = request.getAttribute("kiemTra") + "";
                                    if (trangThaiDangKy.equals("true")) {
                                        kiemTra = true;
                                        out.println(kiemTra);
                                        tenDangNhap = request.getAttribute("username") + "";
                                        tenDangNhap = tenDangNhap.equals("null") ? "" : tenDangNhap;
                                    } else {
                                        baoLoi = request.getAttribute("baoLoi") + "";
                                        baoLoi = baoLoi.equals("null") ? "" : baoLoi;

                                        tenDangNhap = request.getAttribute("username") + "";
                                        tenDangNhap = tenDangNhap.equals("null") ? "" : tenDangNhap;

                                        hoVaTen = request.getAttribute("fullName") + "";
                                        hoVaTen = hoVaTen.equals("null") ? "" : hoVaTen;

                                        email = request.getAttribute("email") + "";
                                        email = email.equals("null") ? "" : email;

                                        phone = request.getAttribute("phone") + "";
                                        phone = phone.equals("null") ? "" : phone;

                                        dateOfBirth = request.getAttribute("dateofbirth") + "";
                                        dateOfBirth = dateOfBirth.equals("null") ? "" : dateOfBirth;

                                        addRess = request.getAttribute("addRess") + "";
                                        addRess = addRess.equals("null") ? "" : addRess;

                                        sex = request.getAttribute("sex") + "";
                                        sex = addRess.equals("null") ? "" : sex;
                                    }
                                } else if (sourceServlet.equals("VertifyController")) {
                                    String hd = request.getAttribute("hanhDong") + "";
                                    hd = hd.equals("null") ? "" : hd;
                                    if (hd.equals("confirm")) {
                                        String trangThaiXacThuc = request.getAttribute("isXacThuc") + "";
                                        trangThaiXacThuc = trangThaiXacThuc.equals("null") ? "" : trangThaiXacThuc;
                                        String msg = request.getAttribute("thongBao") + "";
                                        msg = msg.equals("null") ? "" : msg;
                                        if (trangThaiXacThuc.equals("true")) {
                                            xacThuc = true;
                                            message = msg;
                                        } else {
                                            message = msg;
                                            kiemTra = true;
                                            mo = true;
                                        }
                                    } else if (hd.equals("close")) {
                                        String msg = request.getAttribute("thongBao") + "";
                                        msg = msg.equals("null") ? "" : msg;
                                        message = msg;
                                        dong = true;
                                    } else if (hd.equals("locked")) {
                                        kiemTra = true;
                                        mo = true;
                                        message = request.getAttribute("thongBao") + "";
                                    }

                                } else if (sourceServlet.equals("RegisterImage")) {
                                    String kq = request.getAttribute("kiemTra") + "";
                                    kq = kq.equals("null") ? "" : kq;
                                    if (kq.equals("true")) {
                                        String msg = request.getAttribute("thongBao") + "";
                                        msg = msg.equals("null") ? "" : msg;
                                        message = msg;
                                        xacThuc2 = true;
                                    }
                                } else if (sourceServlet.equals("InsertImage")) {
                                    String kt = request.getAttribute("check") + "";
                                    kt = kt.equals("null") ? "" : kt;
                                    String msg = request.getAttribute("thongTin") + "";
                                    msg = msg.equals("null") ? "" : msg;
                                    if (kt.equals("true")) {
                                        xacThuc3 = true;
                                        message = msg;
                                    }
                                }
                            %>
                            <div class="error-message" id="baoLoi">
                                <%=baoLoi%>
                            </div>
                            <form id="signupForm" action="sign-up" method="post">
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only" for="name">Tên đăng nhập</label>
                                        <input id="name" name="username" type="text" class="form-control"
                                               placeholder="TÊN ĐĂNG NHẬP" required="required"
                                               value="<%=tenDangNhap%>">
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only" for="password">Mật khẩu</label>
                                        <input id="password" name="password" type="password"
                                               class="form-control" placeholder="MẬT KHẨU"
                                               required="required"/>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only" for="again-password">Nhập lại mật
                                            khẩu</label>
                                        <span id="msg" class="error-message"></span>
                                        <input id="again-password" name="again-password" type="password"
                                               class="form-control" placeholder="NHẬP LẠI MẬT KHẨU"
                                               required="required" onkeyup="xacNhanMatKhau()"/>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only" for="fullName">Họ và tên</label>
                                        <input id="fullName" name="fullName" type="text"
                                               class="form-control" placeholder="HỌ VÀ TÊN"
                                               required="required" value="<%=hoVaTen%>">
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only" for="email">Email</label>
                                        <input id="email" name="email" type="email"
                                               class="form-control" placeholder="Email" required="required"
                                               value="<%=email%>">
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only" for="phone">Số điện thoại</label>
                                        <input id="phone" name="phone" type="text"
                                               class="form-control" placeholder="SỐ ĐIỆN THOẠI"
                                               required="required" value="<%=phone%>">
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only" for="dateOfBirth">Ngày sinh</label>
                                        <input id="dateOfBirth" name="dateOfBirth" type="date"
                                               class="form-control" required="required"
                                               value="<%=dateOfBirth%>">
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label class="control-label sr-only" for="address">Địa chỉ</label>
                                        <input id="address" name="address" type="text"
                                               class="form-control" placeholder="ĐỊA CHỈ"
                                               required="required" value="<%=addRess%>">
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
                                    <div class="form-group">
                                        <label for="gioiTinh" class="form-label">Giới tính</label>
                                        <select class="form-control" id="gioiTinh" name="gioiTinh">
                                            <option></option>
                                            <option value="Nam"
                                                    <%=(sex.equals("Nam")) ? "selected = 'selected'" : ""%>>Nam
                                            </option>
                                            <option value="Nữ"
                                                    <%=(sex.equals("Nữ")) ? "selected = 'selected'" : ""%>>Nữ
                                            </option>
                                            <option value="Khác"
                                                    <%=(sex.equals("Khác")) ? "selected = 'selected'" : ""%>>Khác
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
                                    <button type="submit" class="btn btn-primary btn-block mb10">Đăng Ký</button>
                                    <div>
                                        <p>
                                            Bạn đã có tài khoản?<a href="login-form.jsp"> Đăng Nhập</a>
                                        </p>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- features -->
                <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 ">
                    <div class="box-body">
                        <div class="feature-left">
                            <div class="feature-icon">
                                <img src="images/feature_icon_1.png" alt="">
                            </div>
                            <div class="feature-content">
                                <h4>Mức độ uy tín!</h4>
                                <p>Được đánh giá an toàn, tin cậy hàng đầu Việt Nam với
                                    nhiều chính sách hỗ trợ chăm sóc khách hàng.</p>
                            </div>
                        </div>
                        <div class="feature-left">
                            <div class="feature-icon">
                                <img src="images/feature_icon_2.png" alt="">
                            </div>
                            <div class="feature-content">
                                <h4>Thanh toán tức thì!</h4>
                                <p>Thanh toán mọi nơi mọi lúc, giao dịch nhanh gọn, bảo
                                    đảm, an toàn, với liên kết 90% ngân hàng, ví tiền, VISA trong
                                    toàn quốc!</p>
                            </div>
                        </div>
                        <div class="feature-left">
                            <div class="feature-icon">
                                <img src="images/feature_icon_3.png" alt="">
                            </div>
                            <div class="feature-content">
                                <h4>Ưu đãi hấp dẫn!</h4>
                                <p>Với mong muốn làm hài lòng khách hàng, Mobistore luôn
                                    mang đến những ưu đãi cực kỳ tốt với chất lượng cao</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal thông báo đăng ký thành công -->
<% if (kiemTra == true || (request.getAttribute("showVerifyForm") != null && (Boolean)request.getAttribute("showVerifyForm"))) { %>
<div class="modal" id="signupSuccessModal" style="display: flex; z-index: 1000;">
    <div class="modal-content">
        <span class="close-btn" onclick="closeModalAndRedirect()">&times;</span>
        <h3>Đăng ký thành công</h3>
        <p>Vui lòng kiểm tra email để lấy mã xác nhận</p>

        <% if (mo == true) { %>
        <div class="alert alert-warning"><%=message%></div>
        <% } %>

        <form action="VerifyServlet" method="post" class="mt-3">
            <div class="form-group">
                <label for="verificationCode">Mã xác nhận (OTP):</label>
                <input type="text" name="maXacNhan" id="verificationCode"
                       class="form-control" placeholder="Nhập mã OTP" required>
            </div>

            <%-- Only show captcha if showCaptcha attribute is true --%>
            <% if (request.getAttribute("showCaptcha") != null && (Boolean)request.getAttribute("showCaptcha")) { %>
            <div class="form-group mt-3">
                <label for="captcha">Mã bảo mật:</label>
                <div class="d-flex align-items-center gap-2">
                    <input type="text" name="captcha" id="captcha"
                           class="form-control" placeholder="Nhập mã bên dưới" required>
                    <div class="captcha-box bg-light p-2 rounded text-center fw-bold">
                        ${sessionScope.captcha}
                    </div>
                </div>
            </div>
            <% } %>

            <div class="d-flex gap-2 mt-4">
                <button type="submit" name="action" value="confirm"
                        class="btn btn-primary flex-grow-1">Xác nhận</button>
                <button type="button" onclick="closeModalAndRedirect()"
                        class="btn btn-outline-secondary">Đóng</button>
            </div>
        </form>
    </div>
</div>
<% } %>

<!-- Modal thông báo lỗi xác nhận -->
<%
    String hd = request.getAttribute("hanhDong") + "";
    if (hd.equals("locked")) {
        long remainingTime = Long.parseLong(request.getAttribute("remainingTime") + "");
%>
<div class="modal" id="lockedModal" style="display: flex; z-index: 1001;">
    <div class="modal-content">
        <h3 style="color: #d9534f; margin-bottom: 15px;"><%=request.getAttribute("thongBao")%></h3>
        <div id="countdown" style="margin: 15px 0; font-size: 16px;">
            Bạn có thể thử lại sau: <span id="time" style="font-weight: bold;"><%=remainingTime/1000%></span> giây
        </div>
        <div style="display: flex; justify-content: center;">
            <button class="btn-close" id="unlockButton"
                    style="display: none; margin: 20px auto 0;"
                    onclick="closeModal('lockedModal')">Đóng</button>
        </div>
    </div>
</div>
<%
    }
%>

<!-- Modal xác thực thành công -->
<% if (xacThuc == true) { %>
<div class="modal" id="verifySuccessModal" style="display: flex; z-index: 1000;">
    <div class="modal-content">
        <span class="close-btn" onclick="closeModalAndRedirect()">&times;</span>
        <h3><%=message%></h3>
        <button class="btn-close" onclick="closeModalAndRedirect()">Đăng nhập ngay</button>
    </div>
</div>
<% } %>

<!-- Modal đóng -->
<% if (dong == true) { %>
<div class="modal" id="closeModal" style="display: flex; z-index: 1000;">
    <div class="modal-content">
        <span class="close-btn" onclick="closeModalAndRedirect()">&times;</span>
        <h3><%=message%></h3>
        <button class="btn-close" onclick="closeModalAndRedirect()">Đóng</button>
    </div>
</div>
<% } %>

<!-- Modal upload ảnh -->
<% if (xacThuc2 == true) { %>
<div class="modal" id="uploadModal" style="display: flex; z-index: 1000;">
    <div class="modal-content">
        <span class="close-btn" onclick="closeModal('uploadModal')">&times;</span>
        <img src="images/avatar-icon.png" alt="Avatar Icon"/>
        <h3><%=message%></h3>
        <form action="up-Load-File" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label class="control-label sr-only" for="avatar">Ảnh đại diện</label>
                <input id="avatar" name="file" type="file" class="form-control"
                       accept="image/*">
            </div>
            <button class="btn-close" name="action" value="upLoad" type="submit">Upload</button>
            <button class="btn-close" type="button" onclick="closeModal('uploadModal')">
                <a href="http://localhost:8080/MobileWebApp/huyPhien">Upload sau</a>
            </button>
        </form>
    </div>
</div>
<% } %>

<!-- Modal hoàn tất -->
<% if (xacThuc3 == true) { %>
<div class="modal" id="finalSuccessModal" style="display: flex; z-index: 1000;">
    <div class="modal-content">
        <span class="close-btn" onclick="closeModalAndRedirect()">&times;</span>
        <h3><%=message%></h3>
        <button class="btn-close" onclick="closeModalAndRedirect()">Đóng</button>
    </div>
</div>
<% } %>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js" type="text/javascript"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/menumaker.js" type="text/javascript"></script>
<script type="text/javascript" src="js/jquery.sticky.js"></script>
<script type="text/javascript" src="js/sticky-header.js"></script>

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
                            searchBox.value = product.name;
                            suggestionsList.innerHTML = "";
                            suggestionsList.classList.remove("active");
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

    // Hàm đóng modal
    function closeModal(modalId) {
        document.getElementById(modalId).style.display = "none";
    }

    // Hàm hiển thị modal
    function showModal(modalId) {
        document.getElementById(modalId).style.display = "flex";
    }

    // Xử lý khi trang tải xong
    window.onload = function () {
        <% if (kiemTra == true || (request.getAttribute("showVerifyForm") != null && (Boolean)request.getAttribute("showVerifyForm"))) { %>
        showModal('signupSuccessModal');
        <% } %>

        <% if (hd.equals("locked")) { %>
        showModal('lockedModal');
        remainingTime = 10000
        startCountdown(remainingTime);
        <% } %>

        <% if (xacThuc == true) { %>
        showModal('verifySuccessModal');
        <% } %>

        <% if (dong == true) { %>
        showModal('closeModal');
        <% } %>

        <% if (xacThuc2 == true) { %>
        showModal('uploadModal');
        <% } %>

        <% if (xacThuc3 == true) { %>
        showModal('finalSuccessModal');
        <% } %>
    };

    // Hàm đếm ngược thời gian chờ
    function startCountdown(remainingTime) {
        var countdownElement = document.getElementById('countdown');
        var unlockButton = document.getElementById('unlockButton');

        function updateCountdown() {
            var seconds = Math.ceil(remainingTime / 1000);
            document.getElementById("time").textContent = seconds;

            if (remainingTime <= 0) {
                countdownElement.textContent = 'Bạn có thể thử lại ngay bây giờ';
                unlockButton.style.display = 'block';
            } else {
                remainingTime -= 1000;
                setTimeout(updateCountdown, 1000);
            }
        }

        updateCountdown();
    }

    // Hàm đóng modal và chuyển hướng
    function closeModalAndRedirect() {
        window.location.href = 'login-form.jsp';
    }

    // Kiểm tra mật khẩu nhập lại
    function xacNhanMatKhau() {
        var matKhau = document.getElementById("password").value;
        var matKhauNhapLai = document.getElementById("again-password").value;
        if (matKhau != matKhauNhapLai) {
            document.getElementById("msg").innerHTML = "Mật khẩu nhập lại không đúng";
            return false;
        } else {
            document.getElementById("msg").innerHTML = "";
            return true;
        }
    }

    // Xử lý submit form
    document.getElementById('signupForm').addEventListener('submit', function (e) {
        if (!xacNhanMatKhau()) {
            e.preventDefault();
            document.getElementById("msg").style.color = "red";
            document.getElementById("msg").innerHTML = "Mật khẩu nhập lại không khớp!";
        }
    });
</script>

</body>
</html>