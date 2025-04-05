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
import database.TypeUserDAO;
import database.UserDao;
import model.AtmRechargeHistory;
import model.CardRechargeHistory;
import model.ListOrderDetailsItem;
import model.Product;
import model.User;

/**
 * Servlet implementation class XemLichSuNapController
 */
@WebServlet("/xem-lich-su-nap")
public class XemLichSuNapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public XemLichSuNapController() {
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
			AtmRechargeHistoryDAO atmRechargeHistoryDAO = new AtmRechargeHistoryDAO();
			CardRechargeDAO cardRechargeDAO = new CardRechargeDAO();
			List<AtmRechargeHistory> li1 = atmRechargeHistoryDAO.selectListAtmByID(userID);
			List<CardRechargeHistory> li2 = cardRechargeDAO.selectListCardByID(userID);
			ProductFavoriteDAO proFaDao = new ProductFavoriteDAO();
			int soLuongSanPhamLike = proFaDao.getSoLuong2(userID);
			ListOrderDetailsItem li = (ListOrderDetailsItem) session.getAttribute("listItem");
			String slSP = "";
			if (li != null) {
				slSP = li.getList().size() + "";
				slSP = (slSP == "0") ? "0" : slSP;
			} else {
				slSP = "0";
			}
			UserDao userDAO = new UserDao();
			TypeUserDAO typeUserDAO = new TypeUserDAO();
			//
			//
			Date todaysDate = new Date(new java.util.Date().getTime());
			AtmRechargeHistoryDAO atmDAO = new AtmRechargeHistoryDAO();
			CardRechargeDAO cardDAO = new CardRechargeDAO();
			
			String soTienAtm = atmDAO.getSoTienAtm(us.getUserID(), todaysDate);
			String soTienCard = cardDAO.getSoTienCard(us.getUserID(), todaysDate);
			
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
			request.setAttribute("soLuongSanPhamLike", soLuongSanPhamLike);
			request.setAttribute("soLuongSP", slSP);
			request.setAttribute("kiemTra1", true);
			request.setAttribute("list1", li1);
			request.setAttribute("list2", li2);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/nangcap.jsp");
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
