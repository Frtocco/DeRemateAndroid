package com.example.deremate.data.repository.coment;

import com.example.deremate.data.repository.order.OrderServiceCallBack;

public interface ComentRepository {
    void getCoemnt(String orderId, ComentServiceCallback callBack);
}
