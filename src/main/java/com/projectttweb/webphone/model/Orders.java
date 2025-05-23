package com.projectttweb.webphone.model;

import java.sql.Date;

public class Orders {
    private String orderID;
    private Date ordersDate;
    private User user;
    private String status;
    private double totalAmount;
    private String shippingAddress;
    private String phone;
    private int isCheck;
    private String idNV;
    private Date dateTK;

    public Orders(String orderID, Date ordersDate, User user, String status, double totalAmount, String shippingAddress,
                  String phone) {
        this.orderID = orderID;
        this.ordersDate = ordersDate;
        this.user = user;
        this.status = status;
        this.totalAmount = totalAmount;
        this.shippingAddress = shippingAddress;
        this.phone = phone;
    }

    public Orders(String orderID, Date ordersDate, User user, String status, double totalAmount, String shippingAddress, String phone, int isCheck) {
        this.orderID = orderID;
        this.ordersDate = ordersDate;
        this.user = user;
        this.status = status;
        this.totalAmount = totalAmount;
        this.shippingAddress = shippingAddress;
        this.phone = phone;
        this.isCheck = isCheck;

    }

    public Orders(String orderID, Date ordersDate, User user, String status, double totalAmount, String shippingAddress, String phone, int isCheck, String idNV, Date dateTK) {
        this.orderID = orderID;
        this.ordersDate = ordersDate;
        this.user = user;
        this.status = status;
        this.totalAmount = totalAmount;
        this.shippingAddress = shippingAddress;
        this.phone = phone;
        this.isCheck = isCheck;
        this.idNV = idNV;
        this.dateTK = dateTK;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrdersDate() {
        return ordersDate;
    }

    public void setOrdersDate(Date ordersDate) {
        this.ordersDate = ordersDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }

    public String getIdNV() {
        return idNV;
    }

    public void setIdNV(String idNV) {
        this.idNV = idNV;
    }

    public Date getDateTK() {
        return dateTK;
    }

    public void setDateTK(Date dateTK) {
        this.dateTK = dateTK;
    }
}
