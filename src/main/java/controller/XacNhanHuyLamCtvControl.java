package controller;

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

import database.AtmRechargeHistoryDAO;
import database.CardRechargeDAO;
import database.NumBankDAO;
import database.OrderDetailsDAO;
import database.OrdersDAO;
import database.ProductDao;
import database.ProductFavoriteDAO;
import database.RegisterCtvDAO;
import database.TypeUserDAO;
import database.UserDao;
import model.ListOrderDetailsItem;
import model.Product;
import model.User;

/**
 * Servlet implementation class XacNhanHuyLamCtvControl
 */
@WebServlet("/xac-nhan-huy-lam-ctv")
public class XacNhanHuyLamCtvControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XacNhanHuyLamCtvControl() {
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
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(false);
			User us = (User) session.getAttribute("khachHang");
			UserDao userDAO = new UserDao();
			System.out.println(us.getUserID()+"tvtvtvtvtvtvt");
			User user = userDAO.selectById3(us.getUserID());
			NumBankDAO numBankDAO = new NumBankDAO();
			RegisterCtvDAO registerCtvDAO = new RegisterCtvDAO();
			String maXacNhan = request.getParameter("maXacNhan");
			String soNgauNhien = request.getParameter("soNgauNhien");
			String baoLoi = "";
			boolean check10 = false;
			System.out.println(maXacNhan+"pppppppppppppp");
			System.out.println(soNgauNhien+"aaaaaaaaaaaaaaa");
			Date todaysDate = new Date(new java.util.Date().getTime());
			if (maXacNhan.equalsIgnoreCase(soNgauNhien)) {
				System.out.println("tutututuppp");
				String stk = userDAO.selectSoTaiKhoan(us.getUserID());
				System.out.println(stk+"đây là stk");
				String stkReal = numBankDAO.selectSTK(stk);
				System.out.println(stkReal + "stk real");
				String soTienTrongAtm = numBankDAO.selectSoTienTrongAtm(stkReal);
				System.out.println(soTienTrongAtm+"abcxyz");
				String soTienKiemDuoc = registerCtvDAO.selectSoTienKiemDuoc(us.getUserID());
				System.out.println(soTienKiemDuoc+"dcxssss");
				int hoanLoai = Integer.parseInt(soTienTrongAtm) + Integer.parseInt(soTienKiemDuoc) + 2000000;
				String soDuHienTai = userDAO.selectSoDuHienTai(us.getUserID());
				int soTienCanCapNhat = hoanLoai + Integer.parseInt(soDuHienTai);
				if (userDAO.hoanTienLaiChoCtv(us.getUserID(), String.valueOf(soTienCanCapNhat)) > 0) {
					if (userDAO.restartCtvIsNullAndReturnCus(us.getUserID()) > 0) {
						if (numBankDAO.deleteStk(stkReal) > 0) {
							if (registerCtvDAO.capNhatNgayKetThuc(us.getUserID(), todaysDate) > 0) {
								baoLoi = "Chúc mừng bạn đã hủy đăng ký ctv thành công. Số tiền hoàn đã được cộng vào tk của bạn";
								check10 = true;
								request.setAttribute("baoLoi", baoLoi);
								request.setAttribute("check10", check10);
							}
						}
					}
				}
			} else {
				System.out.println("MÃ xác nhận chưa đúng");
				baoLoi = "Mã xác nhận chưa chính xác. Vui lòng kiểm tra lại";
				check10 = true;
				request.setAttribute("soNgauNhien", soNgauNhien);
				request.setAttribute("baoLoi", baoLoi);
				request.setAttribute("check20", true);
				request.setAttribute("check21", check10);
			}
			TypeUserDAO typeUserDAO = new TypeUserDAO();
			//
			//
			AtmRechargeHistoryDAO atmDAO = new AtmRechargeHistoryDAO();
			CardRechargeDAO cardDAO = new CardRechargeDAO();
			
			String soTienAtm = atmDAO.getSoTienAtm(us.getUserID(), todaysDate);
			String soTienCard = cardDAO.getSoTienCard(us.getUserID(), todaysDate);
			
			int tongSoTien = Integer.valueOf(soTienAtm) + Integer.valueOf(soTienCard);
			String typeUser = "";
			User user2 = null;
			if(tongSoTien < 2000000) {
				if(userDAO.updateTypeUser(us.getUserID(), 1) > 0) {
					user2 = userDAO.selectById3(user.getUserID());
				     typeUser = typeUserDAO.selectNameTypeUs(user2.getTypeUser());
				}
			}else {
				if(userDAO.updateTypeUser(us.getUserID(), 2) > 0) {
					user2 = userDAO.selectById3(user.getUserID());
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

}
