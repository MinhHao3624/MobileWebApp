package com.projectttweb.webphone.controller;

import com.projectttweb.webphone.database.*;
import com.projectttweb.webphone.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.Map;

@WebServlet("/Payment-success-VNPay")
public class PaymentSuccessVnPay extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentSuccessVnPay() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        try {
            HttpSession session = request.getSession(false);
            OrdersDAO ordersDAO = new OrdersDAO();
            OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
            Date todaysDate = new Date(new java.util.Date().getTime());
            User user = (User) session.getAttribute("khachHang");
            ListOrderDetails listOrderDetails = (ListOrderDetails) session.getAttribute("cart");
            ListOrderDetailsItem listOrderDetailsItem = (ListOrderDetailsItem) session.getAttribute("listItem");
            ProductDao productDao = new ProductDao();
            ThanhToanVNPay.SaveInfoApi saveInfoApi = (ThanhToanVNPay.SaveInfoApi) session.getAttribute("saveInfoApi");
            for (Map.Entry<Orders, ListOrderDetailsItem> m2 : listOrderDetails.getMap().entrySet()) {
                Orders order = m2.getKey();
                double totalAmount = listOrderDetailsItem.getTotalAmount();
                if(ordersDAO.insertOrderInDB2(order, totalAmount, saveInfoApi) > 0) {
                    aa:	for (OrderDetails orderDetails: m2.getValue().getList()) {
                        if(orderDetailsDAO.insertOrderDetailsInDB(orderDetails) > 0) {
                            if(productDao.capNhatStockQuantity(orderDetails.getProduct().getProductID(), xuLyConLai(orderDetails.getProduct().getProductID(), orderDetails.getQuantity())) > 0) {
                                continue aa;
                            }
                        }
                    }
                }
                break;
            }
            HistoryVNPayDAO historyVNPayDAO = new HistoryVNPayDAO();
            String iD2 = historyVNPayDAO.selectIDCur();
            String iD = xuLy(iD2);
            String vnp_Amount = request.getParameter("vnp_Amount");
            String vnp_BankCode = request.getParameter("vnp_BankCode");
            String vnp_BankTranNo = request.getParameter("vnp_BankTranNo");
            String vnp_CardType = request.getParameter("vnp_CardType");
            String vnp_OrderInfo = request.getParameter("vnp_OrderInfo");
            String vnp_PayDate = request.getParameter("vnp_PayDate");
            String vnp_ResponseCode = request.getParameter("vnp_ResponseCode");
            HistoryVNPay historyVNPay = new HistoryVNPay(iD, vnp_Amount,vnp_BankCode, vnp_BankTranNo, vnp_CardType, vnp_OrderInfo, vnp_PayDate, vnp_ResponseCode, saveInfoApi.getOrderID());
            if(historyVNPayDAO.insertNew(historyVNPay) > 0) {
                session.removeAttribute("cart");
                session.removeAttribute("listItem");
                session.removeAttribute("saveInfoApi");
                request.setAttribute("id", iD);
                request.setAttribute("vnp_Amount", vnp_Amount);
                request.setAttribute("vnp_BankCode", vnp_BankCode);
                request.setAttribute("vnp_BankTranNo", vnp_BankTranNo);
                request.setAttribute("vnp_CardType", vnp_CardType);
                request.setAttribute("vnp_OrderInfo", vnp_OrderInfo);
                request.setAttribute("vnp_PayDate", vnp_PayDate);
                request.setAttribute("vnp_ResponseCode", vnp_ResponseCode);
                request.setAttribute("orderID", saveInfoApi.getOrderID());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/resultvnpay.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    private int xuLyConLai(String productID, int quantity) {
        // TODO Auto-generated method stub
        ProductDao proDao = new ProductDao();
        Product pro = proDao.selectProByID2(productID);
        int res = pro.getStockQuantity() - quantity;
        return res;
    }
    private String xuLy(String orderIDCurrent) {
        // TODO Auto-generated method stub
        String ans = "";
        boolean mo = false;
        for (int i = 0; i < orderIDCurrent.length(); i++) {
            if (orderIDCurrent.charAt(i) != '0' || mo) {
                ans += orderIDCurrent.charAt(i);
                mo = true;
            }
        }
        //	System.out.println(ans+"số là số");
        int ansInt = Integer.parseInt(ans);
        //	System.out.println(ansInt+"labubu");
        int ansIntNext = ansInt + 1;
        String result = "";
        if (ansIntNext < 10) {
            result = "000" + ansIntNext;
        } else if (ansIntNext < 100) {
            result = "00" + ansIntNext;
        } else if (ansIntNext < 1000) {
            result = "0" + ansIntNext;
        } else {
            result = String.valueOf(ansIntNext);
        }
        return result;
    }

}
