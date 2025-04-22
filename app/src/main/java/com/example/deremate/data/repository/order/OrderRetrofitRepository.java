package com.example.deremate.data.repository.order;

import android.util.Log;

import com.example.deremate.Model.Order;
import com.example.deremate.data.api.OrderApi;
import com.example.deremate.data.api.model.OrderModel;
import com.example.deremate.data.api.model.OrderModelListResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class OrderRetrofitRepository implements OrderRepository {
    private final OrderApi orderApi;

    @Inject
    public OrderRetrofitRepository(OrderApi orderApi) {
        this.orderApi = orderApi;
    }
    @Override
    public void getPendingOrders(final OrderServiceCallBack callback) {

        orderApi.getPendingOrders().enqueue(new Callback<List<OrderModelListResponse>>(){

            @Override
            public void onResponse(Call<List<OrderModelListResponse>> call, Response<List<OrderModelListResponse>> response) {

                if(response.isSuccessful()){
                    List<OrderModelListResponse> results = response.body();
                    List<Order> pendingOrders = new ArrayList<>();

                    for (OrderModelListResponse item : results) {
                        pendingOrders.add(new Order(
                                item.getOrderId(),
                                item.getRiderId(),
                                item.getStatus(),
                                item.getAddress()
                        ));
                    }

                    callback.onSuccess(pendingOrders);
                } else {
                    callback.onError(new Exception("Error fetching Pending Orders list"));
                }

            }

            @Override
            public void onFailure(Call<List<OrderModelListResponse>> call, Throwable t) {
                Log.e("API_ERROR", "Fallo la llamada HTTP: " + t.getMessage(), t);
                callback.onError(t);
            }

        });

    }

}
