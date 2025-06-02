<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Kết quả thanh toán qua VNPAY</title>
  <style>
    body {
      font-family: 'Segoe UI', sans-serif;
      background: linear-gradient(to right, #e0f7fa, #ffffff);
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 40px;
      margin: 0;
    }
    h1 {
      color: #00796b;
      margin-bottom: 20px;
    }
    .logo {
      width: 150px;
      margin-bottom: 30px;
    }
    .result-box {
      background: #ffffff;
      box-shadow: 0 4px 10px rgba(0,0,0,0.1);
      border-radius: 12px;
      padding: 30px 40px;
      max-width: 500px;
      width: 100%;
    }
    .info-row {
      display: flex;
      justify-content: space-between;
      margin-bottom: 15px;
      border-bottom: 1px solid #eee;
      padding-bottom: 5px;
    }
    .label {
      font-weight: bold;
      color: #444;
    }
    .value {
      color: #333;
    }
    .status-success {
      color: #2e7d32;
      font-weight: bold;
    }
    .status-failed {
      color: #c62828;
      font-weight: bold;
    }
    .home-button {
      margin-top: 30px;
      text-align: center;
    }
    .home-button a {
      display: inline-block;
      background-color: #00796b;
      color: white;
      text-decoration: none;
      padding: 12px 24px;
      border-radius: 8px;
      transition: background-color 0.3s;
    }
    .home-button a:hover {
      background-color: #004d40;
    }
  </style>
</head>
<body>

<h1>Kết quả thanh toán qua cổng VNPAY</h1>
<img src="images/vnpay_logo.png" alt="VNPAY Logo" class="logo">

<div class="result-box">
  <div class="info-row">
    <div class="label">Mã đơn hàng (ID):</div>
    <div class="value">${id}</div>
  </div>
  <div class="info-row">
    <div class="label">Số tiền:</div>
    <div class="value"><fmt:formatNumber
            value="${vnp_Amount}"
            type="currency"/> VNĐ</div>
  </div>
  <div class="info-row">
    <div class="label">Ngân hàng:</div>
    <div class="value">${vnp_BankCode}</div>
  </div>
  <div class="info-row">
    <div class="label">Mã giao dịch NH:</div>
    <div class="value">${vnp_BankTranNo}</div>
  </div>
  <div class="info-row">
    <div class="label">Loại thẻ:</div>
    <div class="value">${vnp_CardType}</div>
  </div>
  <div class="info-row">
    <div class="label">Thông tin đơn hàng:</div>
    <div class="value">${vnp_OrderInfo}</div>
  </div>
  <div class="info-row">
    <div class="label">Thời gian thanh toán:</div>
    <div class="value">${vnp_PayDate}</div>
  </div>
  <div class="info-row">
    <div class="label">Mã phản hồi:</div>
    <div class="value">${vnp_ResponseCode}</div>
  </div>
  <div class="info-row">
    <div class="label">Đơn hàng</div>
    <div class="value">${orderID}</div>
  </div>
</div>
<div class="home-button">
  <a href="LoadDataMain">← Quay về trang chủ</a>
</div>

</body>
</html>
