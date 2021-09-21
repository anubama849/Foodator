package com.anu.food;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.anu.food.databinding.ActivityOrderBinding;
import com.anu.food.db.DBHelper;

public class OrderActivity extends AppCompatActivity {

    private ActivityOrderBinding binding;
    private int quantity = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order);

        int image = getIntent().getIntExtra("image", 0);
        String foodName = getIntent().getStringExtra("name");
        String price = getIntent().getStringExtra("price");
        String desc = getIntent().getStringExtra("desc");

        binding.ivImage.setImageResource(image);
        binding.tvName.setText(foodName);
        binding.tvPrice.setText(price);
        binding.tvDescription.setText(desc);

        DBHelper dbHelper = new DBHelper(this);

        binding.buttonOrder.setOnClickListener(v -> {
            if (binding.etName.getText().toString().length() > 0 && binding.etNumber.getText().toString().length() > 0) {
              boolean inserted =  dbHelper.insertOrder(binding.etName.getText().toString().trim(),
                        binding.etNumber.getText().toString().trim(), binding.tvPrice.getText().toString(), image, desc, foodName,Integer.parseInt(binding.tvQuantity.getText().toString()));

              if(inserted){
                  Toast.makeText(OrderActivity.this, getString(R.string.txt_order_added_success), Toast.LENGTH_SHORT).show();
              }else{
                  Toast.makeText(OrderActivity.this, getString(R.string.txt_order_added_failed), Toast.LENGTH_SHORT).show();
              }

            } else {
                Toast.makeText(OrderActivity.this, getString(R.string.txt_order_validation), Toast.LENGTH_SHORT).show();
            }

        });

        binding.ivPlus.setOnClickListener(v -> {
            quantity = quantity + 1;
            binding.tvQuantity.setText(quantity+"");
        });

        binding.ivMinus.setOnClickListener(v -> {
            if(quantity>1){
                quantity = quantity - 1;
                binding.tvQuantity.setText(quantity+"");
            }
        });

    }
}