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
@WebServlet("/go-to-xu-li-don-hang")
public class XuLiDonHangPageControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public XuLiDonHangPageControl() {
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
           User user = (User) session.getAttribute("employee");
           Date todaysDate = new Date(new java.util.Date().getTime());
           // Sử dụng Calendar để trừ đi 1 tháng
           String userID = request.getParameter("userID")+"";
           String page = request.getParameter("page")+"";
           if(page.equals("")) {
               page = request.getAttribute("page")+"";
           }
           if(userID.equals("")) {
               userID = request.getAttribute("userID")+"";
           }
           String mess = request.getAttribute("thongBao")+"";
           if(!mess.equals("")) {
               request.setAttribute("thongBao", mess);
           }
           String check = request.getAttribute("check")+"";
           if(check.equals("true")) {
               request.setAttribute("check", true);
           }
           Calendar cal = Calendar.getInstance();
           cal.setTime(todaysDate);
           cal.add(Calendar.MONTH, -1); // Trừ 1 tháng

           // Lấy ngày tháng năm mới
           Date previousMonthDate = new Date(cal.getTimeInMillis());
           OrdersDAO ordersDAO = new OrdersDAO();
           List<Orders> lst = ordersDAO.selectOrdersInTime(todaysDate, previousMonthDate);
           List<Orders> lst2 = ordersDAO.selectOrdersIsCheckNV(todaysDate, previousMonthDate, userID);
           double soTien = 0;
           for(Orders o : lst2) {
               if (o.getIsCheck() == 1 && o.getIdNV().equalsIgnoreCase(userID)) {
                   soTien += o.getTotalAmount();
               }
           }
           int numOrders = 0;
           for(Orders o :lst2) {
               if(o.getIsCheck() == 1 && o.getIdNV().equalsIgnoreCase(userID)) {
                   numOrders++;
               }
           }
           List<Orders> proAns = new ArrayList<Orders>();
           int tongSoTrang = lst.size() / 4;
           if(lst.size() % 4 != 0) {
               tongSoTrang++;
           }

           int pageInt = Integer.valueOf(page);
           if(pageInt == tongSoTrang) {
               int start = (pageInt - 1) * 4;
               int count = 0;
               for (Orders orders : lst) {
                   count++;
                   if(count > start) {
                       proAns.add(orders);
                   }
               }
           }else {
               int start = (pageInt - 1) * 4;
               int end = start + 4;
               int count = 0;
               for (Orders orders : lst) {
                   count++;
                   if(count > start && count <= end) {
                       proAns.add(orders);
                   }
               }

           }
           request.setAttribute("currentPage", page);
           request.setAttribute("tongSoTrang", tongSoTrang);
           request.setAttribute("listOrders", proAns);
           request.setAttribute("soTien1", String.valueOf(soTien));
           request.setAttribute("numOrders", String.valueOf(numOrders));
           RequestDispatcher rd = getServletContext().getRequestDispatcher("/Employee/employee-data-order-pay.jsp");
           rd.forward(request, response);
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
