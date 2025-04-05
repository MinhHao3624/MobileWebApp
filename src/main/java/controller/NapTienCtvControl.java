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
import database.OrderDetailsDAO;
import database.OrdersDAO;
import database.ProductDao;
import database.ProductFavoriteDAO;
import database.RegisterCtvDAO;
import database.SoTaiKhoanDAO;
import database.TypeUserDAO;
import database.UserDao;
import model.ListOrderDetailsItem;
import model.Product;
import model.SoTaiKhoan;
import model.User;

/**
 * Servlet implementation class NapTienCtvControl
 */
@WebServlet("/napTienCtv")
public class NapTienCtvControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NapTienCtvControl() {
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
			User us = (User) session.getAttribute("khachHang");
			UserDao userDAO = new UserDao();
			User user = userDAO.selectById3(us.getUserID());
			SoTaiKhoanDAO stkDAO = new SoTaiKhoanDAO();
			String stk = request.getParameter("soTaiKhoan");
			System.out.println(stk+"tututu");
			String bank = request.getParameter("nganHang");
			System.out.println(bank + "ttttyyyy");
			String maPin = request.getParameter("maPin");
			System.out.println(maPin +"oowwww");
			SoTaiKhoan stk2 = stkDAO.selectSoTaiKhoan(stk);
			String baoLoi = "";
			boolean check13 = false; 
			RegisterCtvDAO registerCtvDAO = new RegisterCtvDAO();
			if(Integer.parseInt(stk2.getSoDu()) >= 10000000) {
				double soDuConLai = Integer.parseInt(stk2.getSoDu()) - 10000000;
				if(stkDAO.capNhatSoDu(String.valueOf(soDuConLai), stk) > 0) {
					if(userDAO.setUpRolesAndDesposit(us.getUserID()) > 0) {
						baoLoi = "Chúc mừng bạn đã đăng ký vị trí cộng tác viên của web thành công";
						check13 = true;
						request.setAttribute("baoLoi", baoLoi);
						request.setAttribute("check10", check13);
					}
				}
			}else {
				check13 = true;
				baoLoi += "Không đủ số dư";
				request.setAttribute("baoLoi", baoLoi);
				request.setAttribute("check10", check13);
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
			
			String iD = registerCtvDAO.selectIDCurrent();
			String iDNext = xuLyID(iD);
			if(registerCtvDAO.insertCtv(iDNext, us.getUserID(), todaysDate, size, size2, size3, soTienKiemDuoc) > 0) {
				
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
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private String xuLyID(String iD) {
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
		n = n+1;
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

}
