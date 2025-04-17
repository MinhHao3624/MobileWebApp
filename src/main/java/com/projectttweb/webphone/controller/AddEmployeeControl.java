package com.projectttweb.webphone.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import com.projectttweb.webphone.controller.TaiDLSanPham.JsonResponse;
import com.projectttweb.webphone.database.UserDao;
import com.projectttweb.webphone.model.Roles;
import com.projectttweb.webphone.model.User;
import com.projectttweb.webphone.util.Email;
import com.projectttweb.webphone.util.MaHoa;
import com.projectttweb.webphone.util.SoNgauNhien;

/**
 * Servlet implementation class AddEmployeeControl
 */
@WebServlet("/add-employee")
public class AddEmployeeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEmployeeControl() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			HttpSession session = request.getSession(false);
			User us = (User) session.getAttribute("admin");
			String username = request.getParameter("username");
			String pass = request.getParameter("password");
			String fullName = request.getParameter("fullName");
			String email = request.getParameter("email");
			String sdt = request.getParameter("phoneNumber");
			String roleID = request.getParameter("roleID");
			String dob = request.getParameter("dob");
			String sex = request.getParameter("gender");
			String address = request.getParameter("address");
			String status = request.getParameter("isKey");
			String typeUser = request.getParameter("typeuser");

			System.out.println("=== Dữ liệu từ form thêm nhân viên ===");
			System.out.println("Username: " + username);
			System.out.println("Password: " + pass);
			System.out.println("Họ tên: " + fullName);
			System.out.println("Email: " + email);
			System.out.println("Số điện thoại: " + sdt);
			System.out.println("Role ID: " + roleID);
			System.out.println("Ngày sinh: " + dob);
			System.out.println("Giới tính: " + sex);
			System.out.println("Địa chỉ: " + address);
			System.out.println("Trạng thái: " + status);
			System.out.println("Loại người dùng: " + typeUser);
			System.out.println("======================================");
			String passWordMaHoa = MaHoa.toSHA1(pass);
			Random rd = new Random();
			UserDao userDAO = new UserDao();
			String maXacThuc = SoNgauNhien.getSoNgauNhien();
			Calendar c = Calendar.getInstance();
			Date todaysDate = new Date(new java.util.Date().getTime());
			c.setTime(todaysDate);
			c.add(Calendar.DATE, 1);
			Date thoiGianHieuLucXacThuc = new Date(c.getTimeInMillis());
			String userID = System.currentTimeMillis() + rd.nextInt(1000) + "";
			User user = new User(userID, username, passWordMaHoa, fullName, email, sdt, new Roles(4, "Employee"),
					Date.valueOf(dob), sex, address, todaysDate, null, null, 0, "", "Chưa xử lý", "0", typeUser, 0, null,
					null, null);
			boolean check = false;
			// trạng thái xác thực
			if (userDAO.insert3(user) > 0) {
				int status2 = 0;
				user.setAuthenticationCode(maXacThuc);
				user.setConfirmationTime(thoiGianHieuLucXacThuc);
				user.setStatus(status2);
				if (userDAO.updateVertifyInformation(user) > 0) {
					// Gửi email
					Email.sendEmail(user.getEmail(), "Xác thực tài khoản tại Duy Shop", getNoiDung(user, maXacThuc));
					check = true;
					session.setAttribute("nhanVien", user);
				}
			}
			/*
			 * RequestDispatcher rd2 =
			 * getServletContext().getRequestDispatcher("/Admin/dataEmployee.jsp");
			 * rd2.forward(request, response);
			 */

			request.setAttribute("kiemTra2", check);
			RequestDispatcher rd2 = getServletContext().getRequestDispatcher("/load-customer-data");
			rd2.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private String getNoiDung(User user, String soNgauNhien) {
		// TODO Auto-generated method stub
		String noiDung = "<p>Duy Shop xin chào bạn <span>" + user.getUserName() + "</span> đây là mã OTP của bạn: "
				+ soNgauNhien + "</p>\r\n";
		return noiDung;
	}

	public class JsonResponse {
		public boolean success;

		public JsonResponse(boolean success) {
			this.success = success;
		}

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

	}

}
