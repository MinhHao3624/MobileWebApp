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
          <a href="${pageContext.request.contextPath}/load-data-order-in-month" class="list-group-item list-group-item-action"> Đơn
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

    <div class="container-fluid">
      <!-- <div class="mb-5 mt-3 ">
        <h4 class=" mt-3 mb-3">Bảng tính tổng</h4>

        <div class="summary-content">
          <div class="summary-item">
            <span class="name-description">Tổng doanh thu</span>
            <h3 class="value">50.000.000</h3>
          </div>
          <div class="summary-item">
            <span class="name-description">Số đơn hàng</span>
            <h3 class="value">120</h3>
          </div>
          <div class="summary-item">
            <span class="name-description">Sản phẩm đã bán</span>
            <h3 class="value">135</h3>
          </div>
          <div class="summary-item">
            <span class="name-description">Sản phẩm tồn kho</span>
            <h3 class="value">500</h3>
          </div>
          <div class="summary-item">
            <span class="name-description">Lượt truy cập</span>
            <h3 class="value">1200</h3>
          </div>
        </div>

        <table class="table table-text-center table-summary">
          <thead class="thead-dark">
            <tr>
              <th scope="col">Tổng doanh thu</th>
              <th scope="col">Số đơn hàng</th>
              <th scope="col">Sản phẩm đã bán</th>
              <th scope="col">Sản phẩm tồn kho</th>
              <th scope="col">Lượt truy cập</th>
            </tr>
          </thead>
          <tbody>
            <tr class="">
              <td scope="row">100,000,000</td>
              <td>100</td>
              <td>123</td>
              <td>100</td>
              <td>17080</td>
            </tr>

          </tbody>
        </table>
      </div> -->
      <div class="border mb-2">
        <h4 class="text-center mt-3 mb-4">Bảng thống kê bán hàng</h4>
        <div class="row">
          <div class="show-page mb-3 ml-3" style="padding-left: 10px;">
            Hiển thị <span> <select id="show" onclick="select_page()">
                  <option value="10">
                    10
                  </option>
                  <option value="20">
                    20
                  </option>
                  <option value="50">
                    50
                  </option>
                </select></span> cột
          </div>
          <div class="show-page " style="margin-left: 50px;">

            Tìm kiếm <span> <input id="myInput" style="padding-left: 15px; border: 0.5px solid grey;" type="text"
                                   placeholder="Search.."></span>
          </div>
        </div>
        <table class="table table-hover table-text-center">
          <thead class="thead-light">
          <tr>
            <th scope="col">Thời gian</th>
            <th scope="col">Doanh thu</th>
            <th scope="col" data-toggle="tooltip" data-placement="top" title="Số lượng sản phẩm được thêm"><span
                    title="Số lượng sản phẩm"> SLSP</span> bán được</th>
            <th scope="col" data-toggle="tooltip" data-placement="top" title="Số lượng sản phẩm tồn kho"><span
                    title="Số lượng sản phẩm"> SLSP</span> được thêm</th>
            <th scope="col">Số lượng tồn kho</th>
            <th scope="col">Số lượng truy cập</th>
          </tr>
          </thead>
          <tbody id="content-table">
          <tr>
            <th scope="row">9/2020</th>
            <td>30,000,00</td>
            <td>4</td>
            <td>2</td>
            <td>42</td>
            <td>400</td>
          </tr>
          <tr>
            <th scope="row">8/2020</th>
            <td>24,000,00</td>
            <td>3</td>
            <td>4</td>
            <td>46</td>
            <td>200</td>
          </tr>
          <tr>
            <th scope="row">7/2020</th>
            <td>12,000,00</td>
            <td>2</td>
            <td>5</td>
            <td>49</td>
            <td>502</td>
          </tr>
          <tr>
            <th scope="row">6/2020</th>
            <td>22,000,00</td>
            <td>7</td>
            <td>2</td>
            <td>51</td>
            <td>420</td>
          </tr>
          <tr>
            <th scope="row">5/2020</th>
            <td>30,000,00</td>
            <td>5</td>
            <td>8</td>
            <td>58</td>
            <td>492</td>
          </tr>
          <tr>
            <th scope="row">4/2020</th>
            <td>25,000,00</td>
            <td>2</td>
            <td>4</td>
            <td>63</td>
            <td>100</td>
          </tr>
          <tr>
            <th scope="row">4/2020</th>
            <td>25,000,00</td>
            <td>2</td>
            <td>4</td>
            <td>63</td>
            <td>100</td>
          </tr>
          <tr>
            <th scope="row">4/2020</th>
            <td>25,000,00</td>
            <td>2</td>
            <td>4</td>
            <td>63</td>
            <td>100</td>
          </tr>
          <tr>
            <th scope="row">4/2020</th>
            <td>25,000,00</td>
            <td>2</td>
            <td>4</td>
            <td>63</td>
            <td>100</td>
          </tr>
          <tr>
            <th scope="row">4/2020</th>
            <td>25,000,00</td>
            <td>2</td>
            <td>4</td>
            <td>63</td>
            <td>100</td>
          </tr>
          <tr>
            <th scope="row">4/2020</th>
            <td>25,000,00</td>
            <td>2</td>
            <td>4</td>
            <td>63</td>
            <td>100</td>
          </tr>
          <tr>
            <th scope="row">4/2020</th>
            <td>25,000,00</td>
            <td>2</td>
            <td>4</td>
            <td>63</td>
            <td>100</td>
          </tr>
          <tr>
            <th scope="row">4/2020</th>
            <td>25,000,00</td>
            <td>2</td>
            <td>4</td>
            <td>63</td>
            <td>100</td>
          </tr>
          <tr>
            <th scope="row">4/2020</th>
            <td>25,000,00</td>
            <td>2</td>
            <td>4</td>
            <td>63</td>
            <td>100</td>
          </tr>
          <tr>
            <th scope="row">4/2020</th>
            <td>25,000,00</td>
            <td>2</td>
            <td>4</td>
            <td>63</td>
            <td>100</td>
          </tr>
          <tr>
            <th scope="row">4/2020</th>
            <td>25,000,00</td>
            <td>2</td>
            <td>4</td>
            <td>63</td>
            <td>100</td>
          </tr>
          <tr>
            <th scope="row">4/2020</th>
            <td>25,000,00</td>
            <td>2</td>
            <td>4</td>
            <td>63</td>
            <td>100</td>
          </tr>
          <tr>
            <th scope="row">4/2020</th>
            <td>25,000,00</td>
            <td>2</td>
            <td>4</td>
            <td>63</td>
            <td>100</td>
          </tr>



          </tbody>
        </table>

      </div>
      <div class="page-navigation">
        <div class="beta"> <button onclick="previous_page()"> Trước</button>
          <span id="page-number">

            </span>
          <button onclick="next_page()"> Sau</button>
        </div>
      </div>
      <!-- <table class="table">
        <thead class="thead-dark">
          <tr>
            <th scope="col">#</th>
            <th scope="col">First</th>
            <th scope="col">Last</th>
            <th scope="col">Handle</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th scope="row">1</th>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
          </tr>
          <tr>
            <th scope="row">2</th>
            <td>Jacob</td>
            <td>Thornton</td>
            <td>@fat</td>
          </tr>
          <tr>
            <th scope="row">3</th>
            <td>Larry</td>
            <td>the Bird</td>
            <td>@twitter</td>
          </tr>
        </tbody>
      </table> -->

    </div>
  </div>
  <!-- /#page-content-wrapper -->
</div>
<!-- /#wrapper -->

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