package com.projectttweb.webphone.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projectttweb.webphone.database.ProductDao;
import com.projectttweb.webphone.database.ProductFavoriteDAO;
import com.projectttweb.webphone.model.ListOrderDetailsItem;
import com.projectttweb.webphone.model.Product;
import com.projectttweb.webphone.model.ProductFavorite;
import com.projectttweb.webphone.model.User;

/**
 * Servlet implementation class LoadProductByScreen
 */
@WebServlet("/load-product-by-screen")
public class LoadProductByScreen extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadProductByScreen() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if (session != null) {
			User user = (User) session.getAttribute("khachHang");
			if (user != null) {
				String screen = request.getParameter("screen");
				String page = request.getParameter("page");
				int pageInt = Integer.valueOf(page);
				ListOrderDetailsItem li = (ListOrderDetailsItem) session.getAttribute("listItem");
				String slSP = "";
				if (li != null) {
					slSP = li.getList().size() + "";
					slSP = (slSP == "0") ? "0" : slSP;
				} else {
					slSP = "0";
				}
				String sourceServlet = (String) request.getParameter("sourceServlet");
				boolean checkVar = false;
				boolean checkVar1 = false;
				if(sourceServlet != null) {
				if (sourceServlet.equalsIgnoreCase("addToCart")) {
					 checkVar = true;
				}else if(sourceServlet.equalsIgnoreCase("hetHang")) {
					checkVar1 = true;
				}
				}
				ProductDao proDao = new ProductDao();
				ArrayList<Product> lst_Res = proDao.getProductByScreen(screen, pageInt);
				int tongSoSp = proDao.getTotalProductByScreen(screen);
				int tongSoTrang = tongSoSp / 9;
				if (tongSoTrang % 9 != 0) {
					tongSoTrang++;
				}
				ProductFavoriteDAO productFaDao = new ProductFavoriteDAO();
				int lstProductFavoriteDao = productFaDao.getSoLuong2(user.getUserID());
				String uri = request.getRequestURI();
				String uriReal = xuLyURI(uri);
				String thamSo = URLEncoder.encode(request.getQueryString(), "UTF-8");
				request.setAttribute("checkNoInput", checkVar);
				request.setAttribute("checkHetHang", checkVar1);
				request.setAttribute("uri", uriReal);
				request.setAttribute("thamSo", thamSo);
				request.setAttribute("soLuongSanPhamLike", lstProductFavoriteDao);
				request.setAttribute("soLuongSP", slSP);
				request.setAttribute("currentPage", pageInt);
				request.setAttribute("listPro", lst_Res);
				request.setAttribute("nameRadio", screen);
				request.setAttribute("tongSoTrang", tongSoTrang);
				request.setAttribute("sourceServlet", "loadProductByScreen");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/product-list.jsp");
				rd.forward(request, response);
			} else {
				String screen = request.getParameter("screen");
				String page = request.getParameter("page");
				int pageInt = Integer.valueOf(page);
				ProductDao proDao = new ProductDao();
				ArrayList<Product> lst_Res = proDao.getProductByScreen(screen, pageInt);
				int tongSoSp = proDao.getTotalProductByScreen(screen);
				int tongSoTrang = tongSoSp / 9;
				if (tongSoTrang % 9 != 0) {
					tongSoTrang++;
				}
				request.setAttribute("currentPage", pageInt);
				request.setAttribute("listPro", lst_Res);
				request.setAttribute("nameRadio", screen);
				request.setAttribute("tongSoTrang", tongSoTrang);
				request.setAttribute("sourceServlet", "loadProductByScreen");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/product-list.jsp");
				rd.forward(request, response);
			}
		}

	}

	private String xuLyURI(String uri) {
		// TODO Auto-generated method stub
		return uri.substring(13);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
