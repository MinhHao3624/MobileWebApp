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

  <title>Employee - Mobile Shop</title>

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
        <h5>Employee</h5>
      </div>
    </div>
    <div class="list-group list-group-flush">
      <ul>
        <li>
          <a href="${pageContext.request.contextPath}/Employee/Employee.jsp" class="list-group-item list-group-item-action  active"> Trang chủ <i
                  class="menu-icon fa fa-laptop"></i></a>
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
          <a href="${pageContext.request.contextPath}/go-to-xu-li-don-hang?userID=${sessionScope.khachHang.userID}&page=1" class="list-group-item list-group-item-action "> Đơn hàng thành công<i
                  class="menu-icon fas fa-shopping-cart font-list"></i></a>
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

    <div class="order-stats-wrapper">
      <h2>Đơn hàng đặt trong tháng</h2>

      <div class="order-labels">
        <div class="order-label">
          <h4>Số tiền trong tháng</h4>
          <p><fmt:formatNumber value="${soTien1}" type="number" groupingUsed="true"/></p>
        </div>
        <div class="order-label">
          <h4>Số đơn hàng duyệt</h4>
          <p>${numOrders}</p>
        </div>
      </div>

      <table class="order-table">
        <thead>
        <tr>
          <th>Mã đơn hàng</th>
          <th>Tên khách hàng</th>
          <th>Ngày đặt hàng</th>
          <th>Tổng tiền</th>
          <th>Tình trạng đơn hàng</th>
          <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${listOrders}">
          <tr>
            <td>${item.orderID}</td>
            <td>${item.user.userName}</td>
            <td>${item.ordersDate}</td>
            <td><fmt:formatNumber value="${item.totalAmount}" type="number" groupingUsed="true"/>
              VND
            </td>
            <td>${item.status}</td>
            <td>
              <c:choose>
                <c:when test="${item.isCheck == 1}">
                  <button class="action-button" style="background-color: red; color: white;"
                          disabled>Đã thống kê
                  </button>
                </c:when>
                <c:otherwise>
                  <a href="thong-Ke-Don-Hang?userID=${sessionScope.employee.userID}&orderID=${item.orderID}&page=${currentPage}">
                    <button type="submit" class="action-button">Thống kê</button>
                  </a>
                </c:otherwise>
              </c:choose>
            </td>
          </tr>
        </c:forEach>
        </tbody>
        <button type="button" class="action-button" style="background-color: #007bff; color: white;"
                onclick="xemLichSuThongKe(${sessionScope.employee.userID})">
          Xem lịch sử thống kê
        </button>
      </table>
      <div class="pagination">
        <a
                href="go-to-xu-li-don-hang?userID=${sessionScope.employee.userID}&page=${currentPage == 1 ? tongSoTrang : currentPage - 1}">Trước</a>

        <c:forEach var="i" begin="1" end="${tongSoTrang}">
          <a
                  href="go-to-xu-li-don-hang?userID=${sessionScope.employee.userID}&page=${i}"
                  class="${i == currentPage ? 'active' : ''}">${i}</a>
        </c:forEach>
        <a
                href="go-to-xu-li-don-hang?userID=${sessionScope.employee.userID}&page=${currentPage == tongSoTrang ? 1 : currentPage + 1}">Sau</a>
      </div>
    </div>
  </div>
</div>
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
          <th>idNV</th>
          <th>Ngày thống kê</th>
        </tr>
        </thead>
        <tbody id="lichSuBody">
        <!-- Dữ liệu sẽ được thêm ở đây bằng JavaScript -->
        </tbody>
      </table>
    </div>
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


  <!-- /#page-content-wrapper -->

<!-- /#wrapper -->

<!-- Bootstrap core JavaScript -->
<script src="${pageContext.request.contextPath}/Admin/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/Admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Menu Toggle Script -->
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
                var idNV = order.idNV;
                var dateTK = order.dateTK;
                row.innerHTML =
                        "<td>" + oi + "</td>" +
                        "<td>" + name + "</td>" +
                        "<td>" + date + "</td>" +
                        "<td>" + price + " VND</td>" +
                        "<td>" + status + "</td>" +
                        "<td>" + isCheck + "</td>" +
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