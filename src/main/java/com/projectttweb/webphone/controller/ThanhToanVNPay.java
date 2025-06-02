package com.projectttweb.webphone.controller;

import com.projectttweb.webphone.model.User;
import com.projectttweb.webphone.vnpay.PaymentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ThanhToanVNPAY")
public class ThanhToanVNPay extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String totla = req.getParameter("amount");
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("khachHang");
        String orderID = req.getParameter("orderID");

        //
        String tinh = req.getParameter("province");
        String huyen = req.getParameter("dictrict");
        String xa = req.getParameter("commue");
        String fee = req.getParameter("shipfee");
        Double amount = Double.parseDouble(totla) + Double.parseDouble(fee);
        SaveInfoApi saveInfoApi = new SaveInfoApi(tinh, huyen, xa, fee, totla, orderID);
        session.setAttribute("saveInfoApi", saveInfoApi);
        PaymentService paymentService = new PaymentService();
        String url = paymentService.createVNPPayment(amount);
        System.out.println(url + "   URL");
        System.out.println(user.getUserID());
        System.out.println(orderID);
        System.out.println(tinh);
        System.out.println(huyen);
        System.out.println(xa);
        System.out.println(fee);
        resp.sendRedirect(url);
    }
    public class SaveInfoApi {
        String tinh;
        String huyen;
        String xa;
        String fee;
        String totalAmount;
        String orderID;

        public SaveInfoApi(String tinh, String huyen, String xa, String fee, String totalAmount, String orderID) {
            this.tinh = tinh;
            this.huyen = huyen;
            this.xa = xa;
            this.fee = fee;
            this.totalAmount = totalAmount;
            this.orderID = orderID;
        }

        public String getTinh() {
            return tinh;
        }

        public void setTinh(String tinh) {
            this.tinh = tinh;
        }

        public String getHuyen() {
            return huyen;
        }

        public void setHuyen(String huyen) {
            this.huyen = huyen;
        }

        public String getXa() {
            return xa;
        }

        public void setXa(String xa) {
            this.xa = xa;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getOrderID() {
            return orderID;
        }

        public void setOrderID(String orderID) {
            this.orderID = orderID;
        }
    }
}
