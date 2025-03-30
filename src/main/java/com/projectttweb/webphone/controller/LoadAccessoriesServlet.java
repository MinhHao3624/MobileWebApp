package com.projectttweb.webphone.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.projectttweb.webphone.database.Accessory;
import com.projectttweb.webphone.database.JDBCUtil;

@WebServlet("/load-accessories")
public class LoadAccessoriesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int ITEMS_PER_PAGE = 6;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String category = request.getParameter("category") != null ? request.getParameter("category") : "Tatca";
        String type = request.getParameter("type") != null ? request.getParameter("type") : "Tatca";
        String brand = request.getParameter("brand") != null ? request.getParameter("brand") : "Tatca";
        String price = request.getParameter("price") != null ? request.getParameter("price") : "Tatca";
        int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

        int offset = (page - 1) * ITEMS_PER_PAGE;
        List<Accessory> listAccessories = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();

            // Xây dựng câu truy vấn chính
            StringBuilder sql = new StringBuilder("SELECT * FROM Accessories WHERE 1=1");
            List<String> params = new ArrayList<>();

            if (!type.equals("Tatca")) {
                sql.append(" AND type = ?");
                params.add(type);
            }
            if (!brand.equals("Tatca")) {
                sql.append(" AND brand = ?");
                params.add(brand);
            }
            if (!price.equals("Tatca")) {
                if (price.equals("duoi500ngan")) {
                    sql.append(" AND price < 500000");
                } else if (price.equals("tu500nganden1trieu")) {
                    sql.append(" AND price BETWEEN 500000 AND 1000000");
                } else if (price.equals("tren1trieu")) {
                    sql.append(" AND price > 1000000");
                }
            }

            // Sử dụng cú pháp LIMIT ?, ? thay vì LIMIT ? OFFSET ?
            sql.append(" LIMIT ?, ?");
            // Không thêm vào params vì LIMIT và OFFSET cần kiểu int

            pstmt = conn.prepareStatement(sql.toString());
            // Gán tham số cho các điều kiện lọc
            for (int i = 0; i < params.size(); i++) {
                pstmt.setString(i + 1, params.get(i));
            }
            // Gán tham số cho LIMIT (offset, row_count)
            pstmt.setInt(params.size() + 1, offset); // offset
            pstmt.setInt(params.size() + 2, ITEMS_PER_PAGE); // row_count

            rs = pstmt.executeQuery();
            while (rs.next()) {
                Accessory accessory = new Accessory();
                accessory.setAccessoryID(rs.getInt("accessoryID"));
                accessory.setName(rs.getString("name"));
                accessory.setPrice(rs.getDouble("price"));
                accessory.setOriginalPrice(rs.getDouble("originalPrice") != 0 ? rs.getDouble("originalPrice") : rs.getDouble("price"));
                accessory.setDiscount(rs.getInt("discount"));
                accessory.setType(rs.getString("type"));
                accessory.setBrand(rs.getString("brand"));
                accessory.setImage(rs.getString("image"));
                accessory.setDescription(rs.getString("description"));
                accessory.setStock(rs.getInt("stock"));
                accessory.setCreatedAt(rs.getTimestamp("createdAt"));
                listAccessories.add(accessory);
            }

            // Tính tổng số trang
            String countSql = "SELECT COUNT(*) FROM Accessories WHERE 1=1";
            if (sql.indexOf("AND") > -1) {
                countSql += sql.substring(sql.indexOf("WHERE") + 7, sql.indexOf("LIMIT"));
            }
            pstmt = conn.prepareStatement(countSql);
            for (int i = 0; i < params.size(); i++) {
                pstmt.setString(i + 1, params.get(i));
            }
            rs = pstmt.executeQuery();
            rs.next();
            int totalItems = rs.getInt(1);
            int totalPages = (int) Math.ceil((double) totalItems / ITEMS_PER_PAGE);

            request.setAttribute("listAccessories", listAccessories);
            request.setAttribute("accessoryCategory", category);
            request.setAttribute("accessoryType", type);
            request.setAttribute("accessoryBrand", brand);
            request.setAttribute("accessoryPrice", price);
            request.setAttribute("currentPage", page);
            request.setAttribute("tongSoTrang", totalPages);

            request.getRequestDispatcher("accessories-list.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        } finally {
            JDBCUtil.closeConnection(conn);
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}