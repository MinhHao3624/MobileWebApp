<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />

  <title>Admin - Mobile Shop</title>

  <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath}/Admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
  <link href="${pageContext.request.contextPath}/css/all.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/fontawesome-free-5.15.4-web/css/all.min.css">
  <link href="${pageContext.request.contextPath}/css/css_admin/admin.css" rel="stylesheet" />
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
        integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
  <style>
    /* CSS dành riêng cho giao diện Doanh thu */
    .revenue-report {
      font-family: 'Segoe UI', sans-serif;
      padding: 30px;
      background-color: #f9f9f9;
    }

    .revenue-report .title {
      font-size: 28px;
      color: #2c3e50;
      margin-bottom: 20px;
    }

    .revenue-report .summary {
      display: flex;
      gap: 40px;
      margin-bottom: 30px;
    }

    .revenue-report .label {
      font-size: 18px;
      color: #34495e;
    }

    .revenue-report .value {
      font-weight: bold;
      color: #27ae60;
    }

    .revenue-report .tables-container {
      display: flex;
      gap: 20px;
      flex-wrap: wrap;
    }

    .revenue-report .table-wrapper {
      flex: 1;
      min-width: 400px;
      max-width: 100%;
    }

    .revenue-report .table-label {
      font-size: 18px;
      font-weight: bold;
      margin-bottom: 10px;
      color: #2980b9;
    }

    .revenue-report .scroll-table {
      border: 1px solid #ccc;
      max-height: 300px;
      overflow: auto;
      background-color: #fff;
      border-radius: 8px;
    }

    .revenue-report table {
      width: 100%;
      border-collapse: collapse;
      min-width: 600px; /* Đảm bảo có scroll ngang */
    }

    .revenue-report th, .revenue-report td {
      padding: 8px 12px;
      border: 1px solid #ddd;
      text-align: left;
      font-size: 14px;
    }

    .revenue-report th {
      background-color: #ecf0f1;
      position: sticky;
      top: 0;
      z-index: 1;
    }

  </style>
</head>

<body>
<div class="d-flex" id="wrapper">
  <!-- Sidebar -->

  <!--Nav-->
  <div class="side-bar bg-light border-right" id="sidebar-wrapper">
    <div class="sidebar-heading text-center"><b>Mobile Shop</b></div>
    <div class="profile">
      <div class="profile-pic">
        <img src="${pageContext.request.contextPath}/Admin/images/admin.jpg" alt="">
      </div>
      <div class="profile-info">
        <span>Welcome</span>
        <h5>Administrator</h5>
      </div>
    </div>
    <div class="list-group list-group-flush">
      <ul>
        <li>
          <a href="${pageContext.request.contextPath}/Admin/admin.jsp" class="list-group-item list-group-item-action  active"> Trang chủ <i
                  class="menu-icon fa fa-laptop"></i></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/load-user-data" class="list-group-item list-group-item-action   ">
            Thông tin người dùng<i class="menu-icon fas fa-users font-list"></i></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/load-customer-data" class="list-group-item list-group-item-action   ">
            Thông tin nhân viên<i class="menu-icon fas fa-users font-list"></i></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/load-product-data" class="list-group-item list-group-item-action  "> Thông tin sản phẩm
            <i class="menu-icon fas fa-mobile-alt font-list"></i></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/load-recipt-data" class="list-group-item list-group-item-action "> Quản lí đơn hàng <i
                  class="menu-icon fas fa-shopping-cart font-list"></i></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/load-product-categories" class="list-group-item list-group-item-action "> Thông
            tin
            thương hiệu <i class="menu-icon fas fa-archway"></i></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/load-cancel-receipt" class="list-group-item list-group-item-action"> Đơn
            hàng bị hủy <i class="menu-icon fas fa-phone-slash"></i></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/load-cancel-receipt" class="list-group-item list-group-item-action"> Đơn
            hàng thành công <i class="menu-icon fas fa-phone-slash"></i></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/load-feedback" class="list-group-item list-group-item-action"> Phản hồi khách hàng <i
                  class="menu-icon far fa-paper-plane"></i></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/load-log" class="list-group-item list-group-item-action"> Xem Log <i
                  class="menu-icon far fa-paper-plane"></i></a>
        </li>
        <!-- <li>
          <a href="admin-filter.html" class="list-group-item list-group-item-action "> Dữ liệu lọc <i
              class="menu-icon fas fa-filter"></i></a>
        </li> -->
      </ul>
    </div>
  </div>
  <!--/Nav-->
  <!-- /#sidebar-wrapper -->

  <!-- Page Content -->
  <div id="page-content-wrapper">
    <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
      <button class="btn btn-primary" id="menu-toggle">
        <i class="fas fa-bars"></i>
      </button>
      <div class="">

      </div>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
          <li class="nav-item active">
            <a class="nav-link" href="#" data-toggle="modal" data-target="#log-out">Đăng xuất <span class="log-out"><i
                    class="fas fa-arrow-right"></i></span></a>
          </li>
          <!-- <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
              aria-haspopup="true" aria-expanded="false">
              Dropdown
            </a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
              <a class="dropdown-item" href="#">Action</a>
              <a class="dropdown-item" href="#">Another action</a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#">Something else here</a>
            </div>
          </li> -->
        </ul>
        <div class="modal fade" id="log-out" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
             aria-hidden="true">
          <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Xác nhận đăng xuất</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                Bạn có muốn đăng xuất?
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                <a href="dang-xuat"><button type="button" class="btn btn-primary">Đăng xuất</button></a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </nav>

    <div class="revenue-report">
      <h1 class="title">Doanh thu tháng ${Month} năm ${Year}</h1>

      <div class="summary">
        <div class="label">Tổng doanh thu: <span class="value"><fmt:formatNumber value="${price}" type="number" groupingUsed="true"/> VNĐ</span></div>
        <div class="label">Lợi nhuận: <span class="value"><fmt:formatNumber value="${loiNhuanReal}" type="number" groupingUsed="true"/> VNĐ</span></div>
      </div>

      <div class="tables-container">
        <div class="table-wrapper">
          <div class="table-label">Đơn hàng đã duyệt</div>
          <div class="scroll-table">
            <table>
              <thead>
              <tr>
                <th>OrderID</th>
                <th>OrderDate</th>
                <th>UserID</th>
                <th>Status</th>
                <th>TotalAmount</th>
                <th>Address</th>
                <th>IdNV</th>
                <th>StatisticalDay</th>
              </tr>
              </thead>
              <tbody>
              <!-- Dữ liệu mẫu -->
              <c:forEach var = "orders" items="${lstOrder}">
              <tr>
                <td>${orders.orderID}</td>
                <td>${orders.ordersDate}</td>
                <td>${orders.user.userID}</td>
                <td>${orders.status}</td>
                <td><fmt:formatNumber value="${orders.totalAmount}" type="number" groupingUsed="true"/> VND</td>
                <td>${orders.shippingAddress}</td>
                <td>${orders.idNV}</td>
                <td>${orders.dateTK}</td>
              </tr>
              </c:forEach>
              <!-- Thêm nhiều dòng ở đây -->
              </tbody>
            </table>
          </div>
        </div>

        <div class="table-wrapper">
          <div class="table-label">Nhân viên chăm chỉ</div>
          <div class="scroll-table">
            <table>
              <thead>
              <tr>
                <th>IdNV</th>
                <th>ApprovedAppNum</th>
              </tr>
              </thead>
              <tbody>
              <!-- Dữ liệu mẫu -->
              <c:forEach var="string" items="${lstNVCham}">
              <tr>
                <td>${string.idNV}</td>
                <td>${string.soLan}</td>
              </tr>
              </c:forEach>
              <!-- Thêm nhiều dòng ở đây -->
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}/Admin/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/Admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Menu Toggle Script -->
<script>
  $("#menu-toggle").click(function (e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
  });

</script>
<script src="${pageContext.request.contextPath}/js/js_admin/divide-page.js"></script>

<!-- search -->
<script>
  $(document).ready(function () {
    $("#myInput").on("keyup", function () {
      var value = $(this).val().toLowerCase();
      $("#content-table tr").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
  });
</script>

<script>
  $(document).ready(function () {
    $('[data-toggle="tooltip"]').tooltip();
  });
</script>

</body>

</html>