<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="">
<!--<![endif]-->

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Thông tin sản phẩm</title>
  <meta name="description" content="Ela Admin - HTML5 Admin Template">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!---->
  <!--Font Awesome-->
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
  <!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Admin/assets/css/admin.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Admin/vendor/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/fontawesome-free-5.15.4-web/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/css_admin/admin1.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/css_admin/admin.css">


</head>

<body>
<!-- Left Panel -->

<!-- /#left-panel -->

<!-- Left Panel -->

<!-- Right Panel -->

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
          <a href="${pageContext.request.contextPath}/Employee/Employee.jsp" class="list-group-item list-group-item-action "> Trang chủ <i
                  class="menu-icon fa fa-laptop"></i></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/load-product-data" class="list-group-item list-group-item-action active "> Thông tin sản phẩm
            <i class="menu-icon fas fa-mobile-alt font-list"></i></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/load-recipt-data" class="list-group-item list-group-item-action "> Quản lí đơn hàng <i
                  class="menu-icon fas fa-shopping-cart font-list"></i></a>
        </li>
        <li>
          <a href="${pageContext.request.contextPath}/go-to-xu-li-don-hang?userID=${sessionScope.khachHang.userID}&page=1" class="list-group-item list-group-item-action "> Đơn hàng thành công <i
                  class="menu-icon fas fa-shopping-cart font-list"></i></a>
        </li>
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
      <div class="mb-5 mt-3 ">
        <div class="content">
          <div class="animated fadeIn">
            <div class="row">
              <div class="col-md-12">
                <div class="card">
                  <div class="card-body">
                    <div class=" mb-2">
                      <h4 class="text-center mt-3 mb-4">Danh sách sản phẩm</h4>
                      <div class="row">
                        <div class="show-page mb-3 ml-3">

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

                        <div class="show-page  arrange">

                          Sắp xếp <span> <select id="show" onclick="select_page()">
                                <option value="10">
                                  A-Z
                                </option>
                                <option value="20">
                                  Z-A
                                </option>
                              </select></span>
                        </div>
                        <div class="show-page  arrange">

                          Tìm kiếm <span> <input id="myInput" style="padding-left: 15px; border: 0.5px solid grey;"
                                                 text" placeholder="Search.."></span>
                        </div>

                      </div>
                      <a href="${pageContext.request.contextPath}/Admin/formAddProduct.jsp"><button data-toggle="tooltip" data-placement="top"
                                                                                                    title="Thêm sản phẩm" class="btn btn-success btn-add"><i
                              class="fas fa-plus-square"></i></button></a>
                      <table id="bootstrap-data-table" class="table table-hover table-text-center">
                        <thead class="thead-light">
                        <tr>
                          <th>Hình ảnh</th>
                          <th>Mã sản phẩm</th>
                          <th>Tên</th>
                          <th data-toggle="tooltip" data-placement="top" title="Hãng sản xuất">Hãng SX</th>
                          <th>Ngày ra mắt</th>
                          <th data-toggle="tooltip" data-placement="top" title="Số lượng còn lại">SL còn lại</th>
                          <th data-toggle="tooltip" data-placement="top" title="Số lượng đã bán">Màu sắc</th>
                          <th>Giá bán</th>
                          <th></th>

                        </tr>
                        </thead>

                        <tbody id="content-table">
                        <c:forEach var = "product" items = "${listProduct}">
                          <tr id="product-${product.productID}">
                            <td style="max-width: 140px;"><img src="${pageContext.request.contextPath}/imagesphone/${product.image}" width="100px"
                                                               height="100px" alt=""></td>
                            <td>${product.productID}</td>
                            <td>${product.name}</td>
                            <td>${product.categories.nameCategories}</td>
                            <td>${product.createAt}</td>
                            <td>${product.stockQuantity}</td>
                            <td>${product.informationPro.color}</td>
                            <td class="color-price">${product.price} VND</td>
                            <td class="row" style="border: none;">
                              <div style="margin: auto;">
                                <a href="edit-product?productID=${product.productID}"><button class="m-wTD btn btn-primary" data-toggle="tooltip"
                                                                                              data-placement="top" title="Chỉnh sửa" data-toggle="modal"
                                                                                              data-target="#editUser"> <i class="txt-center fas fa-edit"></i></button></a>
                                <a><button class="btn btn-danger sizeTh1 delete-product"
                                           data-toggle="tooltip" data-placement="top" title="Xóa" data-id="${product.productID}"><i
                                        class="txt-center menu-icon fas fa-trash-alt"></i></button></a>
                              </div>

                            </td>
                          </tr>
                        </c:forEach>
                        </tbody>
                      </table>

                      <div class="page-navigation">
                        <div class="beta"> <button onclick="previous_page()"> Trước</button>
                          <span id="page-number">

                            </span>
                          <button onclick="next_page()"> Sau</button>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div><!-- .animated -->
          </div><!-- .content -->
          <!--Modal add-->
          <!--/Modal add-->
          <!--Modal edit-->
          <!--/Modal edit-->
        </div><!-- /#right-panel -->
      </div>
    </div>
    <!-- /#page-content-wrapper -->
  </div>
  <!-- delete product -->
  <!-- Modal -->
  <!-- Right Panel -->
  <script src="${pageContext.request.contextPath}/Admin/vendor/jquery/jquery.min.js"></script>


  <script src="${pageContext.request.contextPath}/Admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <script src="${pageContext.request.contextPath}/js/js_admin/divide-page.js"></script>
  <script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function () {
      // Gắn sự kiện click cho các nút xóa
      document.querySelectorAll('.delete-product').forEach(button => {
        button.addEventListener('click', function () {
          const productId = this.getAttribute('data-id'); // Lấy ID sản phẩm
          console.log(productId);
          const row = document.getElementById(`product-`+productId); // Dòng sản phẩm cần xóa

          // Hiển thị hộp thoại xác nhận
          const isConfirmed = confirm("Bạn có chắc muốn xóa sản phẩm này không?");

          if (isConfirmed) { // Nếu chọn "Có"
            // Gửi AJAX request đến Servlet để xóa sản phẩm
            fetch(`deleteProductInList?productID=`+productId, {
              method: "GET"
            })
                    .then(response => response.json()) // Chuyển đổi kết quả thành JSON
                    .then(data => {
                      if (data.success) {
                        // Xóa dòng sản phẩm trên giao diện nếu xóa thành công
                        row.remove();
                        alert("Xóa sản phẩm thành công!");
                      } else {
                        alert("Xóa sản phẩm thất bại: " + data.message);
                      }
                    })
                    .catch(error => {
                      console.error("Error:", error);
                      alert("Đã xảy ra lỗi khi xóa sản phẩm.");
                    });
          } else {
            // Nếu chọn "Không", không làm gì cả
            console.log("Hủy xóa sản phẩm.");
          }
        });
      });
    });
  </script>
  <script>
    $("#menu-toggle").click(function (e) {
      e.preventDefault();
      $("#wrapper").toggleClass("toggled");
    });
  </script>

  <script type="text/javascript">
    $(document).ready(function () {
      $('#bootstrap-data-table-export').DataTable();
    });
  </script>


  <script>
    $(document).ready(function () {
      $('[data-toggle="tooltip"]').tooltip();
    });
  </script>

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



</body>

</html>