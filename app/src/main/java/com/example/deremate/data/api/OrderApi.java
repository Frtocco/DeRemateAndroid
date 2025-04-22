package com.example.deremate.data.api;

import com.example.deremate.data.api.model.OrderModelListResponse;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface OrderApi {

    @GET("orders") //
    Call <List<OrderModelListResponse>> getOrders();

    @GET("orders/pendings")
    Call <List<OrderModelListResponse>> getPendingOrders();

}
