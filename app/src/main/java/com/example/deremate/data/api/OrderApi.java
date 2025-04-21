package com.example.deremate.data.api;

import com.example.deremate.data.api.model.OrderModelListResponse;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OrderApi {

    @GET("orders") //
    Call <List<OrderModelListResponse>> getOrders();

    @GET("orders/pendings")
    Call <List<OrderModelListResponse>> getPendingOrders();

    @GET("orders/history")
    Call<List<OrderModelListResponse>> getHistoryOrders(@Query("riderId") String riderId);
}

