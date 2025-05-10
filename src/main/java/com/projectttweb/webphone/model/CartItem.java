package com.projectttweb.webphone.model;

import java.sql.Timestamp;

public class CartItem {
    private String cartItemID;
    private Cart cart;
    private Product product;
    private int quantity;
    private double price;
    private Timestamp addedAt;

    public CartItem() {
    }

    public CartItem(String cartItemID, Cart cart, Product product, int quantity, double price, Timestamp addedAt) {
        this.cartItemID = cartItemID;
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.addedAt = addedAt;
    }

    public String getCartItemID() {
        return cartItemID;
    }

    public void setCartItemID(String cartItemID) {
        this.cartItemID = cartItemID;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }
}
