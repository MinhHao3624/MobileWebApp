package com.projectttweb.webphone.controller;


import com.projectttweb.webphone.database.OrdersDAO;
import com.projectttweb.webphone.model.Orders;
import com.projectttweb.webphone.model.User;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class AccountLoginController
 */
@WebServlet("/load-data-order-in-month")
public class LoadDataOrderInMonthAndYear extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadDataOrderInMonthAndYear() {
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
        HttpSession session = request.getSession(false);
        try {
            User user = (User) session.getAttribute("admin");
            OrdersDAO ordersDAO = new OrdersDAO();
            Date todaysDate = new Date(new java.util.Date().getTime());
            // Lấy ngày đầu tháng
            Calendar cal = Calendar.getInstance();
            cal.setTime(todaysDate);
            cal.set(Calendar.DAY_OF_MONTH, 1);  // Đặt ngày là ngày đầu tháng

            java.util.Date firstDayOfMonth = cal.getTime();
            List<Orders> lst = ordersDAO.selectOrdersIsCheckInMonth(todaysDate, firstDayOfMonth);
            Map<String, Integer> map = new LinkedHashMap<String, Integer>();
            for (Orders o : lst) {
                if(map.containsKey(o.getIdNV())) {
                    map.replace(o.getIdNV(), map.get(o.getIdNV()) + 1);
                }else{
                    map.put(o.getIdNV(), 1);
                }
            }
            List<NhanVienChamChi> lstNVCham = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                lstNVCham.add(new NhanVienChamChi(entry.getKey(), entry.getValue()));
            }
            double sumMoney = 0;
            double loiNhuan = 0;
            for(Orders o : lst) {
                sumMoney += o.getTotalAmount();
                loiNhuan += o.getTotalAmount() * 0.05;
            }
            int price = (int) sumMoney;
            int loiNhuanReal = (int) loiNhuan;
            request.setAttribute("loiNhuanReal", loiNhuanReal);
            request.setAttribute("price", price);
            request.setAttribute("lstNVCham", lstNVCham);
            request.setAttribute("lstOrder", lst);
            request.setAttribute("Year", todaysDate.getYear()+1900);
            request.setAttribute("Month", todaysDate.getMonth()+1);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Admin/admin-recent-revenue.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public class NhanVienChamChi {
        String idNV;
        int soLan;
        public NhanVienChamChi(String idNV, int soLan) {
            this.idNV = idNV;
            this.soLan = soLan;
        }
        public String getIdNV() {
            return idNV;
        }
        public void setIdNV(String idNV) {
            this.idNV = idNV;
        }
        public int getSoLan() {
            return soLan;
        }
        public void setSoLan(int soLan) {
            this.soLan = soLan;
        }
    }

    public static void main(String[] args) {
        Date todaysDate = new Date(new java.util.Date().getTime());
        // Ngày hiện tại (không dùng trong ví dụ này nhưng vẫn khai báo cho rõ ràng)
        System.out.println(todaysDate.getDate() +" "+todaysDate.getMonth()+" "+todaysDate.getYear());


        // Giả sử ngày hôm nay là 20/5/2025
        Date todaysDate2 = new Date(2025 - 1900, 4, 20); // Tháng 5 là 4 (vì tính từ 0)

        // Lấy ngày đầu tháng
        Calendar cal = Calendar.getInstance();
        cal.setTime(todaysDate);
        cal.set(Calendar.DAY_OF_MONTH, 1);  // Đặt ngày là ngày đầu tháng

        java.util.Date firstDayOfMonth = cal.getTime();  // Không cần ép kiểu (Date)
        System.out.println(firstDayOfMonth.getDate() +" "+firstDayOfMonth.getMonth() +" "+firstDayOfMonth.getYear());

        // (Tùy chọn) Định dạng ngày cho dễ đọc
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ngày đầu tháng: " + sdf.format(firstDayOfMonth));

    }


}
