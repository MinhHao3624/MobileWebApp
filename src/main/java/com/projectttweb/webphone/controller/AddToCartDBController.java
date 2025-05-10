package com.projectttweb.webphone.controller;

import com.projectttweb.webphone.model.User;
import com.projectttweb.webphone.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/add-to-cart")
public class AddToCartDBController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = new CartService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("khachHang") == null) {
            // Chưa đăng nhập ⇒ chuyển hướng tới trang thông báo
            response.sendRedirect("noAccount.jsp");
            return;
        }

        User user = (User) session.getAttribute("khachHang");
        String productID = request.getParameter("productID");
        String uri = request.getParameter("uri");        // URI để redirect lại
        String thamSo = request.getParameter("thamSo");  // Tham số phụ nếu có
        thamSo = (thamSo == null) ? "" : thamSo;

        try {
            if (productID == null || productID.trim().isEmpty()) {
                throw new IllegalArgumentException("Thiếu mã sản phẩm");
            }

            cartService.addProductToCart(user, productID, 1); // Mặc định thêm 1 sản phẩm

            // Redirect về trang cũ sau khi thêm
            String redirectUrl = request.getContextPath() + uri;
            if (!thamSo.isEmpty()) {
                redirectUrl += "?" + thamSo;
            }
            response.sendRedirect(redirectUrl);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "Lỗi khi thêm sản phẩm vào giỏ hàng.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Hỗ trợ cả GET và POST
    }
}
