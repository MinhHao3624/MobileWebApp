package com.projectttweb.webphone.service;

import com.projectttweb.webphone.database.CartDAO;
import com.projectttweb.webphone.database.CartItemDAO;
import com.projectttweb.webphone.database.ProductDao;
import com.projectttweb.webphone.model.Cart;
import com.projectttweb.webphone.model.CartItem;
import com.projectttweb.webphone.model.Product;
import com.projectttweb.webphone.model.User;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;

public class CartService {

    private static final Logger LOGGER = Logger.getLogger(CartService.class.getName());
    private CartDAO cartDAO = new CartDAO();
    private CartItemDAO cartItemDAO = new CartItemDAO();
    private ProductDao productDAO = new ProductDao();

    // Lấy cart đang hoạt động của người dùng hoặc tạo mới nếu chưa có
    public Cart getOrCreateActiveCart(User user) {
        if (user == null) {
            LOGGER.severe("User is null in getOrCreateActiveCart");
            throw new IllegalArgumentException("Người dùng không hợp lệ.");
        }
        LOGGER.info("Getting active cart for userID: " + user.getUserID());
        Cart cart = cartDAO.getActiveCartByUser(user.getUserID());
        if (cart == null) {
            String newCartID = generateNewCartID();
            Timestamp now = new Timestamp(System.currentTimeMillis());
            cart = new Cart(newCartID, user, now, now, "active");
            int result = cartDAO.insert(cart);
            if (result <= 0) {
                LOGGER.severe("Failed to insert cart with ID: " + newCartID);
                throw new RuntimeException("Lỗi khi tạo giỏ hàng.");
            }
            LOGGER.info("Created new cart with ID: " + newCartID);
        }
        return cart;
    }

    // Thêm sản phẩm vào giỏ hàng (nếu đã tồn tại thì tăng số lượng)
    public void addProductToCart(User user, String productID, int quantity) {
        if (user == null) {
            LOGGER.severe("User is null in addProductToCart");
            throw new IllegalArgumentException("Người dùng không hợp lệ.");
        }
        if (quantity <= 0) {
            LOGGER.warning("Invalid quantity: " + quantity + " for productID: " + productID);
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
        }
        Cart cart = getOrCreateActiveCart(user);
        CartItem existingItem = cartItemDAO.getItemByProductAndCart(productID, cart.getCartID());

        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + quantity;
            Product product = existingItem.getProduct();
            if (newQuantity > product.getStockQuantity()) {
                LOGGER.warning("Quantity exceeds stock for productID: " + productID + " - Stock: " + product.getStockQuantity());
                throw new IllegalArgumentException("Số lượng vượt quá tồn kho: " + product.getStockQuantity());
            }
            existingItem.setQuantity(newQuantity);
            int result = cartItemDAO.update(existingItem);
            if (result <= 0) {
                LOGGER.severe("Failed to update cart item with ID: " + existingItem.getCartItemID());
                throw new RuntimeException("Lỗi khi cập nhật số lượng sản phẩm.");
            }
            LOGGER.info("Updated quantity for cartItemID: " + existingItem.getCartItemID() + " to: " + newQuantity);
        } else {
            Product product = productDAO.selectProByID2(productID);
            if (product == null) {
                LOGGER.severe("Product not found for productID: " + productID);
                throw new IllegalArgumentException("Sản phẩm không tồn tại.");
            }
            if (quantity > product.getStockQuantity()) {
                LOGGER.warning("Quantity exceeds stock for productID: " + productID + " - Stock: " + product.getStockQuantity());
                throw new IllegalArgumentException("Số lượng vượt quá tồn kho: " + product.getStockQuantity());
            }
            String newCartItemID = generateNewCartItemID();
            double price = parsePrice(product.getPrice());
            Timestamp now = new Timestamp(System.currentTimeMillis());

            CartItem newItem = new CartItem(newCartItemID, cart, product, quantity, price, now);
            int result = cartItemDAO.insert(newItem);
            if (result <= 0) {
                LOGGER.severe("Failed to insert cart item with ID: " + newCartItemID);
                throw new RuntimeException("Lỗi khi thêm sản phẩm vào giỏ hàng.");
            }
            LOGGER.info("Added new cart item with ID: " + newCartItemID);
        }

        // Cập nhật thời gian chỉnh sửa giỏ hàng
        cart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        int result = cartDAO.update(cart);
        if (result <= 0) {
            LOGGER.severe("Failed to update cart with ID: " + cart.getCartID());
            throw new RuntimeException("Lỗi khi cập nhật giỏ hàng.");
        }
    }

    // Lấy toàn bộ sản phẩm trong giỏ của người dùng
    public ArrayList<CartItem> getCartItems(User user) {
        if (user == null) {
            LOGGER.severe("User is null in getCartItems");
            return new ArrayList<>();
        }
        Cart cart = cartDAO.getActiveCartByUser(user.getUserID());
        if (cart == null) {
            LOGGER.info("No active cart found for userID: " + user.getUserID());
            return new ArrayList<>();
        }
        ArrayList<CartItem> items = cartItemDAO.getItemsByCart(cart.getCartID());
        LOGGER.info("Retrieved " + items.size() + " cart items for cartID: " + cart.getCartID());
        return items;
    }

    // Xóa toàn bộ sản phẩm trong giỏ hàng
    public void clearCart(User user) {
        if (user == null) {
            LOGGER.severe("User is null in clearCart");
            return;
        }
        Cart cart = cartDAO.getActiveCartByUser(user.getUserID());
        if (cart != null) {
            int result = cartItemDAO.deleteAllItemsFromCart(cart.getCartID());
            if (result < 0) {
                LOGGER.severe("Failed to clear cart with ID: " + cart.getCartID());
                throw new RuntimeException("Lỗi khi xóa giỏ hàng.");
            }
            cart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            result = cartDAO.update(cart);
            if (result <= 0) {
                LOGGER.severe("Failed to update cart with ID: " + cart.getCartID());
                throw new RuntimeException("Lỗi khi cập nhật giỏ hàng.");
            }
            LOGGER.info("Cleared cart with ID: " + cart.getCartID());
        }
    }

    // Cập nhật số lượng của một mục trong giỏ hàng
    public void updateCartItem(User user, String cartItemID, int quantity) {
        if (user == null) {
            LOGGER.severe("User is null in updateCartItem");
            throw new IllegalArgumentException("Người dùng không hợp lệ.");
        }
        if (quantity <= 0) {
            LOGGER.warning("Invalid quantity: " + quantity + " for cartItemID: " + cartItemID);
            throw new IllegalArgumentException("Số lượng phải lớn hơn 0.");
        }
        Cart cart = getOrCreateActiveCart(user);
        CartItem item = cartItemDAO.getItemByID(cartItemID);
        if (item == null || !item.getCart().getCartID().equals(cart.getCartID())) {
            LOGGER.warning("Cart item not found or does not belong to user. cartItemID: " + cartItemID + ", userID: " + user.getUserID());
            throw new IllegalArgumentException("Mục giỏ hàng không tồn tại hoặc không thuộc người dùng.");
        }

        Product product = item.getProduct();
        if (quantity > product.getStockQuantity()) {
            LOGGER.warning("Quantity exceeds stock for cartItemID: " + cartItemID + " - Stock: " + product.getStockQuantity());
            throw new IllegalArgumentException("Số lượng vượt quá tồn kho: " + product.getStockQuantity());
        }
        item.setQuantity(quantity);
        int result = cartItemDAO.update(item);
        if (result <= 0) {
            LOGGER.severe("Failed to update cart item with ID: " + cartItemID);
            throw new RuntimeException("Lỗi khi cập nhật số lượng sản phẩm.");
        }
        cart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        result = cartDAO.update(cart);
        if (result <= 0) {
            LOGGER.severe("Failed to update cart with ID: " + cart.getCartID());
            throw new RuntimeException("Lỗi khi cập nhật giỏ hàng.");
        }
        LOGGER.info("Updated cart item with ID: " + cartItemID + " to quantity: " + quantity);
    }

    // Xóa một mục khỏi giỏ hàng
    public void removeCartItem(User user, String cartItemID) {
        if (user == null) {
            LOGGER.severe("User is null in removeCartItem");
            throw new IllegalArgumentException("Người dùng không hợp lệ.");
        }
        Cart cart = getOrCreateActiveCart(user);
        CartItem item = cartItemDAO.getItemByID(cartItemID);
        if (item == null || !item.getCart().getCartID().equals(cart.getCartID())) {
            LOGGER.warning("Cart item not found or does not belong to user. cartItemID: " + cartItemID + ", userID: " + user.getUserID());
            throw new IllegalArgumentException("Mục giỏ hàng không tồn tại hoặc không thuộc người dùng.");
        }

        int result = cartItemDAO.delete(item);
        if (result <= 0) {
            LOGGER.severe("Failed to delete cart item with ID: " + cartItemID);
            throw new RuntimeException("Lỗi khi xóa mục giỏ hàng.");
        }
        cart.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        result = cartDAO.update(cart);
        if (result <= 0) {
            LOGGER.severe("Failed to update cart with ID: " + cart.getCartID());
            throw new RuntimeException("Lỗi khi cập nhật giỏ hàng.");
        }
        LOGGER.info("Removed cart item with ID: " + cartItemID);
    }

    // Tính tổng tiền của giỏ hàng
    public double calculateTotal(User user) {
        ArrayList<CartItem> items = getCartItems(user);
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        LOGGER.info("Calculated total for userID: " + (user != null ? user.getUserID() : "null") + " - Total: " + total);
        return total;
    }

    // Tính thành tiền của một mục trong giỏ hàng
    public double getItemTotal(String cartItemID) {
        CartItem item = cartItemDAO.getItemByID(cartItemID);
        if (item != null) {
            double total = item.getPrice() * item.getQuantity();
            LOGGER.info("Calculated item total for cartItemID: " + cartItemID + " - Total: " + total);
            return total;
        }
        LOGGER.warning("Cart item not found for cartItemID: " + cartItemID);
        throw new IllegalArgumentException("Mục giỏ hàng không tồn tại.");
    }

    // Phân tích giá sản phẩm
    private double parsePrice(String priceStr) {
        try {
            NumberFormat format = NumberFormat.getInstance(new Locale("vi", "VN"));
            Number number = format.parse(priceStr.replaceAll("[^0-9,.]", ""));
            return number.doubleValue();
        } catch (ParseException e) {
            LOGGER.severe("Error parsing price: " + priceStr + " - " + e.getMessage());
            throw new IllegalArgumentException("Giá sản phẩm không hợp lệ: " + priceStr, e);
        }
    }

    private String generateNewCartID() {
        return "CART" + System.currentTimeMillis();
    }

    private String generateNewCartItemID() {
        return "ITEM" + System.currentTimeMillis();
    }

    // Phương thức cũ để cập nhật CartItem (giữ lại để tương thích)
    public void updateCartItem(CartItem item) {
        int result = cartItemDAO.update(item);
        if (result <= 0) {
            LOGGER.severe("Failed to update cart item with ID: " + item.getCartItemID());
            throw new RuntimeException("Lỗi khi cập nhật mục giỏ hàng.");
        }
        LOGGER.info("Updated cart item with ID: " + item.getCartItemID());
    }

    // Phương thức cũ để xóa CartItem (giữ lại để tương thích)
    public void deleteCartItem(CartItem item) {
        int result = cartItemDAO.delete(item);
        if (result <= 0) {
            LOGGER.severe("Failed to delete cart item with ID: " + item.getCartItemID());
            throw new RuntimeException("Lỗi khi xóa mục giỏ hàng.");
        }
        LOGGER.info("Deleted cart item with ID: " + item.getCartItemID());
    }
}