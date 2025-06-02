package com.projectttweb.webphone.controller;

import com.projectttweb.webphone.database.ProductDao;
import com.projectttweb.webphone.database.UserDao;
import com.projectttweb.webphone.model.Product;
import com.projectttweb.webphone.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/xoa-tai-khoan")
public class XoaTaiKhoan extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public XoaTaiKhoan() {
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
           UserDao userDao = new UserDao();
           int res = userDao.updateUserIsDeleted(userID);
           if(res > 0){
               RequestDispatcher rd = request.getRequestDispatcher("load-user-data");
               rd.forward(request, response);
           }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
