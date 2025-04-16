package com.projectttweb.webphone.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projectttweb.webphone.database.UserDao;
import com.projectttweb.webphone.model.User;

/**
 * Servlet implementation class VetifyOtpControl
 */
@WebServlet("/vetifyOtpControl")
public class VetifyOtpControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VetifyOtpControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HttpSession session = request.getSession(false);
			User us = (User) session.getAttribute("nhanVien");
			System.out.println(us.getUserID() + "userIDID");
			UserDao userDAO = new UserDao();
			boolean kiemTra = false;
			String msg = "";
			if(us != null) {
				String maXacNhan = request.getParameter("maXacNhan");
				if(maXacNhan.equalsIgnoreCase(us.getAuthenticationCode())) {
					if(userDAO.capNhatNhanVienMoi(us.getUserID()) > 0) {
					kiemTra = true;
					msg = "Chúc mừng đã đăng ký nhân viên mới thành công";
					request.setAttribute("kiemTra", kiemTra);
					request.setAttribute("msg", msg);
					session.removeAttribute("nhanVien");
					}
				}else {
					request.setAttribute("kiemTra2", true);
					request.setAttribute("mess", "Nhập sai mã xác nhận vui lòng kiểm tra lại mã");
				}
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/load-customer-data");
			rd.forward(request, response);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
