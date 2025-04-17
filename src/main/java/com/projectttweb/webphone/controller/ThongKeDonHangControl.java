package com.projectttweb.webphone.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.projectttweb.webphone.database.OrdersDAO;
import com.projectttweb.webphone.database.ProductFavoriteDAO;
import com.projectttweb.webphone.database.TransactionDAO;
import com.projectttweb.webphone.model.*;

/**
 * Servlet implementation class XuatHoaDonController
 */
@WebServlet("/thong-Ke-Don-Hang")
public class ThongKeDonHangControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThongKeDonHangControl() {
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
          User user = (User) session.getAttribute("khachHang");
          OrdersDAO ordersDAO = new OrdersDAO();
          String orderID = request.getParameter("orderID");
          String userID = request.getParameter("userID");
          String page = request.getParameter("page");
          if(ordersDAO.capNhatThongKe(userID, orderID) > 0) {
              request.setAttribute("page", page);
              request.setAttribute("userID", userID);
              RequestDispatcher rd = getServletContext().getRequestDispatcher("/go-to-xu-li-don-hang");
              rd.forward(request, response);
          }
        }catch (Exception e) {
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
