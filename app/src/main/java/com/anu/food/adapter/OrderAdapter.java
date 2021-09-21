package com.anu.food.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.anu.food.DetailActivity;
import com.anu.food.R;
import com.anu.food.model.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;
    private final Context context;

    public OrderAdapter(List<Order> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        final Order order = orderList.get(position);
        holder.foodImageView.setImageResource(order.getImage());
        holder.nameTextView.setText(order.getFoodName());
        holder.priceTextView.setText(order.getPrice());
        holder.orderNumber.setText(order.getOrderNumber());
        holder.quantityTextView.setText(order.getQuantity()+"");

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("foodName", order.getFoodName());
            intent.putExtra("image", order.getImage());
            intent.putExtra("desc", order.getDescription());
            intent.putExtra("price", order.getPrice());
            intent.putExtra("name", order.getName());
            intent.putExtra("phone", order.getPhone());
            intent.putExtra("quantity", order.getQuantity());
            intent.putExtra("orderId", order.getOrderNumber());
            context.startActivity(intent);
        });
    }

    public void setData(List<Order> data){
        orderList = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        ImageView foodImageView;
        TextView nameTextView, priceTextView, orderNumber, quantityTextView;
        CardView cardView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImageView = itemView.findViewById(R.id.iv_food);
            nameTextView = itemView.findViewById(R.id.tv_name);
            priceTextView = itemView.findViewById(R.id.tv_price);
            orderNumber = itemView.findViewById(R.id.tv_order_number);
            quantityTextView = itemView.findViewById(R.id.tv_quantity);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
