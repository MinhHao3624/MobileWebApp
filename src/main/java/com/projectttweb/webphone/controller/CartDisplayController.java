package com.projectttweb.webphone.controller;

import com.projectttweb.webphone.model.User;
import com.projectttweb.webphone.model.CartItem;
import com.projectttweb.webphone.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/view-cart")
public class CartDisplayController extends HttpServlet {
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
        ArrayList<CartItem> cartItems = cartService.getCartItems(user);

        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("/cart.jsp").forward(request, response);
    }
}
