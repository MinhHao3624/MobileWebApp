package com.projectttweb.webphone.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.projectttweb.webphone.database.CartDAO;
import com.projectttweb.webphone.database.CartItemDAO;
import com.projectttweb.webphone.database.ProductDao;
import com.projectttweb.webphone.model.Cart;
import com.projectttweb.webphone.model.CartItem;
import com.projectttweb.webphone.model.Product;
import com.projectttweb.webphone.model.User;

public class CartService {

    private CartDAO cartDAO = new CartDAO();
    private CartItemDAO cartItemDAO = new CartItemDAO();
    private ProductDao productDAO = new ProductDao();

    // Lấy cart đang hoạt động của người dùng hoặc tạo mới nếu chưa có
    public Cart getOrCreateActiveCart(User user) {
        Cart cart = cartDAO.getActiveCartByUser(user.getUserID());
        if (cart == null) {
            String newCartID = generateNewCartID(); // bạn có thể tạo logic auto nếu muốn
            Timestamp now = new Timestamp(System.currentTimeMillis());
            cart = new Cart(newCartID, user, now, now, "active");
            cartDAO.insert(cart);
        }
        return cart;
    }

    // Thêm sản phẩm vào giỏ hàng (nếu đã tồn tại thì tăng số lượng)
    public void addProductToCart(User user, String productID, int quantity) {
        Cart cart = getOrCreateActiveCart(user);
        CartItem existingItem = cartItemDAO.getItemByProductAndCart(productID, cart.getCartID());

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartItemDAO.update(existingItem);
        } else {
            Product product = productDAO.selectProByID2(productID);
            String newCartItemID = generateNewCartItemID(); // hoặc dùng UUID/ngẫu nhiên
            double price = Double.parseDouble(product.getPrice().replace(".", ""));
            Timestamp now = new Timestamp(System.currentTimeMillis());

            CartItem newItem = new CartItem(newCartItemID, cart, product, quantity, price, now);
            cartItemDAO.insert(newItem);
        }

        // Cập nhật thời gian chỉnh sửa giỏ hàng
        cart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        cartDAO.update(cart);
    }

    // Lấy toàn bộ sản phẩm trong giỏ của người dùng
    public ArrayList<CartItem> getCartItems(User user) {
        Cart cart = cartDAO.getActiveCartByUser(user.getUserID());
        if (cart == null) return new ArrayList<>();
        return cartItemDAO.getItemsByCart(cart.getCartID());
    }

    // Xóa toàn bộ sản phẩm trong giỏ hàng
    public void clearCart(User user) {
        Cart cart = cartDAO.getActiveCartByUser(user.getUserID());
        if (cart != null) {
            cartItemDAO.deleteAllItemsFromCart(cart.getCartID());
            cart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            cartDAO.update(cart);
        }
    }

    // Bạn có thể thay thế các hàm này bằng logic sinh ID cụ thể tùy hệ thống
    private String generateNewCartID() {
        return "CART" + System.currentTimeMillis();
    }

    private String generateNewCartItemID() {
        return "ITEM" + System.currentTimeMillis();
    }
}
