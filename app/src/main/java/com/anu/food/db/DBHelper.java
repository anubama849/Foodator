package com.anu.food.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.anu.food.model.Order;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    final static String dbName = "foodator.db";
    final static int dbVersion = 1;

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table orders " +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price text," +
                        "image int," +
                        "quantity int," +
                        "description text," +
                        "foodName text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists orders");
        onCreate(db);
    }

    public boolean insertOrder(String name, String phone, String price, int image, String description, String foodName,int quantity) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", description);
        values.put("foodName", foodName);
        values.put("quantity", quantity);

        long id = database.insert("orders", null, values);
        return id > 0;
    }


    public boolean updateOrder(String name, String phone, String price, int image, String description, String foodName,int quantity, int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", description);
        values.put("foodName", foodName);
        values.put("quantity", quantity);

        long val = database.update("orders", values, "id="+id, null);
        return val > 0;
    }


    public List<Order> getOrdersList(){
        List<Order> orderList = new ArrayList<>();
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery("select id,foodName,image,price,name,phone,quantity from orders", null);
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()) {
                Order order = new Order();
                order.setOrderNumber(cursor.getInt(0) + "");
                order.setFoodName(cursor.getString(1));
                order.setImage(cursor.getInt(2));
                order.setPrice(cursor.getString(3));
                order.setName(cursor.getString(4));
                order.setPhone(cursor.getString(5));
                order.setQuantity(cursor.getInt(6));
                orderList.add(order);
            }
        }

        cursor.close();
        database.close();
        return orderList;
    }

    public int deleteOrder(int id){
        SQLiteDatabase database = getWritableDatabase();
       return database.delete("orders", "id="+id, null);
    }
}
