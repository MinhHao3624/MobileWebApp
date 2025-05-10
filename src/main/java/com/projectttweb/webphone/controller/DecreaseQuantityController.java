package com.projectttweb.webphone.controller;

import com.projectttweb.webphone.model.User;
import com.projectttweb.webphone.model.CartItem;
import com.projectttweb.webphone.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/giam-so-luong")
public class DecreaseQuantityController extends HttpServlet {
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

		String productID = request.getParameter("productID");
		User user = (User) session.getAttribute("khachHang");

		for (CartItem item : cartService.getCartItems(user)) {
			if (item.getProduct().getProductID().equals(productID)) {
				int currentQty = item.getQuantity();
				if (currentQty > 1) {
					item.setQuantity(currentQty - 1);
					cartService.updateCartItem(item);
				}
				break;
			}
		}

		response.sendRedirect("go-to-cart");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
