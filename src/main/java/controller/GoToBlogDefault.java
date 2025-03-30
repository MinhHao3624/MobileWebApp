package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.ProductFavoriteDAO;
import model.ListOrderDetailsItem;
import model.User;

/**
 * Servlet implementation class GoToBlogDefault
 */
@WebServlet("/go-to-blog")
public class GoToBlogDefault extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoToBlogDefault() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("khachHang");
		if (user != null) {
			ProductFavoriteDAO productFaDao = new ProductFavoriteDAO();
			int lstProductFavoriteDao = productFaDao.getSoLuong2(user.getUserID());
			request.setAttribute("soLuongSanPhamLike", lstProductFavoriteDao);
			ListOrderDetailsItem li = (ListOrderDetailsItem) session.getAttribute("listItem");
			String slSP = "";
			if (li != null) {
				slSP = li.getList().size() + "";
				slSP = (slSP == "0") ? "0" : slSP;
			} else {
				slSP = "0";
			}
			request.setAttribute("soLuongSP", slSP);
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/blog-default.jsp");
		rd.forward(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
