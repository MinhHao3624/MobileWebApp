package com.projectttweb.webphone.controller;

import com.projectttweb.webphone.model.User;
import com.projectttweb.webphone.model.CartItem;
import com.projectttweb.webphone.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/delete-orderDetails-in-cart")
public class DeleteOrderDetailsControl extends HttpServlet {
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

		String cartItemID = request.getParameter("cartItemID");
		if (cartItemID != null && !cartItemID.trim().isEmpty()) {
			CartItem item = new CartItem();
			item.setCartItemID(cartItemID);
			cartService.deleteCartItem(item);
		}

		response.sendRedirect("go-to-cart");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
