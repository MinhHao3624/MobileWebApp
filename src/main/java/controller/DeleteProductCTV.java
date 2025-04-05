package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import controller.CapNhatSanPhamMoiControl.JsonResponse;
import database.OrderDetailsDAO;
import database.OrdersDAO;
import database.ProductDao;
import database.UserDao;
import model.Product;
import model.User;

/**
 * Servlet implementation class DeleteProductCTV
 */
@WebServlet("/deleteProductCTV")
public class DeleteProductCTV extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteProductCTV() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession(false);
			User us = (User) session.getAttribute("khachHang");
			OrdersDAO orderDAO = new OrdersDAO();
			UserDao userDAO = new UserDao();
			User user = userDAO.selectById3(us.getUserID());
			String productID = request.getParameter("productID");
			System.out.println(productID + "hi");
			ProductDao productDAO = new ProductDao();
			Product pro = productDAO.selectProByID2(productID);
			int stock = pro.getStockQuantity();
			int soTienHoanLai = 0;
			for (int i = 0; i < stock; i++) {
				int soTienSP = Integer.parseInt(xuLyChuoi(pro.getPrice()));
				soTienHoanLai += (int) (0.85 * soTienSP);
			}
			String soDuHienTai = user.getSoDu();
			int soDuHienTaiInt = Integer.parseInt(soDuHienTai);
			int soDuMoi = soDuHienTaiInt + soTienHoanLai;
			if (!orderDAO.kiemTraProductIsOrderByCus(productID)) {
				if (productDAO.deleteProductByID(productID)) {
					if (userDAO.capNhatSoDuMoiKhiThemSP(user, String.valueOf(soDuMoi)) > 0) {
						JsonResponse json = new JsonResponse(true, productID);
						PrintWriter out = response.getWriter();
						Gson gson = new Gson();
						out.print(gson.toJson(json));
						out.flush();
					}
				}
			} else {
				JsonResponse json = new JsonResponse(false, productID);
				PrintWriter out = response.getWriter();
				Gson gson = new Gson();
				out.print(gson.toJson(json));
				out.flush();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public class JsonResponse {
		public boolean success;
		public String productID;

		public JsonResponse(boolean success, String productID) {
			this.success = success;
			this.productID = productID;
		}

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getProductID() {
			return productID;
		}

		public void setProductID(String productID) {
			this.productID = productID;
		}

	}

	private String xuLyChuoi(String price) {
		// TODO Auto-generated method stub
		String ans = "";
		for (int i = 0; i < price.length(); i++) {
			if (price.charAt(i) == '.') {
				continue;
			} else {
				ans += price.charAt(i);
			}
		}
		return ans;
	}

}
