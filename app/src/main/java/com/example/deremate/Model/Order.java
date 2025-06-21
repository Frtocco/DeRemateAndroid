package com.example.deremate.Model;

public class Order {
    private String orderId;
    private String riderId;
    private String status;
    private String address;

    public Order(String orderId, String riderId, String status, String address) {
        this.orderId = orderId;
        this.riderId = riderId;
        this.status = status;
        this.address = address;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRiderId() {
        return riderId;
    }

    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
<<<<<<< Updated upstream
=======

    public String getComent() {
        return coment;
    }

    public float getComentPunt() {
        if (comentPunt == null || comentPunt.trim().isEmpty()) return 0f;
        try {
            return Float.parseFloat(comentPunt);
        } catch (NumberFormatException e) {
            return 0f;
        }
    }
>>>>>>> Stashed changes
}
