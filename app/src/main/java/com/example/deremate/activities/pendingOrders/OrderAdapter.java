package com.example.deremate.activities.pendingOrders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deremate.Model.Order;
import com.example.deremate.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private final List<Order> orders;

    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.txtOrderId.setText("ID: " + order.getOrderId());
        holder.txtStatus.setText("Estado: " + order.getStatus());
        holder.txtAddress.setText("Dirección: " + order.getAddress());
<<<<<<< Updated upstream
=======

        boolean isCompleted = "Completed".equalsIgnoreCase(order.getStatus());

        if (isCompleted) {
            holder.btnVerOpinion.setVisibility(View.VISIBLE);
            holder.btnVerOpinion.setOnClickListener(v -> {
                if (opinionClickListener != null) {
                    opinionClickListener.onOpinionClick(order);
                }
            });
        } else {
            holder.btnVerOpinion.setVisibility(View.GONE);
        }
>>>>>>> Stashed changes
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView txtOrderId, txtStatus, txtAddress;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrderId = itemView.findViewById(R.id.txtOrderId);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtAddress = itemView.findViewById(R.id.txtAddress);
        }
    }
}

