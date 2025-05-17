package com.projectttweb.webphone.controller;

import com.projectttweb.webphone.model.User;
import com.projectttweb.webphone.service.CartService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

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
            response.sendRedirect("noAccount.jsp");
            return;
        }

        User user = (User) session.getAttribute("khachHang");
        String productID = request.getParameter("productID");
        String uri = request.getParameter("uri");
        String thamSo = request.getParameter("thamSo");
        thamSo = (thamSo == null) ? "" : thamSo;

        try {
            if (productID == null || productID.trim().isEmpty()) {
                throw new IllegalArgumentException("Thiếu mã sản phẩm");
            }

            // Kiểm tra sản phẩm đã tồn tại trong giỏ hàng
            boolean productExists = cartService.getCartItems(user).stream()
                    .anyMatch(item -> item.getProduct().getProductID().equals(productID));

            String redirectUrl = request.getContextPath() + (uri != null && !uri.isEmpty() ? uri : "/LoadDataMain");
            if (!thamSo.isEmpty()) {
                redirectUrl += (redirectUrl.contains("?") ? "&" : "?") + thamSo;
            }

            if (productExists) {
                // Chuyển hướng với thông báo sản phẩm đã tồn tại
                redirectUrl += (redirectUrl.contains("?") ? "&" : "?") + "sourceServlet=addToCart";
                response.sendRedirect(redirectUrl);
                return;
            }

            // Thêm sản phẩm vào giỏ hàng
            cartService.addProductToCart(user, productID, 1);

            // Chuyển hướng với thông báo thành công
            redirectUrl += (redirectUrl.contains("?") ? "&" : "?") + "sourceServlet=success";
            response.sendRedirect(redirectUrl);

        } catch (Exception e) {
            e.printStackTrace();
            String redirectUrl = request.getContextPath() + (uri != null && !uri.isEmpty() ? uri : "/LoadDataMain");
            redirectUrl += (redirectUrl.contains("?") ? "&" : "?") + "error=addToCartFailed";
            response.sendRedirect(redirectUrl);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        HttpSession session = request.getSession(false);
        try {
            if (session == null || session.getAttribute("khachHang") == null) {
                out.print(gson.toJson(new Response("error", "Vui lòng đăng nhập để thêm sản phẩm")));
                return;
            }

            User user = (User) session.getAttribute("khachHang");
            String productID = request.getParameter("productID");

            if (productID == null || productID.trim().isEmpty()) {
                out.print(gson.toJson(new Response("error", "Thiếu mã sản phẩm")));
                return;
            }

            // Kiểm tra sản phẩm đã tồn tại trong giỏ hàng
            boolean productExists = cartService.getCartItems(user).stream()
                    .anyMatch(item -> item.getProduct().getProductID().equals(productID));

            if (productExists) {
                out.print(gson.toJson(new Response("error", "Sản phẩm đã tồn tại trong giỏ hàng")));
                return;
            }

            // Thêm sản phẩm vào giỏ hàng
            cartService.addProductToCart(user, productID, 1);
            int cartItemCount = cartService.getCartItems(user).size();

            out.print(gson.toJson(new Response("success", "Thêm sản phẩm thành công", cartItemCount)));
        } catch (Exception e) {
            e.printStackTrace();
            out.print(gson.toJson(new Response("error", "Lỗi khi thêm sản phẩm: " + e.getMessage())));
        } finally {
            out.flush();
        }
    }

    // Lớp để định dạng phản hồi JSON
    private static class Response {
        String status;
        String message;
        int cartItemCount;

        Response(String status, String message) {
            this.status = status;
            this.message = message;
        }

        Response(String status, String message, int cartItemCount) {
            this.status = status;
            this.message = message;
            this.cartItemCount = cartItemCount;
        }
    }
}