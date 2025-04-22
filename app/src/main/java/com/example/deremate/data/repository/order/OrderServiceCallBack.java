package com.example.deremate.data.repository.order;

import com.example.deremate.Model.Order;

import java.util.List;

public interface OrderServiceCallBack {
    void onSuccess(List<Order> orders);
    void onError(Throwable error);

}
