package com.anu.food;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.anu.food.adapter.FoodAdapter;
import com.anu.food.databinding.ActivityFoodListBinding;
import com.anu.food.model.Food;

import java.util.ArrayList;
import java.util.List;

public class FoodListActivity extends AppCompatActivity {

    ActivityFoodListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_list);

        List<Food> foodList = new ArrayList<>();

        foodList.add(new Food(R.drawable.ic_parupu_vadai,"Paruppu Vadai","$ 1", "Moru Moru paruppu Vadai"));
        foodList.add(new Food(R.drawable.ic_uluntha_vadai,"Uluntha Vadai","$ 0.5", "Moru Moru uluntha Vadai"));
        foodList.add(new Food(R.drawable.ic_burger,"Beef Burger","$ 3", "Tasty, delicious, and has me craving more on the first bite"));
        foodList.add(new Food(R.drawable.ic_pizza,"Yum Yum Pizza","$ 5", "Bread dough topped with some combination of olive oil, tomato, olives, mozzarella"));
        foodList.add(new Food(R.drawable.ic_pasta,"White Pasta","$ 2.5", "They include shapes that resemble shells, bow ties, spirals, snails, wheels and radiators"));

        FoodAdapter foodAdapter = new FoodAdapter(foodList,this);
        binding.rvFood.setAdapter(foodAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvFood.setLayoutManager(layoutManager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(FoodListActivity.this, OrderListActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}