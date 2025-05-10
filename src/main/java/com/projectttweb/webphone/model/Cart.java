package com.projectttweb.webphone.model;

import java.sql.Timestamp;

public class Cart {
    private String cartID;
    private User user;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String status;

    public Cart() {
    }

    public Cart(String cartID, User user, Timestamp createdAt, Timestamp updatedAt, String status) {
        this.cartID = cartID;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
    }

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
