package com.example.deremate.data.repository.order;

public interface OrderRepository {
    void getPendingOrders(OrderServiceCallBack callBack);
    void getHistoryOrders(String riderId, OrderServiceCallBack callback);
}