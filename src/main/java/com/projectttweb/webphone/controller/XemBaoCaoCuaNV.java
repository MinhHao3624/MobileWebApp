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
@WebServlet("/xem-bao-cao-cua-nv")
public class XemBaoCaoCuaNV extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public XemBaoCaoCuaNV() {
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
            System.out.println(userID);
            OrdersDAO ordersDAO = new OrdersDAO();
            Date todaysDate = new Date(new java.util.Date().getTime());
            Calendar cal = Calendar.getInstance();
            cal.setTime(todaysDate);
            cal.add(Calendar.MONTH, -1); // Trừ 1 tháng

            // Lấy ngày tháng năm mới
            Date previousMonthDate = new Date(cal.getTimeInMillis());
            ArrayList<Orders> lst = ordersDAO.getListByNhanVienTKBaoCao(userID, todaysDate, previousMonthDate);
            Gson gson = new Gson();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            List<Map<String, Object>> orderData = new ArrayList<>();
            for (Orders order : lst) {
                System.out.println("ok");
                Map<String, Object> map = new HashMap<>();
                map.put("orderID", order.getOrderID());
                map.put("userName", order.getUser().getUserName());
                map.put("orderDate", order.getOrdersDate().toString());
                map.put("totalAmount", order.getTotalAmount());
                map.put("status", order.getStatus());
                map.put("isCheck", order.getIsCheck()+"");
                map.put("idNV", order.getIdNV());
                map.put("dateTK", order.getDateTK());
                orderData.add(map);
            }
            response.getWriter().write(gson.toJson(orderData));
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
