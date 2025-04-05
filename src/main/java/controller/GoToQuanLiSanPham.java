package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ProductDao;
import database.ProductFavoriteDAO;
import database.UserDao;
import model.ListOrderDetailsItem;
import model.Product;
import model.User;

/**
 * Servlet implementation class GoToQuanLiSanPham
 */
@WebServlet("/go-to-quan-li-san-pham")
public class GoToQuanLiSanPham extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoToQuanLiSanPham() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession(false);
			User us = (User) session.getAttribute("khachHang");
			String userID = request.getParameter("userID");
			System.out.println(userID);
			String page = request.getParameter("page");
			if(page == null) {
				page = (String) request.getAttribute("page");
			}
			System.out.println(page + "trang chính");
			UserDao userDAO = new UserDao();
			User user = userDAO.selectById3(us.getUserID());
			ProductDao proDAO = new ProductDao();
			List<Product> list = proDAO.selectProDangBan(user.getUserID());
			List<Product> proAns = new ArrayList<Product>();
			int tongSoTrang = list.size() / 4;
			if(list.size() % 4 != 0) {
				tongSoTrang++;
			}
			System.out.println(list.size() +"ts sp ld");
			System.out.println(tongSoTrang +" đây là tổng số trang");
			int pageInt = Integer.valueOf(page);
			if(pageInt == tongSoTrang) {
				int start = (pageInt - 1) * 4;
				int count = 0;
				for (Product product : list) {
					count++;
					if(count > start) {
						proAns.add(product);
					}
				}
			}else {
				int start = (pageInt - 1) * 4;
				int end = start + 4;
				int count = 0;
				for (Product product : list) {
					count++;
					if(count > start && count <= end) {
						proAns.add(product);
					}
				}
				
			}
			ProductFavoriteDAO proFaDao = new ProductFavoriteDAO();
			int soLuongSanPhamLike = proFaDao.getSoLuong2(us.getUserID().trim());
			ListOrderDetailsItem li = (ListOrderDetailsItem) session.getAttribute("listItem");
			String slSP = "";
			if (li != null) {
				slSP = li.getList().size() + "";
				slSP = (slSP == "0") ? "0" : slSP;
			} else {
				slSP = "0";
			}
			request.setAttribute("currentPage", page);
			request.setAttribute("listSP", proAns);
			request.setAttribute("tongSoTrang", tongSoTrang);
			request.setAttribute("soLuongSanPhamLike", soLuongSanPhamLike);
			request.setAttribute("soLuongSP", slSP);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/dataproduct.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
