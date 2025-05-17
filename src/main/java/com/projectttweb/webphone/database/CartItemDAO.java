package com.projectttweb.webphone.database;

import com.projectttweb.webphone.model.Cart;
import com.projectttweb.webphone.model.CartItem;
import com.projectttweb.webphone.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Logger;

public class CartItemDAO implements DAOInterface<CartItem> {

    private static final Logger LOGGER = Logger.getLogger(CartItemDAO.class.getName());

    @Override
    public ArrayList<CartItem> selectAll() {
        ArrayList<CartItem> items = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM cart_items")) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CartItem item = createCartItemFromResultSet(rs);
                items.add(item);
            }
            LOGGER.info("Retrieved " + items.size() + " cart items from database");
        } catch (Exception e) {
            LOGGER.severe("Error in selectAll: " + e.getMessage());
            e.printStackTrace();
        }
        return items;
    }

    public CartItem getItemByID(String cartItemID) {
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM cart_items WHERE cartItemID = ?")) {
            st.setString(1, cartItemID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                CartItem item = createCartItemFromResultSet(rs);
                LOGGER.info("Retrieved cart item with ID: " + cartItemID);
                return item;
            }
            LOGGER.info("No cart item found with ID: " + cartItemID);
            return null;
        } catch (Exception e) {
            LOGGER.severe("Error in getItemByID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CartItem selectById(CartItem t) {
        return getItemByID(t.getCartItemID());
    }

    @Override
    public int insert(CartItem t) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(
                     "INSERT INTO cart_items (cartItemID, cartID, productID, quantity, price, addedAt) VALUES (?,?,?,?,?,?)")) {
            st.setString(1, t.getCartItemID());
            st.setString(2, t.getCart().getCartID());
            st.setString(3, t.getProduct().getProductID());
            st.setInt(4, t.getQuantity());
            st.setDouble(5, t.getPrice());
            st.setTimestamp(6, t.getAddedAt());
            result = st.executeUpdate();
            LOGGER.info("Insert cart item with ID: " + t.getCartItemID() + " - Rows affected: " + result);
        } catch (Exception e) {
            LOGGER.severe("Error in insert: " + e.getMessage());
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
        LOGGER.info("Inserted " + count + " cart items");
        return count;
    }

    @Override
    public int delete(CartItem t) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement("DELETE FROM cart_items WHERE cartItemID = ?")) {
            st.setString(1, t.getCartItemID());
            result = st.executeUpdate();
            LOGGER.info("Delete cart item with ID: " + t.getCartItemID() + " - Rows affected: " + result);
        } catch (Exception e) {
            LOGGER.severe("Error in delete: " + e.getMessage());
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
        LOGGER.info("Deleted " + count + " cart items");
        return count;
    }

    @Override
    public int update(CartItem t) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(
                     "UPDATE cart_items SET quantity = ?, price = ? WHERE cartItemID = ?")) {
            st.setInt(1, t.getQuantity());
            st.setDouble(2, t.getPrice());
            st.setString(3, t.getCartItemID());
            result = st.executeUpdate();
            LOGGER.info("Update cart item with ID: " + t.getCartItemID() + " - Rows affected: " + result);
        } catch (Exception e) {
            LOGGER.severe("Error in update: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<CartItem> getItemsByCart(String cartID) {
        ArrayList<CartItem> items = new ArrayList<>();
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM cart_items WHERE cartID = ?")) {
            st.setString(1, cartID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CartItem item = createCartItemFromResultSet(rs);
                items.add(item);
            }
            LOGGER.info("Retrieved " + items.size() + " cart items for cartID: " + cartID);
        } catch (Exception e) {
            LOGGER.severe("Error in getItemsByCart: " + e.getMessage());
            e.printStackTrace();
        }
        return items;
    }

    public CartItem getItemByProductAndCart(String productID, String cartID) {
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(
                     "SELECT * FROM cart_items WHERE productID = ? AND cartID = ?")) {
            st.setString(1, productID);
            st.setString(2, cartID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                CartItem item = createCartItemFromResultSet(rs);
                LOGGER.info("Retrieved cart item for productID: " + productID + ", cartID: " + cartID);
                return item;
            }
            LOGGER.info("No cart item found for productID: " + productID + ", cartID: " + cartID);
            return null;
        } catch (Exception e) {
            LOGGER.severe("Error in getItemByProductAndCart: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public int deleteAllItemsFromCart(String cartID) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement("DELETE FROM cart_items WHERE cartID = ?")) {
            st.setString(1, cartID);
            result = st.executeUpdate();
            LOGGER.info("Delete all items from cart with ID: " + cartID + " - Rows affected: " + result);
        } catch (Exception e) {
            LOGGER.severe("Error in deleteAllItemsFromCart: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public boolean checkProductExistInCart(String productID, String cartID) {
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(
                     "SELECT 1 FROM cart_items WHERE productID = ? AND cartID = ?")) {
            st.setString(1, productID);
            st.setString(2, cartID);
            ResultSet rs = st.executeQuery();
            boolean exists = rs.next();
            LOGGER.info("Check productID: " + productID + " in cartID: " + cartID + " - Exists: " + exists);
            return exists;
        } catch (Exception e) {
            LOGGER.severe("Error in checkProductExistInCart: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public int updateQuantity(String cartItemID, int newQuantity) {
        int result = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(
                     "UPDATE cart_items SET quantity = ? WHERE cartItemID = ?")) {
            st.setInt(1, newQuantity);
            st.setString(2, cartItemID);
            result = st.executeUpdate();
            LOGGER.info("Update quantity for cartItemID: " + cartItemID + " to: " + newQuantity + " - Rows affected: " + result);
        } catch (Exception e) {
            LOGGER.severe("Error in updateQuantity: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public double calculateCartTotal(String cartID) {
        double total = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(
                     "SELECT SUM(price * quantity) as total FROM cart_items WHERE cartID = ?")) {
            st.setString(1, cartID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
            LOGGER.info("Calculated total for cartID: " + cartID + " - Total: " + total);
        } catch (Exception e) {
            LOGGER.severe("Error in calculateCartTotal: " + e.getMessage());
            e.printStackTrace();
        }
        return total;
    }

    public double getItemTotal(String cartItemID) {
        double total = 0;
        try (Connection con = JDBCUtil.getConnection();
             PreparedStatement st = con.prepareStatement(
                     "SELECT price * quantity as total FROM cart_items WHERE cartItemID = ?")) {
            st.setString(1, cartItemID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("total");
            }
            LOGGER.info("Calculated item total for cartItemID: " + cartItemID + " - Total: " + total);
        } catch (Exception e) {
            LOGGER.severe("Error in getItemTotal: " + e.getMessage());
            e.printStackTrace();
        }
        return total;
    }

    private CartItem createCartItemFromResultSet(ResultSet rs) throws Exception {
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

        return new CartItem(cartItemID, cart, product, quantity, price, addedAt);
    }
}