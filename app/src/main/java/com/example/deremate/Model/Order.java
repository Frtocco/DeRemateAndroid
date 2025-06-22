package com.example.deremate.Model;

import android.util.Log;

public class Order {
    private String orderId;
    private String riderId;
    private String status;
    private String address;
    private String coment;
    private String comentPunt;

    public Order(String orderId, String riderId, String status, String address, String coment, String comentPunt) {
        this.orderId = orderId;
        this.riderId = riderId;
        this.status = status;
        this.address = address;
        this.coment = coment;
        this.comentPunt = comentPunt;
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

    public String getComent() {
        Log.d("ORDER","Valor: "+this.coment);
        return coment;
    }

    public float getComentPunt() {
        Log.d("ORDER","Valor: "+this.comentPunt);
        if (comentPunt == null || comentPunt.trim().isEmpty()) return 0f;
        try {
            return Float.parseFloat(comentPunt);
        } catch (NumberFormatException e) {
            return 0f;
        }
    }
}