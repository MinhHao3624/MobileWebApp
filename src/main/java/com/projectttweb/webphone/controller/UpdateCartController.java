package com.projectttweb.webphone.controller;

import com.projectttweb.webphone.model.User;
import com.projectttweb.webphone.service.CartService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update-cart")
public class UpdateCartController extends HttpServlet {
    private CartService cartService;

    @Override
    public void init() throws ServletException {
        cartService = new CartService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        HttpSession session = request.getSession(false);
        try {
            if (session == null || session.getAttribute("khachHang") == null) {
                out.print(gson.toJson(new Response("error", "Vui lòng đăng nhập")));
                return;
            }

            User user = (User) session.getAttribute("khachHang");
            String cartItemID = request.getParameter("cartItemID");
            String soLuongStr = request.getParameter("soLuong");
            int soLuong = Integer.parseInt(soLuongStr);

            if (cartItemID == null || soLuong <= 0) {
                out.print(gson.toJson(new Response("error", "Dữ liệu không hợp lệ")));
                return;
            }

            // Cập nhật số lượng trong giỏ hàng
            cartService.updateCartItem(user, cartItemID, soLuong);
            int cartItemCount = cartService.getCartItems(user).size();
            double totalAmount = cartService.calculateTotal(user);
            double itemTotal = cartService.getItemTotal(cartItemID);

            out.print(gson.toJson(new Response("success", "Cập nhật thành công", cartItemCount, totalAmount, itemTotal)));
        } catch (Exception e) {
            e.printStackTrace();
            out.print(gson.toJson(new Response("error", "Lỗi khi cập nhật: " + e.getMessage())));
        } finally {
            out.flush();
        }
    }

    private static class Response {
        String status;
        String message;
        int cartItemCount;
        double totalAmount;
        double itemTotal;

        Response(String status, String message) {
            this.status = status;
            this.message = message;
        }

        Response(String status, String message, int cartItemCount, double totalAmount, double itemTotal) {
            this.status = status;
            this.message = message;
            this.cartItemCount = cartItemCount;
            this.totalAmount = totalAmount;
            this.itemTotal = itemTotal;
        }
    }
}