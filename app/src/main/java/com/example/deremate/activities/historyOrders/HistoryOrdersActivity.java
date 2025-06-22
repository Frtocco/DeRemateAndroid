package com.example.deremate.activities.historyOrders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deremate.Model.Order;
import com.example.deremate.R;
import com.example.deremate.activities.menu.MenuActivity;
import com.example.deremate.data.repository.order.OrderRetrofitRepository;
import com.example.deremate.data.repository.order.OrderServiceCallBack;
import com.example.deremate.fragments.OpinionFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HistoryOrdersActivity extends AppCompatActivity {
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

        orderAdapter.setOpinionClickListener(order -> {
            Log.d("ORDER", order.getComentPunt()+ order.getComent());
            OpinionFragment.newInstance(order.getComentPunt(), order.getComent(),order.getImgLink())
                    .show(getSupportFragmentManager(), "OpinionFragment");
        });

        recyclerView.setAdapter(orderAdapter);

        String userId = getIntent().getStringExtra("userId");
        cargarPedidos(userId);

        Button btnVolver = findViewById(R.id.btnVolverMenu);
        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(HistoryOrdersActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void cargarPedidos(String riderId) {
        orderRepository.getHistoryOrders(riderId, new OrderServiceCallBack() {
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
                Log.e("ACTIVITY", "Error al obtener historial de pedidos", t);
            }
        });
    }
}
