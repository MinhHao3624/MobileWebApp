package com.projectttweb.webphone.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.projectttweb.webphone.model.Cart;
import com.projectttweb.webphone.model.User;
import com.projectttweb.webphone.database.UserDao;

public class CartDAO implements DAOInterface<Cart> {

    @Override
    public ArrayList<Cart> selectAll() {
        ArrayList<Cart> carts = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM cart";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String cartID = rs.getString("cartID");
                String userID = rs.getString("userID");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                Timestamp updatedAt = rs.getTimestamp("updatedAt");
                String status = rs.getString("status");

                UserDao userDAO = new UserDao();
                User user = userDAO.selectById3(userID);

                Cart cart = new Cart(cartID, user, createdAt, updatedAt, status);
                carts.add(cart);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carts;
    }

    @Override
    public Cart selectById(Cart t) {
        Cart cart = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM cart WHERE cartID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getCartID());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String cartID = rs.getString("cartID");
                String userID = rs.getString("userID");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                Timestamp updatedAt = rs.getTimestamp("updatedAt");
                String status = rs.getString("status");

                UserDao userDAO = new UserDao();
                User user = userDAO.selectById3(userID);

                cart = new Cart(cartID, user, createdAt, updatedAt, status);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public int insert(Cart t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO cart (cartID, userID, createdAt, updatedAt, status) VALUES (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getCartID());
            st.setString(2, t.getUser().getUserID());
            st.setTimestamp(3, t.getCreatedAt());
            st.setTimestamp(4, t.getUpdatedAt());
            st.setString(5, t.getStatus());

            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insertAll(ArrayList<Cart> arr) {
        int count = 0;
        for (Cart cart : arr) {
            count += this.insert(cart);
        }
        return count;
    }

    @Override
    public int delete(Cart t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM cart WHERE cartID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getCartID());

            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteAll(ArrayList<Cart> arr) {
        int count = 0;
        for (Cart cart : arr) {
            count += this.delete(cart);
        }
        return count;
    }

    @Override
    public int update(Cart t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE cart SET userID=?, updatedAt=?, status=? WHERE cartID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getUser().getUserID());
            st.setTimestamp(2, t.getUpdatedAt());
            st.setString(3, t.getStatus());
            st.setString(4, t.getCartID());

            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Additional methods
    public Cart getActiveCartByUser(String userID) {
        Cart cart = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM cart WHERE userID=? AND status='active'";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, userID);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String cartID = rs.getString("cartID");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                Timestamp updatedAt = rs.getTimestamp("updatedAt");
                String status = rs.getString("status");

                UserDao userDAO = new UserDao();
                User user = userDAO.selectById3(userID);

                cart = new Cart(cartID, user, createdAt, updatedAt, status);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    public int deactivateCart(String cartID) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE cart SET status='checked_out', updatedAt=CURRENT_TIMESTAMP WHERE cartID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cartID);

            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}