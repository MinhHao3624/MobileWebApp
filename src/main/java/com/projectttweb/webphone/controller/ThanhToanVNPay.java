package com.projectttweb.webphone.controller;

import com.projectttweb.webphone.vnpay.PaymentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ThanhToanVNPAY")
public class ThanhToanVNPay extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String totla = req.getParameter("amount");
        Double amount = Double.parseDouble(totla);
        PaymentService paymentService = new PaymentService();
        String url = paymentService.createVNPPayment(amount);
        resp.sendRedirect(url);
    }
}
