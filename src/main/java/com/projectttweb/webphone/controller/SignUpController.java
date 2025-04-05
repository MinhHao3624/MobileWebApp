package com.projectttweb.webphone.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.projectttweb.webphone.database.UserDao;
import com.projectttweb.webphone.model.Roles;
import com.projectttweb.webphone.model.User;
import com.projectttweb.webphone.util.Email;
import com.projectttweb.webphone.util.MaHoa;
import com.projectttweb.webphone.util.SoNgauNhien;

@WebServlet("/sign-up")
public class SignUpController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUpController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");

			// Lấy thông tin từ form đăng ký
			String userName = request.getParameter("username");
			String passWord = request.getParameter("password");
			String re_PassWord = request.getParameter("again-password");
			String fullName = request.getParameter("fullName");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String dateOfBirth = request.getParameter("dateOfBirth");
			String address = request.getParameter("address");
			String sex = request.getParameter("gioiTinh");

			// Thiết lập các giá trị mặc định
			int roleID = 2; // Khách hàng
			String createAt = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			// Đặt các thuộc tính để giữ lại giá trị form khi có lỗi
			request.setAttribute("username", userName);
			request.setAttribute("fullName", fullName);
			request.setAttribute("email", email);
			request.setAttribute("phone", phone);
			request.setAttribute("dateofbirth", dateOfBirth);
			request.setAttribute("addRess", address);
			request.setAttribute("sex", sex);

			String url = "";
			String error = "";
			boolean check = false;
			UserDao userDao = new UserDao();

			// Kiểm tra hợp lệ dữ liệu
			if (userDao.kiemTraTenDangNhap(userName)) {
				error += "Tên đăng nhập đã tồn tại.<br/>";
			}
			if (!passWord.equals(re_PassWord)) {
				error += "Mật khẩu nhập lại không khớp.<br/>";
			} else {
				passWord = MaHoa.toSHA1(passWord); // Mã hóa mật khẩu
			}
			if (userDao.kiemTraByEmail(email)) {
				error += "Email đã tồn tại. Vui lòng chọn email khác!<br/>";
			}
			if (userDao.kiemTraSoDienThoai(phone)) {
				error += "Số điện thoại đã tồn tại. Vui lòng chọn số khác!<br/>";
			}

			request.setAttribute("baoLoi", error);

			if (error.length() > 0) {
				url = "/signup-form.jsp";
			} else {
				// Tạo user mới
				Random rd = new Random();
				String userID = System.currentTimeMillis() + rd.nextInt(1000) + "";
				User user = new User(userID, userName, passWord, fullName, email, phone,
						new Roles(roleID, "Khách hàng"), Date.valueOf(dateOfBirth),
						sex, address, Date.valueOf(createAt), "");

				if (userDao.insert2(user) > 0) {
					// Tạo CAPTCHA ban đầu và lưu vào session
					String captcha = generateCaptcha();
					HttpSession session = request.getSession();
					session.setAttribute("captcha", captcha);

					// Tạo mã OTP và thiết lập thời gian hết hạn
					String maXacThuc = SoNgauNhien.getSoNgauNhien();
					Calendar c = Calendar.getInstance();
					c.add(Calendar.DATE, 1);
					Date thoiGianHieuLucXacThuc = new Date(c.getTimeInMillis());

					// Cập nhật thông tin xác thực
					user.setAuthenticationCode(maXacThuc);
					user.setConfirmationTime(thoiGianHieuLucXacThuc);
					user.setStatus(0); // Chưa xác thực

					if (userDao.updateVertifyInformation(user) > 0) {
						// Gửi email xác thực
						Email.sendEmail(user.getEmail(), "Xác thực tài khoản", getEmailContent(user));
						check = true;
						session.setAttribute("user", user);
					}
				}
				url = "/signup-form.jsp";
			}

			request.setAttribute("kiemTra", check);
			request.setAttribute("sourceServlet", "signUpController");
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Tạo CAPTCHA ngẫu nhiên
	private String generateCaptcha() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder captcha = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int index = (int) (Math.random() * chars.length());
			captcha.append(chars.charAt(index));
		}
		return captcha.toString();
	}

	// Nội dung email xác thực
	private String getEmailContent(User user) {
		String link = "http://localhost:8080/MobileWebApp/xac-thuc?userID=" + user.getUserID() +
				"&authenticationCode=" + user.getAuthenticationCode();
		return "<p>Xin chào <strong>" + user.getUserName() + "</strong>,</p>" +
				"<p>Vui lòng xác thực tài khoản bằng mã: <strong>" + user.getAuthenticationCode() + "</strong></p>" +
				"<p>Hoặc truy cập link: <a href=\"" + link + "\">" + link + "</a></p>" +
				"<p>Email này tự động, vui lòng không phản hồi.</p>";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}