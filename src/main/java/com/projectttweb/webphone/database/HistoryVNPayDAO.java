package com.projectttweb.webphone.database;

import com.projectttweb.webphone.model.HistoryVNPay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class HistoryVNPayDAO implements DAOInterface<HistoryVNPay> {
    @Override
    public ArrayList<HistoryVNPay> selectAll() {
        return null;
    }

    @Override
    public HistoryVNPay selectById(HistoryVNPay historyVNPay) {
        return null;
    }

    @Override
    public int insert(HistoryVNPay historyVNPay) {
        return 0;
    }

    @Override
    public int insertAll(ArrayList<HistoryVNPay> arr) {
        return 0;
    }

    @Override
    public int delete(HistoryVNPay historyVNPay) {
        return 0;
    }

    @Override
    public int deleteAll(ArrayList<HistoryVNPay> arr) {
        return 0;
    }

    @Override
    public int update(HistoryVNPay historyVNPay) {
        return 0;
    }

    public int insertNew(HistoryVNPay historyVNPay) {
        int res = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO historyvnpay (id, vnp_Amount, vnp_BankCode, vnp_BankTranNo, vnp_CardType, vnp_OrderInfo, vnp_PayDate, vnp_ResponseCode, orderID) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, historyVNPay.getId());
            ps.setString(2, historyVNPay.getVnp_Amount());
            ps.setString(3, historyVNPay.getVnp_BankCode());
            ps.setString(4, historyVNPay.getVnp_BankTranNo());
            ps.setString(5, historyVNPay.getVnp_CardType());
            ps.setString(6, historyVNPay.getVnp_OrderInfo());
            ps.setString(7, historyVNPay.getVnp_PayDate());
            ps.setString(8, historyVNPay.getVnp_ResponseCode());
            ps.setString(9, historyVNPay.getOrderID());
            res = ps.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public String selectIDCur() {
        // TODO Auto-generated method stub
        String ans = "";
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM historyvnpay ORDER BY id DESC LIMIT 1";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            // ProductDao proDAO = new ProductDao();
            // OrdersDAO orderDAO = new OrdersDAO();
            while (rs.next()) {
                String id = rs.getString("id");
//				int quantity = rs.getInt("quantity");
//				String orderID = rs.getString("orderID");
//				String productID = rs.getString("productID");
//				double unitPrice = rs.getDouble("unitPrice");
//				OrderDetails orderDetails = new OrderDetails(orderDetailsID, quantity, proDAO.selectProByID(productID), orderDAO.selectOrderByID(orderID), unitPrice);
                ans = id;
                break;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return ans;

    }
    }

