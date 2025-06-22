package com.example.deremate.activities.historyOrders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.deremate.Model.Order;
import com.example.deremate.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private final List<Order> orders;
    private OnOpinionClickListener opinionClickListener;

    public OrderAdapter(List<Order> orders) {
        this.orders = orders;
    }

    public void setOpinionClickListener(OnOpinionClickListener listener) {
        this.opinionClickListener = listener;
    }


    @Override
    public OrderViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder( OrderViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.txtOrderId.setText("ID: " + order.getOrderId());
        holder.txtStatus.setText("Estado: " + order.getStatus());
        holder.txtAddress.setText("Dirección: " + order.getAddress());

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

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView txtOrderId, txtStatus, txtAddress;
        Button btnVerOpinion;

        public OrderViewHolder( View itemView) {
            super(itemView);
            txtOrderId = itemView.findViewById(R.id.txtOrderId);
            txtStatus = itemView.findViewById(R.id.txtStatus);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            btnVerOpinion = itemView.findViewById(R.id.btnVerOpinion); // esto es clave
        }
    }

    public interface OnOpinionClickListener {
        void onOpinionClick(Order order);
    }
}
