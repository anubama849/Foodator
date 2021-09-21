package com.anu.food;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.anu.food.adapter.OrderAdapter;
import com.anu.food.databinding.ActivityOrderListBinding;
import com.anu.food.db.DBHelper;

public class OrderListActivity extends AppCompatActivity {

    ActivityOrderListBinding binding;
    OrderAdapter orderAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_list);

         dbHelper = new DBHelper(this);

        orderAdapter = new OrderAdapter(dbHelper.getOrdersList(),this);
        binding.rvOrders.setAdapter(orderAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvOrders.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        orderAdapter.setData(dbHelper.getOrdersList());
    }
}