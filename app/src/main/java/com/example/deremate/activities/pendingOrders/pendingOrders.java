package com.example.deremate.activities.pendingOrders;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deremate.Model.Order;
import com.example.deremate.R;
import com.example.deremate.data.repository.order.OrderRetrofitRepository;
import com.example.deremate.data.repository.order.OrderServiceCallBack;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
@AndroidEntryPoint
public class pendingOrders extends AppCompatActivity {

    @Inject
    OrderRetrofitRepository orderRepository;

    private RecyclerView recyclerView;
    private OrderAdapter orderAdapter;
    private final List<Order> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_orders);

        recyclerView = findViewById(R.id.recyclerOrders);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        orderAdapter = new OrderAdapter(orderList);
        recyclerView.setAdapter(orderAdapter);

        cargarPedidos();
    }

    private void cargarPedidos() {
        orderRepository.getPendingOrders(new OrderServiceCallBack() {
            @Override
            public void onSuccess(List<Order> orders) {
                runOnUiThread(() -> {
                    orderList.clear();
                    orderList.addAll(orders);
                    orderAdapter.notifyDataSetChanged();
                });
            }

            @Override
            public void onError(Throwable t) {
                Log.e("ACTIVITY", "Error al obtener pedidos pendientes", t);
            }
        });
    }
}
