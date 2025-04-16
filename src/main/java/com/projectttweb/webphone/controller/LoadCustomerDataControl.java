package com.projectttweb.webphone.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class LoadCustomerDataControl
 */
@WebServlet("/load-customer-data")
public class LoadCustomerDataControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadCustomerDataControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("okokokok");
		System.out.println("diminochan");
		try {
			
				HttpSession session = request.getSession(false);
				User user = (User) session.getAttribute("admin");
				String check = request.getAttribute("check") + "";
				boolean one = false;
				if(check.equals("true")) {
					String mess = request.getAttribute("mess") + "";
					if(!mess.equals("")) {
						request.setAttribute("mess", mess);
					}
					one = true;
					request.setAttribute("kiemTra2", one);
				}
				String kiemTra = request.getAttribute("kiemTra") +"";
				boolean two = false;
				if(kiemTra.equals("true")) {
					two = true;
					String msg = request.getAttribute("msg") +"";
					request.setAttribute("msg", msg);
					request.setAttribute("kiemTra", two);
				}
				if(user != null) {
					UserDao userDAO = new UserDao();
					ArrayList<User> lstEmployee = userDAO.selectEmployeeNotAdminNotUser();
					request.setAttribute("listEmployee", lstEmployee);
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/dataEmployee.jsp");
					rd.forward(request, response);
				}
			
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
