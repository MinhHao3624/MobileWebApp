package com.projectttweb.webphone.database;

import com.projectttweb.webphone.model.Cart;
import com.projectttweb.webphone.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Logger;

public class CartDAO implements DAOInterface<Cart> {

    private static final Logger LOGGER = Logger.getLogger(CartDAO.class.getName());

    @Override
    public ArrayList<Cart> selectAll() {
        ArrayList<Cart> carts = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM cart")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cart cart = createCartFromResultSet(rs);
                carts.add(cart);
            }
            LOGGER.info("Retrieved " + carts.size() + " carts from database");
        } catch (Exception e) {
            LOGGER.severe("Error in selectAll: " + e.getMessage());
            e.printStackTrace();
        }
        return carts;
    }

    @Override
    public Cart selectById(Cart t) {
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM cart WHERE cartID=?")) {
            st.setString(1, t.getCartID());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Cart cart = createCartFromResultSet(rs);
                LOGGER.info("Retrieved cart with ID: " + t.getCartID());
                return cart;
            }
            LOGGER.info("No cart found with ID: " + t.getCartID());
            return null;
        } catch (Exception e) {
            LOGGER.severe("Error in selectById: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int insert(Cart t) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(
                     "INSERT INTO cart (cartID, userID, createdAt, updatedAt, status) VALUES (?,?,?,?,?)")) {
            st.setString(1, t.getCartID());
            st.setString(2, t.getUser().getUserID());
            st.setTimestamp(3, t.getCreatedAt());
            st.setTimestamp(4, t.getUpdatedAt());
            st.setString(5, t.getStatus());
            result = st.executeUpdate();
            LOGGER.info("Insert cart with ID: " + t.getCartID() + " - Rows affected: " + result);
        } catch (Exception e) {
            LOGGER.severe("Error in insert: " + e.getMessage());
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
        LOGGER.info("Inserted " + count + " carts");
        return count;
    }

    @Override
    public int delete(Cart t) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement("DELETE FROM cart WHERE cartID=?")) {
            st.setString(1, t.getCartID());
            result = st.executeUpdate();
            LOGGER.info("Delete cart with ID: " + t.getCartID() + " - Rows affected: " + result);
        } catch (Exception e) {
            LOGGER.severe("Error in delete: " + e.getMessage());
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
        LOGGER.info("Deleted " + count + " carts");
        return count;
    }

    @Override
    public int update(Cart t) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(
                     "UPDATE cart SET userID=?, updatedAt=?, status=? WHERE cartID=?")) {
            st.setString(1, t.getUser().getUserID());
            st.setTimestamp(2, t.getUpdatedAt());
            st.setString(3, t.getStatus());
            st.setString(4, t.getCartID());
            result = st.executeUpdate();
            LOGGER.info("Update cart with ID: " + t.getCartID() + " - Rows affected: " + result);
        } catch (Exception e) {
            LOGGER.severe("Error in update: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Cart getActiveCartByUser(String userID) {
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM cart WHERE userID=? AND status='active'")) {
            st.setString(1, userID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Cart cart = createCartFromResultSet(rs);
                LOGGER.info("Retrieved active cart for userID: " + userID);
                return cart;
            }
            LOGGER.info("No active cart found for userID: " + userID);
            return null;
        } catch (Exception e) {
            LOGGER.severe("Error in getActiveCartByUser: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public int deactivateCart(String cartID) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(
                     "UPDATE cart SET status='checked_out', updatedAt=CURRENT_TIMESTAMP WHERE cartID=?")) {
            st.setString(1, cartID);
            result = st.executeUpdate();
            LOGGER.info("Deactivate cart with ID: " + cartID + " - Rows affected: " + result);
        } catch (Exception e) {
            LOGGER.severe("Error in deactivateCart: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    private Cart createCartFromResultSet(ResultSet rs) throws Exception {
        String cartID = rs.getString("cartID");
        String userID = rs.getString("userID");
        Timestamp createdAt = rs.getTimestamp("createdAt");
        Timestamp updatedAt = rs.getTimestamp("updatedAt");
        String status = rs.getString("status");

        UserDao userDAO = new UserDao();
        User user = userDAO.selectById3(userID);

        return new Cart(cartID, user, createdAt, updatedAt, status);
    }
}