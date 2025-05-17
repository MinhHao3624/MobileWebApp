<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="keywords" content="">
	<title>Duy LTW</title>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
	<link rel="stylesheet" href="fonts/fontawesome-free-5.15.4-web/css/all.min.css">
	<link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,300i,400,400i,700,700i" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<style type="text/css">
		.search-bg {
			position: relative;
		}
		.suggestions-list {
			position: absolute;
			top: 100%;
			left: 0;
			right: 0;
			background-color: white;
			z-index: 10000000000000;
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
		.modal.active {
			display: flex;
		}
		.modal-content {
			background-color: #fff;
			padding: 30px;
			border-radius: 8px;
			text-align: center;
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
		.quantity-input {
			width: 60px;
			text-align: center;
		}
		.btn-quantity {
			padding: 5px 10px;
			cursor: pointer;
			background-color: #f8f8f8;
			border: 1px solid #ddd;
		}
		.btn-remove {
			color: #d9534f;
			cursor: pointer;
		}
	</style>
</head>
<body>
<!-- top-header -->
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
	</div>
</div>
<!-- header-section -->
<div class="header-wrapper">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-8">
				<div class="logo">
					<a href="index.html"><img src="images/logo.png" alt=""></a>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
				<form action="SearchOnBox" method="post">
					<div class="search-bg">
						<input type="text" class="form-control" placeholder="Tìm kiếm sản phẩm..." id="searchBox" autocomplete="off" list="product-suggestions" name="searchOnBox" value="${required}">
						<input type="hidden" id="pageNumber" name="page" value="1">
						<button type="submit"><i class="fa fa-search"></i></button>
						<ul id="product-suggestions" class="suggestions-list"></ul>
					</div>
				</form>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
				<div class="account-section">
					<ul>
						<c:if test="${empty sessionScope.khachHang}">
							<li><a href="noAccount.jsp" class="title hidden-xs">Tài khoản</a></li>
							<li class="hidden-xs">|</li>
							<li><a href="login-form.jsp" class="title hidden-xs">Đăng Nhập</a></li>
						</c:if>
						<c:if test="${not empty sessionScope.khachHang}">
							<li><a href="account-login?userID=${sessionScope.khachHang.userID}" class="title hidden-xs">Hi <c:out value="${sessionScope.khachHang.userName}" /></a>|</li>
							<li><a href="dang-xuat" class="title hidden-xs">Log out</a></li>
							<li><a href="load-page-favorite-list?userID=${sessionScope.khachHang.userID}"><i class="fa fa-heart"></i><sup class="cart-quantity">${soLuongSanPhamLike}</sup></a></li>
							<li><a href="go-to-cart" class="title"><i class="fa fa-shopping-cart"></i><sup class="cart-quantity" id="cart-quantity">${soLuongSP}</sup></a></li>
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
							<li><a href="load-product?page=1">Điện thoại</a></li>
							<li class="has-sub"><a href="load-accessories">Phụ kiện</a>
								<ul>
									<li><a href="load-accessories?type=op-lung&page=1">Ốp lưng - Bao da</a></li>
									<li><a href="load-accessories?type=sac-cap&page=1">Sạc - Cáp</a></li>
									<li><a href="load-accessories?type=tai-nghe&page=1">Tai nghe</a></li>
									<li><a href="load-accessories?type=pin-sac-du-phong&page=1">Pin sạc dự phòng</a></li>
									<li><a href="load-accessories?type=dan-man-hinh&page=1">Dán màn hình</a></li>
									<li><a href="load-accessories?type=hub-chuyen-doi&page=1">Hub chuyển đổi</a></li>
								</ul>
							</li>
							<li><a href="go-to-blog">Thông tin</a></li>
							<li><a href="go-to-about">Bài viết</a></li>
							<li><a href="go-to-contactus">Liên hệ, hỗ trợ</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- page-header -->
<div class="page-header">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="page-breadcrumb">
					<ol class="breadcrumb">
						<li><a href="#">Trang chủ</a></li>
						<li>Giỏ hàng</li>
					</ol>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- cart-section -->
<div class="container">
	<div class="cart-content mt30 mb30">
		<div class="title-header mb20">
			<h2 class="title">Giỏ Hàng</h2>
			<p><span class="text-blue" id="cart-item-count">${cartItems.size()}</span> sản phẩm trong giỏ hàng của bạn</p>
		</div>
		<c:if test="${empty cartItems}">
			<p>Giỏ hàng của bạn đang trống.</p>
		</c:if>
		<c:if test="${not empty cartItems}">
			<table class="table">
				<thead class="thead-light">
				<tr>
					<th>Sản phẩm</th>
					<th scope="col">Đơn giá</th>
					<th scope="col">Số lượng</th>
					<th scope="col">Thành tiền</th>
					<th scope="col"></th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="item" items="${cartItems}">
					<tr data-cart-item-id="${item.cartItemID}">
						<td>
							<div class="product-title item-center">
								<img src="imagesphone/${item.product.image}" alt="" style="width: 50px;">
								<div>
									<p>${item.product.name}</p>
									<p>Màu sắc: ${item.product.informationPro.color}</p>
								</div>
							</div>
						</td>
						<td><div class="item-center"><fmt:formatNumber value="${item.price}" type="currency" currencySymbol="đ" /></div></td>
						<td>
							<div class="item-center">
								<div class="quantity">
									<button class="btn-quantity decrease-quantity">-</button>
									<input type="number" max="${item.product.stockQuantity}" min="1" name="quantity" value="${item.quantity}" class="quantity-input" readonly id="quantity-${item.cartItemID}">
									<button class="btn-quantity increase-quantity">+</button>
								</div>
							</div>
						</td>
						<td><div class="item-center text-red" id="item-total-${item.cartItemID}"><fmt:formatNumber value="${item.price * item.quantity}" type="currency" currencySymbol="đ" /></div></td>
						<td><div class="item-center"><i class="fa fa-trash btn-remove"></i></div></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<c:set var="totalAmount" value="0" />
			<c:forEach var="item" items="${cartItems}">
				<c:set var="totalAmount" value="${totalAmount + (item.price * item.quantity)}" />
			</c:forEach>
			<div class="prices-summary">
				<div class="left-content">
					<a href="LoadDataMain" class="derection-product text-blue"><i class="fas fa-long-arrow-alt-left"></i> Tiếp tục mua hàng</a>
				</div>
				<div class="right-con">
					<div class="total-receipt">
						<ul class="prices pinside20">
							<li class="prices-item">
								<span class="prices-text">Tạm tính</span>
								<span class="prices-value"><fmt:formatNumber value="${totalAmount}" type="currency" currencySymbol="đ" /></span>
							</li>
							<li class="prices-item">
								<span class="prices-text">Giảm giá</span>
								<span class="prices-value">0đ</span>
							</li>
						</ul>
						<div class="prices-total pinside20">
							<span class="price-text">Tổng cộng</span>
							<span class="prices-value prices-final text-red" id="cart-total"><fmt:formatNumber value="${totalAmount}" type="currency" currencySymbol="đ" /></span>
						</div>
					</div>
					<a href="go-to-checkout" class="btn-default btn-checkout">Mua Hàng</a>
				</div>
			</div>
		</c:if>
	</div>
</div>
<!-- footer -->
<div class="footer">
	<div class="container">
		<div class="row">
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
				<div class="footer-widget">
					<h3 class="footer-title">Thông tin hỗ trợ</h3>
					<div class="contact-info">
						<span class="contact-icon"><i class="fa fa-map-marker"></i></span>
						<span class="contact-text">Phường Linh Trung, Thủ Đức<br>Thành phố Hồ Chí Minh, Việt Nam - 1955</span>
					</div>
					<div class="contact-info">
						<span class="contact-icon"><i class="fa fa-phone"></i></span>
						<span class="contact-text">+084-123-4567 / 89</span>
					</div>
					<div class="contact-info">
						<span class="contact-icon"><i class="fa fa-envelope"></i></span>
						<span class="contact-text">nhom21@ltweb.com</span>
					</div>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
				<div class="footer-widget">
					<h3 class="footer-title">Tiện ích</h3>
					<ul class="arrow">
						<li><a href="index.html">Trang chủ</a></li>
						<li><a href="product-list.html">Điện thoại</a></li>
						<li><a href="about.html">Thông tin</a></li>
						<li><a href="blog-default.html">Bài viết</a></li>
						<li><a href="contact-us.html">Liên hệ, hỗ trợ</a></li>
					</ul>
				</div>
			</div>
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
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
			<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
				<div class="footer-widget">
					<h3 class="footer-title">Liên lạc với chúng tôi</h3>
					<div class="ft-social">
						<span><a href="#" class="btn-social btn-facebook"><i class="fa fa-facebook"></i></a></span>
						<span><a href="#" class="btn-social btn-twitter"><i class="fa fa-twitter"></i></a></span>
						<span><a href="#" class="btn-social btn-googleplus"><i class="fa fa-google-plus"></i></a></span>
						<span><a href="#" class="btn-social btn-linkedin"><i class="fa fa-linkedin"></i></a></span>
						<span><a href="#" class="btn-social btn-pinterest"><i class="fa fa-pinterest-p"></i></a></span>
						<span><a href="#" class="btn-social btn-instagram"><i class="fa fa-instagram"></i></a></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="tiny-footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="payment-method alignleft">
						<ul>
							<li><a href="#"><i class="fa fa-cc-paypal fa-2x"></i></a></li>
							<li><a href="#"><i class="fa fa-cc-mastercard fa-2x"></i></a></li>
							<li><a href="#"><i class="fa fa-cc-visa fa-2x"></i></a></li>
							<li><a href="#"><i class="fa fa-cc-discover fa-2x"></i></a></li>
						</ul>
					</div>
					<p class="alignright">
						Copyright © All Rights Reserved 2020 Template Design by
						<a href="https://easetemplate.com/" target="_blank" class="copyrightlink">Nhom 21</a>
					</p>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Modal thông báo -->
<div class="modal" id="cart-modal">
	<div class="modal-content">
		<img id="modal-icon" src="" alt="Icon" style="width: 50px; height: 50px">
		<h3 id="modal-title"></h3>
		<p id="modal-message"></p>
		<button class="btn-close">Đóng</button>
	</div>
</div>
<!-- Scripts -->
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/menumaker.js" type="text/javascript"></script>
<script src="js/jquery.sticky.js" type="text/javascript"></script>
<script src="js/sticky-header.js" type="text/javascript"></script>
<script src="js/owl.carousel.min.js" type="text/javascript"></script>
<script src="js/multiple-carousel.js" type="text/javascript"></script>
<script type="text/javascript">
	// Hàm hiển thị modal
	function showModal(title, message, isSuccess) {
		const modal = document.getElementById('cart-modal');
		const modalTitle = document.getElementById('modal-title');
		const modalMessage = document.getElementById('modal-message');
		const modalIcon = document.getElementById('modal-icon');

		modalTitle.textContent = title;
		modalMessage.textContent = message;
		modalIcon.src = isSuccess
				? 'https://tse1.mm.bing.net/th?id=OIP.jZnEX7kzfh_5H-lln_XraAHaDt&pid=Api&P=0&h=180'
				: 'https://cdn-icons-png.flaticon.com/512/753/753344.png';
		modal.classList.add('active');

		modal.querySelector('.btn-close').onclick = () => modal.classList.remove('active');
		setTimeout(() => modal.classList.remove('active'), 3000);
	}

	// Hàm debounce
	function debounce(func, wait) {
		let timeout;
		return function executedFunction(...args) {
			const later = () => {
				clearTimeout(timeout);
				func(...args);
			};
			clearTimeout(timeout);
			timeout = setTimeout(later, wait);
		};
	}

	// Tăng/giảm số lượng
	document.querySelectorAll('.decrease-quantity').forEach(button => {
		button.addEventListener('click', debounce(function() {
			const row = this.closest('tr');
			const cartItemID = row.dataset.cartItemId;
			const input = row.querySelector('.quantity-input');
			const currentQuantity = parseInt(input.value);
			if (currentQuantity > 1) {
				updateQuantity(cartItemID, currentQuantity - 1, row);
			}
		}, 300));
	});

	document.querySelectorAll('.increase-quantity').forEach(button => {
		button.addEventListener('click', debounce(function() {
			const row = this.closest('tr');
			const cartItemID = row.dataset.cartItemId;
			const input = row.querySelector('.quantity-input');
			const currentQuantity = parseInt(input.value);
			const max = parseInt(input.max);
			if (currentQuantity < max) {
				updateQuantity(cartItemID, currentQuantity + 1, row);
			} else {
				showModal('Lỗi', `Chỉ còn ${max} sản phẩm trong kho.`, false);
			}
		}, 300));
	});

	// Cập nhật số lượng qua AJAX
	function updateQuantity(cartItemID, quantity, row) {
		console.log('Sending request to update quantity for cartItemID: ' + cartItemID + ', quantity: ' + quantity);
		fetch(`${pageContext.request.contextPath}/tang-so-luong?cartItemID=${cartItemID}&quantity=${quantity}`, {
			method: 'GET',
			headers: { 'Content-Type': 'application/json' }
		})
				.then(response => {
					if (!response.ok) {
						throw new Error(`HTTP error! status: ${response.status}`);
					}
					return response.json();
				})
				.then(data => {
					if (!data || typeof data.status !== 'string') {
						throw new Error('Invalid JSON response');
					}
					if (data.status === 'success') {
						const input = row.querySelector('.quantity-input');
						input.value = quantity;
						row.querySelector('.text-red').textContent = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(data.itemTotal);
						document.querySelector('#cart-total').textContent = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(data.totalAmount);
						document.querySelector('.prices-value:not(.prices-final)').textContent = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(data.totalAmount);
						document.querySelectorAll('#cart-quantity').forEach(el => el.textContent = data.cartItemCount);
						document.querySelector('#cart-item-count').textContent = data.cartItemCount;
						showModal('Thành công', data.message, true);
					} else {
						showModal('Lỗi', data.message || 'Không thể cập nhật số lượng.', false);
					}
				})
				.catch(error => {
					console.error('AJAX error for cartItemID: ' + cartItemID, error);
					showModal('Lỗi', 'Đã xảy ra lỗi khi cập nhật: ' + error.message, false);
				});
	}

	// Xóa sản phẩm
	document.querySelectorAll('.btn-remove').forEach(button => {
		button.addEventListener('click', debounce(function() {
			const row = this.closest('tr');
			const cartItemID = row.dataset.cartItemId;
			console.log('Sending request to remove cartItemID: ' + cartItemID);
			fetch(`${pageContext.request.contextPath}/remove-from-cart?cartItemID=${cartItemID}`, {
				method: 'GET',
				headers: { 'Content-Type': 'application/json' }
			})
					.then(response => {
						if (!response.ok) {
							throw new Error(`HTTP error! status: ${response.status}`);
						}
						return response.json();
					})
					.then(data => {
						if (!data || typeof data.status !== 'string') {
							throw new Error('Invalid JSON response');
						}
						if (data.status === 'success') {
							row.remove();
							document.querySelector('#cart-total').textContent = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(data.totalAmount);
							document.querySelector('.prices-value:not(.prices-final)').textContent = new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(data.totalAmount);
							document.querySelectorAll('#cart-quantity').forEach(el => el.textContent = data.cartItemCount);
							document.querySelector('#cart-item-count').textContent = data.cartItemCount;
							if (!document.querySelector('table tbody tr')) {
								document.querySelector('.cart-content').innerHTML = '<p>Giỏ hàng của bạn đang trống.</p>';
							}
							showModal('Thành công', data.message, true);
						} else {
							showModal('Lỗi', data.message || 'Không thể xóa sản phẩm.', false);
						}
					})
					.catch(error => {
						console.error('AJAX error for cartItemID: ' + cartItemID, error);
						showModal('Lỗi', 'Đã xảy ra lỗi khi xóa: ' + error.message, false);
					});
		}, 300));
	});

	// Tìm kiếm gợi ý
	const searchBox = document.getElementById("searchBox");
	const suggestionsList = document.getElementById("product-suggestions");
	searchBox.addEventListener("input", function () {
		const keyword = this.value.trim();
		if (keyword.length > 1) {
			fetch(`SearchServlet?ans=${keyword}`)
					.then(response => response.json())
					.then(data => {
						suggestionsList.innerHTML = "";
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
						suggestionsList.classList.add("active");
					})
					.catch(error => console.error('Search error:', error));
		} else {
			suggestionsList.innerHTML = "";
			suggestionsList.classList.remove("active");
		}
	});
	document.addEventListener("click", function (e) {
		if (!searchBox.contains(e.target) && !suggestionsList.contains(e.target)) {
			suggestionsList.innerHTML = "";
			suggestionsList.classList.remove("active");
		}
	});
</script>
</body>
</html>