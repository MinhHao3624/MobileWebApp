package com.projectttweb.webphone.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projectttweb.webphone.database.AtmRechargeHistoryDAO;
import com.projectttweb.webphone.database.CardRechargeDAO;
import com.projectttweb.webphone.database.OrderDetailsDAO;
import com.projectttweb.webphone.database.OrdersDAO;
import com.projectttweb.webphone.database.ProductDao;
import com.projectttweb.webphone.database.ProductFavoriteDAO;
import com.projectttweb.webphone.database.TypeUserDAO;
import com.projectttweb.webphone.database.UserDao;
import com.projectttweb.webphone.model.ListOrderDetailsItem;
import com.projectttweb.webphone.model.Product;
import com.projectttweb.webphone.model.User;
import com.projectttweb.webphone.util.Email;
import com.projectttweb.webphone.util.SoNgauNhien;

/**
 * Servlet implementation class KiemTraHuyCtvControl
 */
@WebServlet("/kiem-tra-huy-ctv")
public class KiemTraHuyCtvControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KiemTraHuyCtvControl() {
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
			User us = (User) session.getAttribute("khachHang");
			ProductDao proDAO = new ProductDao();
			UserDao userDAO = new UserDao();
			User user = userDAO.selectById3(us.getUserID());
			String baoLoi = "";
			boolean check20 = false;
			System.out.println(user.getUserID() + "dcmcmmcmcm");
			if (!proDAO.checkUserHasProInList(us.getUserID())) {
				baoLoi = "Chúc mừng bạn đã gửi yêu cầu thành công. Mã xác nhận đã được gửi về gmail của bạn";
				check20 = true;
				String soNgauNhien = SoNgauNhien.getSoNgauNhien();
				if (Email.sendEmail(user.getEmail(), "Duy Shop gửi mã xác nhận cho bạn " + user.getUserName(),
						getNoiDung(user, soNgauNhien))) {
					request.setAttribute("soNgauNhien", soNgauNhien);
					request.setAttribute("check20", check20);
					request.setAttribute("baoLoi", baoLoi);
				}

			} else {
				System.out.println();
				check20 = true;
				baoLoi = "Bạn vui lòng thu hồi sản phẩm đang đăng bán trên web trước khi hủy ctv";
				request.setAttribute("baoLoi", baoLoi);
				request.setAttribute("check10", check20);
			}
			String userID = request.getParameter("userID");
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
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/nangcap.jsp");
		rd.forward(request, response);
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

	private String getNoiDung(User user, String soNgauNhien) {
		// TODO Auto-generated method stub
		String noiDung = "<p>Duy Shop xin chào bạn <span>" + user.getUserName()
				+ "</span> đây là mã xác nhận hủy đăng ký ctv của bạn: " + soNgauNhien + "</p>\r\n";
		return noiDung;
	}

}
