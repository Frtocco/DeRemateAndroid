package com.example.deremate.data.repository.order;

public interface OrderRepository {
    void getPendingOrders(OrderServiceCallBack callBack);
}
