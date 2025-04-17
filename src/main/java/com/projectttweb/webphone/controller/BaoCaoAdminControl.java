package com.projectttweb.webphone.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.projectttweb.webphone.database.OrdersDAO;
import com.projectttweb.webphone.database.ProductFavoriteDAO;
import com.projectttweb.webphone.database.TransactionDAO;
import com.projectttweb.webphone.model.*;

/**
 * Servlet implementation class XuatHoaDonController
 */
@WebServlet("/bao-cao-cho-admin")
public class BaoCaoAdminControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BaoCaoAdminControl() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        try {
           String userID = request.getParameter("userID");
           OrdersDAO ordersDAO = new OrdersDAO();
           Date todaysDate = new Date(new java.util.Date().getTime());
           String page = request.getParameter("page");
            Calendar cal = Calendar.getInstance();
            cal.setTime(todaysDate);
            cal.add(Calendar.MONTH, -1); // Trừ 1 tháng

            // Lấy ngày tháng năm mới
            Date previousMonthDate = new Date(cal.getTimeInMillis());
           ArrayList<Orders> li =ordersDAO.selectNotSendAdmin(userID, todaysDate, previousMonthDate);
           for (Orders o : li) {
               if(ordersDAO.updateSendAdminIsTrue(o.getOrderID()) > 0){
                   continue;
               }
           }
           request.setAttribute("page", page);
           request.setAttribute("thongBao", "Chúc mừng bạn đã gửi đơn thành công");
           request.setAttribute("check", true);
           RequestDispatcher rd = getServletContext().getRequestDispatcher("/go-to-xu-li-don-hang");
           rd.forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
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
