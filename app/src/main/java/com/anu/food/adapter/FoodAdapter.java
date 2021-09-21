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

import com.anu.food.OrderActivity;
import com.anu.food.R;
import com.anu.food.model.Food;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private final List<Food> foodList;
    private final Context context;

    public FoodAdapter(List<Food> foodList, Context context) {
        this.foodList = foodList;
        this.context = context;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_food_item, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.FoodViewHolder holder, int position) {

        final Food food = foodList.get(position);
        holder.foodImageView.setImageResource(food.getImage());
        holder.nameTextView.setText(food.getName());
        holder.priceTextView.setText(food.getPrice());
        holder.descriptionTextView.setText(food.getDescription());

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, OrderActivity.class);
            intent.putExtra("name", food.getName());
            intent.putExtra("image", food.getImage());
            intent.putExtra("desc", food.getDescription());
            intent.putExtra("price", food.getPrice());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder {

        ImageView foodImageView;
        TextView nameTextView, priceTextView, descriptionTextView;
        CardView cardView;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodImageView = itemView.findViewById(R.id.iv_food);
            nameTextView = itemView.findViewById(R.id.tv_name);
            priceTextView = itemView.findViewById(R.id.tv_price);
            descriptionTextView = itemView.findViewById(R.id.tv_description);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
