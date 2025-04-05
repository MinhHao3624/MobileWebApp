package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.Year;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.AtmRechargeHistoryDAO;
import database.CardRechargeDAO;
import database.OrderDetailsDAO;
import database.OrdersDAO;
import database.ProductDao;
import database.ProductFavoriteDAO;
import database.SoTaiKhoanDAO;
import database.TypeUserDAO;
import database.UserDao;
import model.ListOrderDetailsItem;
import model.Product;
import model.SoTaiKhoan;
import model.User;

/**
 * Servlet implementation class DangKiCTVControl
 */
@WebServlet("/dang-ky-ctv")
public class DangKiCTVControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DangKiCTVControl() {
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
		try {
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("khachHang");
			UserDao userDAO = new UserDao();
			User us = userDAO.selectById3(user.getUserID());
			String baoLoi = "";
			boolean check10 = false;
			boolean check11 = false;
			SoTaiKhoanDAO stkDAO = new SoTaiKhoanDAO();
			if (us.getRole().getRoleID() != 3) {
				String name = request.getParameter("hoten");
				String age = request.getParameter("age");
				String cccd = request.getParameter("cccd");
				String gender = request.getParameter("gender");
				String degree = request.getParameter("trinhdo");
				String stk = request.getParameter("soTaiKhoan");
				String stk2 = xuLyStK(stk);
				String nganHang = xuLy(stk);
				if (userDAO.kiemTraHoTen(name, us.getUserID())) {
					if (kiemTraCCCD(cccd)) {
						String iD = stkDAO.selectIDLast();
						String iDNext = xuLyIDNext(iD);
						SoTaiKhoan stk3 = new SoTaiKhoan(iDNext, stk2, "20000000", randomPin(), nganHang);
						if (stkDAO.insertSoTaiKhoan(stk3) > 0) {
							if (userDAO.updateCTV(age, cccd, gender, degree, stk3, us.getUserID())) {
								check11 = true;
								request.setAttribute("check11", check11);
								request.setAttribute("soTaiKhoan", stk);
								request.setAttribute("nganHang", nganHang);
							}
						}
					} else {
						baoLoi += "Bạn chưa đủ tuổi";
						check10 = true;
						request.setAttribute("baoLoi", baoLoi);
						request.setAttribute("check10", check10);
					}
				} else {
					baoLoi += "Sai họ và tên";
					check10 = true;
					request.setAttribute("baoLoi", baoLoi);
					request.setAttribute("check10", check10);
				}
			} else {
				baoLoi = "Bạn đang giữ vị trí là ctv của web";
				check10 = true;
				request.setAttribute("baoLoi", baoLoi);
				request.setAttribute("check10", check10);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/nangcap.jsp");
		rd.forward(request, response);
	}

	private String randomPin() {
		// TODO Auto-generated method stub
		Random rd = new Random();
		String ans = "";
		int a = rd.nextInt(10);
		int b = rd.nextInt(10);
		int c = rd.nextInt(10);
		int d = rd.nextInt(10);
		ans = a +""+ b +""+ c +""+ d + "";
		return ans;
	}

	private String xuLyIDNext(String iD) {
		// TODO Auto-generated method stub
		String num = "";
		boolean mo = false;
		for (int i = 0; i < iD.length(); i++) {
			if (iD.charAt(i) != '0' || mo) {
				num += iD.charAt(i);
				mo = true;
			}
		}
		int n = Integer.valueOf(num);
		n = n + 1;
		String ans = "";
		if (n < 10) {
			ans = "000" + n;
		} else if (n < 100) {
			ans = "00" + n;
		} else if (n < 1000) {
			ans = "0" + n;
		} else {
			ans = String.valueOf(n);
		}
		return ans;
	}

	private String xuLyStK(String stk) {
		// TODO Auto-generated method stub
		String mess = "";
		for (int i = 0; i < stk.length(); i++) {
			if (Character.isDigit(stk.charAt(i))) {
				mess += stk.charAt(i);
			}
		}
		return mess;
	}

	private String xuLy(String stk) {
		// TODO Auto-generated method stub
		String mess = "";
		for (int i = 0; i < stk.length(); i++) {
			if (Character.isLetter(stk.charAt(i))) {
				mess += stk.charAt(i);
			}
		}
		return mess;
	}

	private boolean kiemTraCCCD(String cccd) {
		// TODO Auto-generated method stub
		if (cccd.length() != 12) {
			System.out.println("CCCD không hợp lệ!");
			return false;
		}
		try {
			int genderCode = Character.getNumericValue(cccd.charAt(3)); // Lấy mã giới tính (số thứ 4)
			int yearShort = Integer.parseInt(cccd.substring(4, 6)); // Lấy 2 số cuối của năm sinh
			int birthYear;

			// Xác định năm sinh dựa trên mã giới tính
			if (genderCode == 0 || genderCode == 1) {
				birthYear = 1900 + yearShort; // 19XX
			} else if (genderCode == 2 || genderCode == 3) {
				birthYear = 2000 + yearShort; // 20XX
			} else if (genderCode == 4 || genderCode == 5) {
				birthYear = 2100 + yearShort; // 21XX
			} else {
				System.out.println("CCCD không hợp lệ!");
				return false;
			}

			// Lấy năm hiện tại
			int currentYear = Year.now().getValue();
			int age = currentYear - birthYear;

			// Kiểm tra nếu trên 18 tuổi
			return age >= 18;
		} catch (NumberFormatException e) {
			System.out.println("Lỗi: CCCD không đúng định dạng!");
			return false;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(false);
			User user = (User) session.getAttribute("khachHang");
			UserDao userDAO = new UserDao();
			User us = userDAO.selectById3(user.getUserID());
			String baoLoi = "";
			boolean check11 = false;
			boolean check10 = false;
			SoTaiKhoanDAO stkDAO = new SoTaiKhoanDAO();
			if (us.getRole().getRoleID() != 3) {
				String name = request.getParameter("hoten");
				String age = request.getParameter("age");
				String cccd = request.getParameter("cccd");
				String gender = request.getParameter("gender");
				String degree = request.getParameter("trinhdo");
				String stk = request.getParameter("soTaiKhoan");
				String stk2 = xuLyStK(stk);
				String nganHang = xuLy(stk);
				if (userDAO.kiemTraHoTen(name, us.getUserID())) {
					if (kiemTraCCCD(cccd)) {
						String iD = stkDAO.selectIDLast();
						String iDNext = xuLyIDNext(iD);
						SoTaiKhoan stk3 = new SoTaiKhoan(iDNext, stk2, "20000000", randomPin(), nganHang);
						if (!stkDAO.kiemTraSoTaiKhoan(stk2)) {
							if (!userDAO.kiemTraCCCD(cccd)) {
								if (stkDAO.insertSoTaiKhoan(stk3) > 0) {
									if (userDAO.updateCTV(age, cccd, gender, degree, stk3, us.getUserID())) {
										check11 = true;
										request.setAttribute("check11", check11);
										request.setAttribute("soTaiKhoan", stk2);
										request.setAttribute("nganHang", nganHang);
									}
								}
							} else {
								baoLoi += "CCCD đã tồn tại";
								check10 = true;
								request.setAttribute("baoLoi", baoLoi);
								request.setAttribute("check10", check10);
							}
						} else {
							baoLoi += "Số tài khoản đã tồn tại rồi";
							check10 = true;
							request.setAttribute("baoLoi", baoLoi);
							request.setAttribute("check10", check10);
						}
					} else {
						baoLoi += "Bạn chưa đủ tuổi";
						check10 = true;
						request.setAttribute("baoLoi", baoLoi);
						request.setAttribute("check10", check10);
					}
				} else {
					baoLoi += "Sai họ và tên";
					check10 = true;
					request.setAttribute("baoLoi", baoLoi);
					request.setAttribute("check10", check10);
				}
			} else {
				baoLoi = "Bạn đang giữ vị trí là ctv của web";
				check10 = true;
				request.setAttribute("baoLoi", baoLoi);
				request.setAttribute("check10", check10);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		HttpSession session = request.getSession(false);
		User us = (User) session.getAttribute("khachHang");
		String userID = request.getParameter("userID");
		UserDao userDAO = new UserDao();
		User user = userDAO.selectById3(us.getUserID());
		TypeUserDAO typeUserDAO = new TypeUserDAO();
		//
		//
		Date todaysDate = new Date(new java.util.Date().getTime());
		AtmRechargeHistoryDAO atmDAO = new AtmRechargeHistoryDAO();
		CardRechargeDAO cardDAO = new CardRechargeDAO();
		
		String soTienAtm = atmDAO.getSoTienAtm(user.getUserID(), todaysDate);
		String soTienCard = cardDAO.getSoTienCard(user.getUserID(), todaysDate);
		
		int tongSoTien = Integer.valueOf(soTienAtm) + Integer.valueOf(soTienCard);
		String typeUser = "";
		User user2 = null;
		if(tongSoTien < 2000000) {
			if(userDAO.updateTypeUser(us.getUserID(), 1) > 0) {
				user2 = userDAO.selectById3(us.getUserID());
			     typeUser = typeUserDAO.selectNameTypeUs(user2.getTypeUser());
			}
		}else {
			if(userDAO.updateTypeUser(us.getUserID(), 2) > 0) {
				user2 = userDAO.selectById3(us.getUserID());
				typeUser = typeUserDAO.selectNameTypeUs(user2.getTypeUser());
			}
		}
		
		String checkCTV = userDAO.isCTV(user2.getUserID());
		String ctv = "";
		if(checkCTV.equals("ok")) {
			ctv = "Cộng tác viên bán hàng";
		}else {
			ctv = "không phải";
		}
		ProductDao productDao = new ProductDao();
		// so sp đã bán 
		List<Product> soSPDaDangBan = productDao.selectSoSPDaDangBan(user2.getUserID());
		int size = soSPDaDangBan.size();
		// so sp đã thu hồi
		List<Product> soSPDaThuHoi = productDao.selectSoSPDaThuHoi(user2.getUserID());
		int size2 = soSPDaThuHoi.size();
		int size3 = 0;
		List<Product> li4 = null;
		if(ctv.equalsIgnoreCase("Cộng tác viên bán hàng")) {
			OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
			li4 = orderDetailsDAO.selectSoSPDaBanTrongThang(user2.getUserID(), todaysDate);
			size3 = li4.size();
		}else {
			size3 = 0;
		}
		// số tiền kiếm được
		double soTienKiemDuoc = 0;
		OrdersDAO ordersDAO = new OrdersDAO();
		if(ctv.equalsIgnoreCase("Cộng tác viên bán hàng")) {
			soTienKiemDuoc = ordersDAO.getTongSoTienBanDuoc(user2.getUserID());
		}else {
			soTienKiemDuoc = 0;
		}
		ProductFavoriteDAO proFaDao = new ProductFavoriteDAO();
		int soLuongSanPhamLike = proFaDao.getSoLuong2(user2.getUserID().trim());
		ListOrderDetailsItem li = (ListOrderDetailsItem) session.getAttribute("listItem");
		String slSP = "";
		if (li != null) {
			slSP = li.getList().size() + "";
			slSP = (slSP == "0") ? "0" : slSP;
		} else {
			slSP = "0";
		}
		request.setAttribute("soLuongSanPhamLike", soLuongSanPhamLike);
		request.setAttribute("soLuongSP", slSP);
		request.setAttribute("loaiTaiKhoan", typeUser);
		request.setAttribute("soTienDaNop", tongSoTien);
		request.setAttribute("ctv", ctv);
		request.setAttribute("soSPDaDangBan", size);
		request.setAttribute("soSPDaThuHoi", size2);
		request.setAttribute("soSPDaBanTrongThangGanNhat", size3);
		request.setAttribute("soTienKiemDuoc", soTienKiemDuoc);
		
		request.setAttribute("dsSoSPDaDangBan", soSPDaDangBan);
		request.setAttribute("dsSoSPDaThuHoi", soSPDaThuHoi);
		request.setAttribute("soSPDaBanTrongThang", li4);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/nangcap.jsp");
		rd.forward(request, response);
	}

}
