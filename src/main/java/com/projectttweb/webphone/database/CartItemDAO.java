package com.projectttweb.webphone.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.projectttweb.webphone.model.Cart;
import com.projectttweb.webphone.model.CartItem;
import com.projectttweb.webphone.model.Product;

public class CartItemDAO implements DAOInterface<CartItem> {

    @Override
    public ArrayList<CartItem> selectAll() {
        ArrayList<CartItem> items = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM cart_items";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String cartItemID = rs.getString("cartItemID");
                String cartID = rs.getString("cartID");
                String productID = rs.getString("productID");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                Timestamp addedAt = rs.getTimestamp("addedAt");

                CartDAO cartDAO = new CartDAO();
                Cart cart = new Cart();
                cart.setCartID(cartID);
                cart = cartDAO.selectById(cart);

                ProductDao productDAO = new ProductDao();
                Product product = productDAO.selectProByID2(productID);

                CartItem item = new CartItem(cartItemID, cart, product, quantity, price, addedAt);
                items.add(item);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public CartItem selectById(CartItem t) {
        CartItem item = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM cart_items WHERE cartItemID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getCartItemID());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String cartItemID = rs.getString("cartItemID");
                String cartID = rs.getString("cartID");
                String productID = rs.getString("productID");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                Timestamp addedAt = rs.getTimestamp("addedAt");

                CartDAO cartDAO = new CartDAO();
                Cart cart = new Cart();
                cart.setCartID(cartID);
                cart = cartDAO.selectById(cart);

                ProductDao productDAO = new ProductDao();
                Product product = productDAO.selectProByID2(productID);

                item = new CartItem(cartItemID, cart, product, quantity, price, addedAt);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public int insert(CartItem t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO cart_items (cartItemID, cartID, productID, quantity, price, addedAt) VALUES (?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getCartItemID());
            st.setString(2, t.getCart().getCartID());
            st.setString(3, t.getProduct().getProductID());
            st.setInt(4, t.getQuantity());
            st.setDouble(5, t.getPrice());
            st.setTimestamp(6, t.getAddedAt());

            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int insertAll(ArrayList<CartItem> arr) {
        int count = 0;
        for (CartItem item : arr) {
            count += this.insert(item);
        }
        return count;
    }

    @Override
    public int delete(CartItem t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM cart_items WHERE cartItemID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getCartItemID());

            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int deleteAll(ArrayList<CartItem> arr) {
        int count = 0;
        for (CartItem item : arr) {
            count += this.delete(item);
        }
        return count;
    }

    @Override
    public int update(CartItem t) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE cart_items SET quantity=?, price=? WHERE cartItemID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getQuantity());
            st.setDouble(2, t.getPrice());
            st.setString(3, t.getCartItemID());

            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // Additional methods
    public ArrayList<CartItem> getItemsByCart(String cartID) {
        ArrayList<CartItem> items = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM cart_items WHERE cartID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cartID);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String cartItemID = rs.getString("cartItemID");
                String productID = rs.getString("productID");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                Timestamp addedAt = rs.getTimestamp("addedAt");

                CartDAO cartDAO = new CartDAO();
                Cart cart = new Cart();
                cart.setCartID(cartID);
                cart = cartDAO.selectById(cart);

                ProductDao productDAO = new ProductDao();
                Product product = productDAO.selectProByID2(productID);

                CartItem item = new CartItem(cartItemID, cart, product, quantity, price, addedAt);
                items.add(item);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public CartItem getItemByProductAndCart(String productID, String cartID) {
        CartItem item = null;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM cart_items WHERE productID=? AND cartID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, productID);
            st.setString(2, cartID);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String cartItemID = rs.getString("cartItemID");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                Timestamp addedAt = rs.getTimestamp("addedAt");

                CartDAO cartDAO = new CartDAO();
                Cart cart = new Cart();
                cart.setCartID(cartID);
                cart = cartDAO.selectById(cart);

                ProductDao productDAO = new ProductDao();
                Product product = productDAO.selectProByID2(productID);

                item = new CartItem(cartItemID, cart, product, quantity, price, addedAt);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public int deleteAllItemsFromCart(String cartID) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM cart_items WHERE cartID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cartID);

            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // New method to check if product exists in cart
    public boolean checkProductExistInCart(String productID, String cartID) {
        boolean exists = false;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM cart_items WHERE productID=? AND cartID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, productID);
            st.setString(2, cartID);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                exists = true;
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    // New method to update quantity of existing item
    public int updateQuantity(String cartItemID, int newQuantity) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE cart_items SET quantity=? WHERE cartItemID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, newQuantity);
            st.setString(2, cartItemID);

            result = st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // New method to calculate total price of a cart
    public double calculateCartTotal(String cartID) {
        double total = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT SUM(price * quantity) as total FROM cart_items WHERE cartID=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, cartID);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }
}