package com.anu.food;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.anu.food.databinding.ActivityDetailBinding;
import com.anu.food.db.DBHelper;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private int quantity = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail);

        int image = getIntent().getIntExtra("image", 0);
        String orderId = getIntent().getStringExtra("orderId");
        String foodName = getIntent().getStringExtra("foodName");
        String price = getIntent().getStringExtra("price");
        String desc = getIntent().getStringExtra("desc");
        quantity = getIntent().getIntExtra("quantity", 0);
        String name = getIntent().getStringExtra("name");
        String phone = getIntent().getStringExtra("phone");

        binding.ivImage.setImageResource(image);
        binding.tvName.setText(foodName);
        binding.tvPrice.setText(price);
        binding.tvDescription.setText(desc);
        binding.tvQuantity.setText(quantity + "");
        binding.etName.setText(name);
        binding.etNumber.setText(phone);

        DBHelper dbHelper = new DBHelper(this);

        binding.buttonUpdate.setOnClickListener(v -> {
            if (binding.etName.getText().toString().length() > 0 && binding.etNumber.getText().toString().length() > 0) {
                boolean inserted = dbHelper.updateOrder(binding.etName.getText().toString().trim(),
                        binding.etNumber.getText().toString().trim(), binding.tvPrice.getText().toString(), image, desc, foodName, Integer.parseInt(binding.tvQuantity.getText().toString()), Integer.parseInt(orderId));

                if (inserted) {
                    Toast.makeText(DetailActivity.this, getString(R.string.txt_order_updated_success), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailActivity.this, getString(R.string.txt_order_added_failed), Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(DetailActivity.this, getString(R.string.txt_order_validation), Toast.LENGTH_SHORT).show();
            }

        });

        binding.buttonDelete.setOnClickListener(v -> {
            int value = dbHelper.deleteOrder(Integer.parseInt(orderId));
            if (value > 0) {
                Toast.makeText(DetailActivity.this, getString(R.string.txt_order_deleted_success), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DetailActivity.this, getString(R.string.txt_order_added_failed), Toast.LENGTH_SHORT).show();
            }
        });

        binding.ivPlus.setOnClickListener(v -> {
            quantity = quantity + 1;
            binding.tvQuantity.setText(quantity + "");
        });

        binding.ivMinus.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity = quantity - 1;
                binding.tvQuantity.setText(quantity + "");
            }
        });

    }
}