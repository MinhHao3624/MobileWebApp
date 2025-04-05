<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	crossorigin="anonymous" referrerpolicy="no-referrer" />

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

///////
/* Đảm bảo CSS chỉ áp dụng cho trang danh sách sản phẩm */
/* Định dạng bảng sản phẩm */
.products-container {
	width: 90%;
	margin: auto;
	font-family: Arial, sans-serif;
	background-color: #f8f9fa;
	padding: 20px;
	border-radius: 8px;
}

.products-container h2 {
	text-align: center;
}

/* Nút Thêm sản phẩm */
.products-container .btn-add {
	padding: 10px 15px;
	background-color: #28a745;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	float: right;
	margin-bottom: 15px;
}

/* Bảng hiển thị sản phẩm */
.products-table {
	width: 100%;
	border-collapse: collapse;
	background: white;
	border-radius: 5px;
	overflow: hidden;
}

/* Tăng khoảng cách giữa các tiêu đề */
.products-table th {
	background-color: #f4f4f4;
	font-weight: bold;
	padding: 16px; /* Tăng padding để giãn khoảng cách */
	text-align: center;
}

.products-table td {
	padding: 12px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

.products-table td img {
	width: 60px;
	height: auto;
}

/* Nút sửa và xóa */
.action-buttons {
	display: flex;
	justify-content: center;
	gap: 8px;
}

.products-table .btn-edit, .products-table .btn-delete {
	padding: 8px 12px;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 14px;
	display: flex;
	align-items: center;
	justify-content: center;
	width: 40px;
	height: 40px;
}

.products-table .btn-edit {
	background-color: #007bff;
}

.products-table .btn-delete {
	background-color: #dc3545;
}

.products-table .btn-edit:hover {
	background-color: #0056b3;
}

.products-table .btn-delete:hover {
	background-color: #a71d2a;
}

/* Phân trang */
/* Phân trang */
.pagination {
	text-align: center;
	margin-top: 15px;
}

.pagination a {
	display: inline-block;
	padding: 6px 10px; /* Giảm kích thước nút */
	margin: 2px;
	text-decoration: none;
	background: #007bff;
	color: white;
	border-radius: 4px;
	font-size: 14px; /* Giảm kích thước chữ */
	transition: background 0.3s;
}

.pagination a.active {
	background: #ff5722;
	font-weight: bold;
}

.pagination a:hover {
	background: #0056b3;
}

////////////////////
/* CSS cho form thêm sản phẩm */
/* Ẩn form ban đầu */
/* Ẩn form ban đầu */
#productForm {
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
	z-index: 999999999999;
}

/* Nội dung form */
.product-form-content {
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
	z-index: 1110000;
}

/* Nút đóng form */
.product-form-close-btn {
	position: absolute;
	top: 10px;
	right: 15px;
	border: none;
	background: none;
	font-size: 24px;
	cursor: pointer;
}

/* Dòng nhập liệu */
.product-form-row {
	display: flex;
	flex-direction: column;
	margin-bottom: 10px;
}

label {
	font-weight: bold;
	margin-bottom: 5px;
}

input, select, textarea {
	padding: 8px;
	width: 100%;
	border: 1px solid #ccc;
	border-radius: 5px;
}

/* Nút submit */
.product-form-submit-btn {
	background-color: #007bff;
	color: white;
	border: none;
	padding: 10px;
	width: 100%;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
}

.product-form-submit-btn:hover {
	background-color: #0056b3;
}

////////////////////////
/* CSS cho form chỉnh sửa sản phẩm */
/* Ẩn form chỉnh sửa ban đầu */
#editProductForm {
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

/* Nội dung form chỉnh sửa */
.edit-product-form-content {
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

/* Nút đóng form chỉnh sửa */
.edit-product-form-close-btn {
	position: absolute;
	top: 10px;
	right: 15px;
	border: none;
	background: none;
	font-size: 24px;
	cursor: pointer;
}

/* Vùng cuộn trong form chỉnh sửa */
.edit-product-form-scrollable {
	max-height: 60vh;
	overflow-y: auto;
	padding-right: 10px;
}

/* Dòng nhập liệu */
.edit-product-form-row {
	display: flex;
	flex-direction: column;
	margin-bottom: 10px;
}

label {
	font-weight: bold;
	margin-bottom: 5px;
}

input, select, textarea {
	padding: 8px;
	width: 100%;
	border: 1px solid #ccc;
	border-radius: 5px;
}

/* Nút lưu chỉnh sửa */
.edit-product-form-submit-btn {
	background-color: #007bff;
	color: white;
	border: none;
	padding: 10px;
	width: 100%;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
}

.edit-product-form-submit-btn:hover {
	background-color: #0056b3;
}

//////////////////////////

/* Đảm bảo modal nằm giữa màn hình */
/* Đảm bảo modal nằm giữa màn hình */
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
									href="http://localhost:8080/MobileWebApp/account-login?userID=${sessionScope.khachHang.userID}"
									class="title hidden-xs">Hi <c:out
											value="${sessionScope.khachHang.userName}" /></a>|</li>
								<li><a href="http://localhost:8080/MobileWebApp/dang-xuat"
									class="title hidden-xs">Log out </a></li>
								<li><a
									href="load-page-favorite-list?userID=${sessionScope.khachHang.userID}"><i
										class="fa fa-heart"></i><sup class="cart-quantity">${soLuongSanPhamLike}</sup></a></li>
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
									href="http://localhost:8080/MobileWebApp/load-product?page=1">Điện
										thoại</a></li>
								<li><a href="go-to-blog">Thông tin</a></li>
								<li><a href="go-to-about">Bài viết</a></li>
								<li><a
									href="http://localhost:8080/MobileWebApp/go-to-contactus">Liên
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
									href="http://localhost:8080/MobileWebApp/account-login?userID=${sessionScope.khachHang.userID}"><li
									class="slide-bar "><i class="fa fa-edit"></i><span>Thông
											tin tài khoản</span></li></a>
								<a href="go-to-don-hang?page=1"><li class="slide-bar active"><i
										class="fas fa-money-check"></i><span>Quản lý đơn hàng</span></li></a>
								<a href="go-to-phan-hoi?page=1"><li class="slide-bar"><i
										class="fas fa-money-check"></i><span>Phản hồi</span></li></a>
								<a href="go-to-so-du?userID=${sessionScope.khachHang.userID}"><li
									class="slide-bar"><i class="fas fa-money-check"></i><span>Số
											dư</span></li></a>
								<a href="go-to-nang-cap?userID=${sessionScope.khachHang.userID}"><li
									class="slide-bar"><i class="fas fa-money-check"></i><span>Nâng
											cấp</span></li></a>
								<c:if test="${sessionScope.khachHang.role.roleID == 3}">
									<a
										href="go-to-quan-li-san-pham?userID=${sessionScope.khachHang.userID}&page=1"><li
										class="slide-bar"><i class="fas fa-money-check"></i><span>Quản
												lí sản phẩm</span></li></a>
									<a
										href="go-to-quan-li-don-hang?userID=${sessionScope.khachHang.userID}&page=1"><li
										class="slide-bar"><i class="fas fa-money-check"></i><span>Quản
												lí đơn hàng</span></li></a>
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
					<div class="products-container">
						<h2>Danh sách sản phẩm</h2>
						<button class="btn-add" id="showFormBtn">+ Thêm sản phẩm
							mới</button>

						<table class="products-table">
							<thead>
								<tr>
									<th>Hình ảnh</th>
									<th>Mã sản phẩm</th>
									<th>Tên</th>
									<th>Hãng SX</th>
									<th>SL còn lại</th>
									<th>Màu sắc</th>
									<th>Giá bán</th>
									<th>Hành động</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="product" items="${listSP}">
									<tr id="productRow${product.productID}">
										<td><img src="imagesphone/${product.image}" alt=""></td>
										<td>${product.productID}</td>
										<td class="productName">${product.name}</td>
										<td class="productNameCate">${product.categories.nameCategories}</td>
										<td class="productQuantity">${product.stockQuantity}</td>
										<td class="productColor">${product.informationPro.color}</td>
										<td class="productPrice">${product.price}</td>
										<td class="action-buttons">
											<button class="btn-edit" id="showEditFormBtn"
												data-product-id="${product.productID}">✏️</button>
											<button class="btn-delete" data-product-id="${product.productID}">🗑️</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<!-- Phân trang -->
						<div class="pagination">
							<a
								href="go-to-quan-li-san-pham?userID=${sessionScope.khachHang.userID}&page=${currentPage == 1 ? tongSoTrang : currentPage - 1}">Trước</a>

							<c:forEach var="i" begin="1" end="${tongSoTrang}">
								<a
									href="go-to-quan-li-san-pham?userID=${sessionScope.khachHang.userID}&page=${i}"
									class="${i == currentPage ? 'active' : ''}">${i}</a>
							</c:forEach>

							<a
								href="go-to-quan-li-san-pham?userID=${sessionScope.khachHang.userID}&page=${currentPage == tongSoTrang ? 1 : currentPage + 1}">Sau</a>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<div id="productForm" class="product-form-container"
		class="form-container" style="display: none;">
		<div class="product-form-content">
			<button id="closeFormBtn" class="product-form-close-btn" style="color:red">&times;</button>
			<h2 style="color:red;">Thêm sản phẩm</h2>
			<span style="color: red;">Lưu ý:Hiện tại Web chỉ cho ctv lời 5% từ số tiền của sản phẩm bạn đăng bán.</br> Và bạn sẽ bị trừ 95% số tiền của sản phẩm đăng bán vào số dư hiện có</span>
			<form action="add-product-ctv" method="POST">

				<div class="product-form-row">
					<label for="productName">Tên sản phẩm:</label> <input type="text"
						id="productName" placeholder="Nhập tên sản phẩm" name="tenSanPham"
						required="required">
				</div>
				<div class="product-form-row">
					<label for="brand">Tên Thương hiệu:</label> <select id="brand"
						name="tenThuongHieu" required="required">
						<option value="Iphone">IPhone</option>
						<option value="Samsung">Samsung</option>
						<option value="Xiaomi">Xiaomi</option>
						<option value="Vsmart">Vsmart</option>
						<option value="OPPO">OPPO</option>
						<option value="Vivo">Vivo</option>
						<option value="Nokia">Nokia</option>
						<option value="Huawei">Huawei</option>
					</select>
				</div>
				<div class="product-form-row">
					<label for="price">Giá bán (VND):</label> <input type="number"
						id="price" placeholder="20.000.000" name="giaBan"
						required="required">
				</div>
				<div class="product-form-row">
					<label for="discountPrice">Giá bán Khuyến mãi (VND):</label> <input
						type="number" id="discountPrice" placeholder="20.000.000">
				</div>
				<div class="product-form-row">
					<label for="color">Màu sắc:</label> <input type="text" id="color"
						placeholder="Nhập màu sắc" name="color" required="required">
				</div>
				<div class="product-form-row">
					<label for="quantity">Số lượng:</label> <input type="number"
						id="quantity" placeholder="Nhập số lượng" name="soLuong"
						required="required">
				</div>
				<div class="product-form-row">
					<label for="description">Giới thiệu sản phẩm:</label>
					<textarea id="description" rows="4" name="description"
						required="required"></textarea>
				</div>

				<h3>Thông số kĩ thuật</h3>
				<div class="product-form-row">
					<label for="screen">Màn hình:</label> <input type="text"
						id="screen" placeholder="Nhập thông số màn hình" name="manHinh"
						required="required">
				</div>
				<div class="product-form-row">
					<label for="os">Hệ điều hành:</label> <input type="text" id="os"
						placeholder="Nhập hệ điều hành" name="os" required="required">
				</div>
				<div class="product-form-row">
					<label for="rearCamera">Camera sau:</label> <input type="text"
						id="rearCamera" placeholder="Nhập thông số camera sau"
						name="cameraSau" required="required">
				</div>
				<div class="product-form-row">
					<label for="frontCamera">Camera trước:</label> <input type="text"
						id="frontCamera" placeholder="Nhập thông số camera trước"
						name="cameraTruoc" required="required">
				</div>
				<div class="product-form-row">
					<label for="cpu">CPU:</label> <input type="text" id="cpu"
						placeholder="Nhập thông số CPU" name="cpu" required="required">
				</div>
				<div class="product-form-row">
					<label for="memory">Bộ nhớ:</label> <input type="text" id="memory"
						placeholder="Nhập thông số bộ nhớ" name="memory"
						required="required">
				</div>
				<div class="product-form-row">
					<label for="screenSize">Kích thước màn hình:</label> <input
						type="text" id="screenSize" placeholder="Nhập thông số màn hình"
						name="kichThuocManHinh" required="required">
				</div>
				<div class="product-form-row">
					<label for="glass">Glass:</label> <input type="text" id="glass"
						placeholder="Nhập Glass của máy" name="glass" required="required">
				</div>
				<div class="product-form-row">
					<label for="resolution">Resolution:</label> <input type="text"
						id="resolution" placeholder="Nhập resolution của máy"
						name="resolution" required="required">
				</div>
				<div class="product-form-row">
					<label for="ram">Ram:</label> <input type="text" id="ram"
						placeholder="Nhập thông số ram" name="ram" required="required">
				</div>
				<div class="product-form-row">
					<label for="gpu">GPU:</label> <input type="text" id="gpu"
						placeholder="Nhập thông số gpu" name="gpu" required="required">
				</div>
				<div class="product-form-row">
					<label for="sim">SIM:</label> <input type="text" id="sim"
						placeholder="Nhập thông số sim" name="sim" required="required">
				</div>
				<div class="product-form-row">
					<label for="battery">Battery:</label> <input type="text"
						id="battery" placeholder="Nhập thông số battery" name="battery"
						required="required">
				</div>
				<div class="product-form-row">
					<label for="memoryCard">Memory Card:</label> <input type="text"
						id="memoryCard" placeholder="Nhập thông số thẻ nhớ"
						name="memoryCard" required="required">
				</div>

				<button type="submit" class="product-form-submit-btn">Thêm
					sản phẩm</button>
			</form>
		</div>
	</div>


	<div id="editProductForm" class="edit-product-form-container"
		style="display: none;">
		<div class="edit-product-form-content">
			<button id="closeEditFormBtn" class="edit-product-form-close-btn">&times;</button>
			<h2>Chỉnh sửa sản phẩm</h2>
			<form id="editProductFormContent">
				<div class="edit-product-form-scrollable">
					<div class="edit-product-form-row">
						<label for="productName">ID:</label> <input type="text"
							id="productID2" name="productName" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="productName">Tên sản phẩm:</label> <input type="text"
							id="productName2" name="productName" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="brand">Tên Thương hiệu:</label> <select id="brand2"
							name="brand" required="required">
							<option value="Iphone">IPhone</option>
							<option value="Samsung">Samsung</option>
							<option value="Xiaomi">Xiaomi</option>
							<option value="Vsmart">Vsmart</option>
							<option value="OPPO">OPPO</option>
							<option value="Vivo">Vivo</option>
							<option value="Nokia">Nokia</option>
							<option value="Huawei">Huawei</option>
						</select>
					</div>
					<div class="edit-product-form-row">
						<label for="price">Giá bán (VND):</label> <input type="text"
							id="price2" name="price" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="discountPrice">Giá bán Khuyến mãi (VND):</label> <input
							type="number" id="discountPrice2" name="discountPrice">
					</div>
					<div class="edit-product-form-row">
						<label for="color">Màu sắc:</label> <input type="text" id="color2"
							name="color" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="quantity">Số lượng:</label> <input type="number"
							id="quantity2" name="quantity" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="description">Giới thiệu sản phẩm:</label>
						<textarea id="description2" name="description" rows="4"
							required="required"></textarea>
					</div>

					<h3>Thông số kĩ thuật</h3>
					<div class="edit-product-form-row">
						<label for="screen">Màn hình:</label> <input type="text"
							id="screen2" name="screen" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="os">Hệ điều hành:</label> <input type="text" id="os2"
							name="os" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="rearCamera">Camera sau:</label> <input type="text"
							id="rearCamera2" name="rearCamera" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="frontCamera">Camera trước:</label> <input type="text"
							id="frontCamera2" name="frontCamera" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="cpu">CPU:</label> <input type="text" id="cpu2"
							name="cpu" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="memory">Bộ nhớ:</label> <input type="text"
							id="memory2" name="memory" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="screenSize">Kích thước màn hình:</label> <input
							type="text" id="screenSize2" name="screenSize"
							required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="glass">Glass:</label> <input type="text" id="glass2"
							name="glass" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="resolution">Resolution:</label> <input type="text"
							id="resolution2" name="resolution" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="ram">Ram:</label> <input type="text" id="ram2"
							name="ram" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="gpu">GPU:</label> <input type="text" id="gpu2"
							name="gpu" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="sim">SIM:</label> <input type="text" id="sim2"
							name="sim" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="battery">Battery:</label> <input type="text"
							id="battery2" name="battery" required="required">
					</div>
					<div class="edit-product-form-row">
						<label for="memoryCard">Memory Card:</label> <input type="text"
							id="memoryCard2" name="memoryCard" required="required">
					</div>
				</div>
				<button type="submit" class="edit-product-form-submit-btn"
					id="saveChangesBtn">Lưu thay đổi</button>
			</form>
		</div>
	</div>






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
								class="btn-social btn-twitter"><i class="fa fa-twitter"></i></a></span>
							<span><a href="#" class="btn-social btn-googleplus"><i
									class="fa fa-google-plus"></i></a></span> <span><a href="#"
								class=" btn-social btn-linkedin"><i class="fa fa-linkedin"></i></a></span>
							<span><a href="#" class=" btn-social btn-pinterest"><i
									class="fa fa-pinterest-p"></i></a></span> <span><a href="#"
								class=" btn-social btn-instagram"><i class="fa fa-instagram"></i></a></span>
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
	<c:if test="${check50 == true}">
		<form action="/MobileWebApp/up-Load-Anh-Dien-Thoai-CTV" method="post"
			enctype="multipart/form-data">
			<div class="modal5" id="successModal5">
				<div class="modal-content5">
					<img
						src="https://tse3.mm.bing.net/th?id=OIP.pAyRN_lNf6IPukCMXYcRcQAAAA&pid=Api&P=0&h=180"
						alt="Avatar Icon" />
					<h3>${baoLoi}</h3>
					<div class="form-group">
						<label class="control-label  sr-only" for="re_password"></label> <input
							id="avatar" name="file" type="file" class="form-control"
							accept="image/*">
					</div>
					<button class="btn-close5" name="action" value="upLoad"
						type="submit">Upload</button>

				</div>
			</div>
		</form>
		</c:if>
	
	<c:if test="${complete == true}">
		<div class="modal5" id="successModal5">
			<div class="modal-content5">
				<img
					src="https://tse1.mm.bing.net/th?id=OIP.jZnEX7kzfh_5H-lln_XraAHaDt&pid=Api&P=0&h=180"
					alt="Notify Icon" style="width: 100px; height: 50px" />
				<h3>${message}</h3>
				<button class="btn-close5" onclick="closeModal()" name="action"
					value="xacThuc">Đóng</button>
			</div>
		</div>
	</c:if>
	<script>
        function deleteProduct(code) {
            if (confirm("Bạn có chắc chắn muốn xóa sản phẩm " + code + "?")) {
                window.location.href = "deleteProductServlet?code=" + code;
            }
        }
        function closeModal() {
			document.getElementById("successModal5").style.display = "none";

		}
        
    </script>
    <script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function () {
        // Lắng nghe tất cả nút xóa
        const deleteButtons = document.querySelectorAll(".btn-delete");

        deleteButtons.forEach(function (button) {
            button.addEventListener("click", function () {
                const productId = this.getAttribute("data-product-id");
                console.log(productId);

                // Hiển thị xác nhận
                const confirmDelete = confirm("Bạn có muốn thu hồi sản phẩm này không?Nếu thu hồi bạn sẽ nhận được 85%/1 sản phẩm");

                if (confirmDelete) {
                    // Gửi yêu cầu xóa đến Servlet
                    fetch("deleteProductCTV", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/x-www-form-urlencoded",
                        },
                        body: "productID=" + encodeURIComponent(productId),
                    })
                    .then(response =>  response.json())
                    .then(data => {
                        if (data.success) {
                            // Xóa dòng sản phẩm khỏi giao diện
                            const row = document.getElementById("productRow" + data.productID);
                            if (row) row.remove();

                            alert("Đã thu hồi sản phẩm thành công!");
                        } else {
                            alert("Sản phẩm đang được đặt mua không thể thu hồi lúc này");
                        }
                    })
                    .catch(error => {
                        console.error("Lỗi khi gửi yêu cầu xóa:", error);
                        alert("Không thể kết nối đến máy chủ.");
                    });
                }
            });
        });
    });    
    </script>
	<script type="text/javascript">
    document.getElementById("saveChangesBtn").addEventListener("click", function(event) {
        event.preventDefault(); // Ngăn chặn hành vi mặc định của form

        // Tạo đối tượng chứa dữ liệu
        const formData = new URLSearchParams();
        formData.append("productID", document.getElementById("productID2").value);
        formData.append("productName", document.getElementById("productName2").value);
        formData.append("brand", document.getElementById("brand2").value);
        formData.append("price", document.getElementById("price2").value);
        formData.append("discountPrice", document.getElementById("discountPrice2").value);
        formData.append("color", document.getElementById("color2").value);
        formData.append("quantity", document.getElementById("quantity2").value);
        formData.append("description", document.getElementById("description2").value);
        formData.append("screen", document.getElementById("screen2").value);
        formData.append("os", document.getElementById("os2").value);
        formData.append("rearCamera", document.getElementById("rearCamera2").value);
        formData.append("frontCamera", document.getElementById("frontCamera2").value);
        formData.append("cpu", document.getElementById("cpu2").value);
        formData.append("memory", document.getElementById("memory2").value);
        formData.append("screenSize", document.getElementById("screenSize2").value);
        formData.append("glass", document.getElementById("glass2").value);
        formData.append("resolution", document.getElementById("resolution2").value);
        formData.append("ram", document.getElementById("ram2").value);
        formData.append("gpu", document.getElementById("gpu2").value);
        formData.append("sim", document.getElementById("sim2").value);
        formData.append("battery", document.getElementById("battery2").value);
        formData.append("memoryCard", document.getElementById("memoryCard2").value);

        // Gửi dữ liệu lên Servlet bằng Fetch API (URL Encoded)
        fetch('capNhatSanPhamMoi', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded', // Quan trọng để Servlet nhận dạng đúng
            },
            body: formData.toString() // Chuyển dữ liệu thành chuỗi URL Encoded
        })
        .then(response => response.json())
        .then(data => {
            if (data.success1) {
                alert('Cập nhật sản phẩm thành công!');
                // Cập nhật dòng sản phẩm
                updateProductRowInTable(data.productID, Object.fromEntries(formData.entries())); // Chuyển đổi formData thành object và cập nhật
                closeEditForm(); // Đóng form chỉnh sửa
            } else if(data.success2) {
                alert('Cập nhật sản phẩm thành công. Số lượng trong kho giảm đi nên bạn được cộng thêm tiền vào số dư (85%/1)');
                updateProductRowInTable(data.productID, Object.fromEntries(formData.entries())); // Chuyển đổi formData thành object và cập nhật
                closeEditForm(); // Đóng form chỉnh sửa
            }else if(data.success3) {
            	alert('Cập nhật sản phẩm thành công. Do số lượng sản phẩm tăng lên nên bạn bị trừ tiền ở số dư (95%/1)');
            	updateProductRowInTable(data.productID, Object.fromEntries(formData.entries())); // Chuyển đổi formData thành object và cập nhật
                closeEditForm(); // Đóng form chỉnh sửa
            }else{
            	alert('cập nhật sản phẩm thất bại vì không đủ số dư');
            	closeEditForm(); //
            }
        })
        .catch(error => {
            console.error('Có lỗi xảy ra:', error);
            alert('Đã có lỗi xảy ra, vui lòng thử lại.');
        });
    });

    // Cập nhật dữ liệu dòng sản phẩm trong bảng
    function updateProductRowInTable(productId, productData) {
        const productRow = document.getElementById('productRow' + productId); // ID dòng sản phẩm
        if (productRow) {
            productRow.querySelector('.productName').innerText = productData.productName;
            productRow.querySelector('.productNameCate').innerText = productData.brand;
            productRow.querySelector('.productQuantity').innerText = productData.quantity;
            productRow.querySelector('.productColor').innerText = productData.color;
            productRow.querySelector('.productPrice').innerText = productData.price;
            // Cập nhật các trường khác nếu cần
        }
    }

 // Đóng form chỉnh sửa
    function closeEditForm() {
        document.getElementById("editProductForm").style.display = "none";
    }
    
    </script>
	<script type="text/javascript">
    const showEditFormBtn = document.getElementById('showEditFormBtn');
    const editProductForm = document.getElementById('editProductForm');
    const closeEditFormBtn = document.getElementById('closeEditFormBtn');
    const editProductFormContent = document.getElementById('editProductFormContent');


    closeEditFormBtn.addEventListener('click', () => {
        editProductForm.style.display = 'none';
    });

    // Hàm để tải dữ liệu sản phẩm lên form
   // Lắng nghe sự kiện click cho tất cả nút "Sửa"
document.querySelectorAll('.btn-edit').forEach(button => {
    button.addEventListener('click', (event) => {
        const productId = event.target.dataset.productId; // Lấy productId từ data-product-id
        console.log("Product ID:", productId);

        // Hiển thị form chỉnh sửa
        document.getElementById('editProductForm').style.display = 'block';

        // Gọi hàm để tải dữ liệu sản phẩm
        loadProductData(productId);
    });
});

// Hàm tải dữ liệu sản phẩm
function loadProductData(productId) {
    console.log("Đang tải dữ liệu cho sản phẩm ID:", productId);

    fetch('tai-du-lieu-sp?productId=' + productId, {
    	method: 'GET',
    })
	   .then(response => response.json()) // Chuyển đổi kết quả thành JSON
	   .then(data => {
		   console.log(data); 
	      if (data.success) {
	    	document.getElementById("productID2").value = data.productID;
            document.getElementById("productName2").value = data.tenSP;
            document.getElementById("brand2").value = data.tenThuongHieu.toLowerCase();
            document.getElementById("price2").value = data.giaBan;
            document.getElementById("discountPrice2").value = data.giaKM;
            document.getElementById("color2").value = data.color;
            document.getElementById("quantity2").value = data.soLuong;
            document.getElementById("description2").value = data.des;
            document.getElementById("screen2").value = data.manHinh;
            document.getElementById("os2").value = data.hdh;
            document.getElementById("rearCamera2").value = data.cameraSau;
            document.getElementById("frontCamera2").value = data.cameraTruoc;
            document.getElementById("cpu2").value = data.cpu;
            document.getElementById("memory2").value = data.boNho;
            document.getElementById("screenSize2").value = data.kichThuoc;
            document.getElementById("glass2").value = data.glass;
            document.getElementById("resolution2").value = data.resolution;
            document.getElementById("ram2").value = data.ram;
            document.getElementById("gpu2").value = data.gpu;
            document.getElementById("sim2").value = data.sim;
            document.getElementById("battery2").value = data.battery;
            document.getElementById("memoryCard2").value = data.memoryCard;
	      }else{
	    	  alert("Có lỗi xảy ra");
	      }
        })
        .catch(err => {
            console.error('Lỗi khi load dữ liệu sản phẩm:', err);
        });
}


   /*  // Xử lý sự kiện submit form
    editProductFormContent.addEventListener('submit', (event) => {
        event.preventDefault(); // Ngăn chặn form gửi đi mặc định

        // Lấy dữ liệu từ form
        const formData = new FormData(editProductFormContent);

        // Gửi dữ liệu đến servlet để cập nhật sản phẩm
        fetch('/your-servlet-url', {
            method: 'POST',
            body: formData
        })
        .then(response => {
            if (response.ok) {
                alert('Cập nhật sản phẩm thành công!');
                editProductForm.style.display = 'none'; // Ẩn form sau khi cập nhật
            } else {
                alert('Lỗi khi cập nhật sản phẩm.');
            }
        })
        .catch(error => {
            console.error('Lỗi khi gửi dữ liệu:', error);
        });
    }); */
    
    </script>
	<script type="text/javascript">
    const showFormBtn = document.getElementById('showFormBtn');
    const productForm = document.getElementById('productForm');
    const closeFormBtn = document.getElementById('closeFormBtn');

    showFormBtn.addEventListener('click', () => {
      productForm.style.display = 'block';
    });

    closeFormBtn.addEventListener('click', () => {
      productForm.style.display = 'none';
    });
    </script>
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
	    	console.log(`URL Fetch: SearchServlet?ans=`+keyword);
	        fetch(`SearchServlet?ans=`+keyword)
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

