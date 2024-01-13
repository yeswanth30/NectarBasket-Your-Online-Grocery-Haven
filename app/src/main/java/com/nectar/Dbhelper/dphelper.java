package com.nectar.Dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.util.Log;

import com.nectar.Retrofitclient.mycartmodel;
import com.nectar.Retrofitclient.mycartmodel12;
import com.nectar.Retrofitclient.mylikemodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dphelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database";
    private static final int DATABASE_VERSION = 17;
    private static final String My_Cart_table = "my_cart";
    private static final String Like_table = "like";
    private static final String id = "id";
    private static final String userid = "userid";
    private static final String Product_id = "product_id";
    private static final String Category_id = "category_id";
    private static final String Product_name = "name";
    private static final String Quantity = "quantity";
    private static final String finalprice = "final";
    private static final String cashbackprice = "cashbackprice";
    private static final String cashbackdate = "cashbackdate";
    private static final String cashbackpercentage = "cashbackpercentage";
    private static final String Price = "price";
    private static final String Photo_cart = "image";
    private static final String status = "status";
    private static final String date_time = "date_time";
    private static final String id_like = "id";
    private static final String Product_id_like = "product_id_like";
    private static final String Category_id_like = "category_id";
    private static final String Product_name_like = "product_name_like";
    private static final String Quantity_like = "quantity_like";
    private static final String Price_like = "price_like";
    private static final String Photo = "photo";
    private static final String CREATE_My_Cart_table = "CREATE TABLE " + My_Cart_table + "("
            + id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Product_id + " INTEGER,"
            + userid + " TEXT,"
            + Category_id + " INTEGER,"
            + Product_name + " TEXT,"
            + Quantity + " TEXT,"
            + finalprice + " TEXT,"
            + cashbackprice + " TEXT DEFAULT 0,"  // Set default value as 0
            + cashbackdate + " TEXT DEFAULT 0,"    // Set default value as 0
            + cashbackpercentage + " TEXT DEFAULT 0,"  // Set default value as 0
            + Photo_cart + " TEXT ,"
            + status + " TEXT DEFAULT 'Pending',"
            + date_time + " TEXT ,"
            + Price + " TEXT " + ")";
    private static final String CREATE_Like_table = "CREATE TABLE " + Like_table + "( "
            + id_like + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + Product_id_like + " INTEGER,"
            + Category_id_like + " INTEGER,"
            + Product_name_like + " TEXT,"
            + Quantity_like + " TEXT,"
            + Photo + " TEXT ,"
            + Price_like + " TEXT " + ")";

    public dphelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_My_Cart_table);
        db.execSQL(CREATE_Like_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + My_Cart_table);
        db.execSQL("DROP TABLE IF EXISTS " + Like_table);

        onCreate(db);
    }

    public void addcart(mycartmodel cart, String dates, String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Product_id, cart.getProduct_id());
        values.put(userid, cart.getUserid());
        values.put(Category_id, cart.getCat_id());
        Log.e("from db", String.valueOf(cart.getProduct_id()));
        Log.e("from db", String.valueOf(cart.getCat_id()));
        values.put(Price, cart.getPrice());
        values.put(Quantity, cart.getQunatity());
        values.put(finalprice, cart.getFinal_price());
        values.put(Product_name, cart.getProduct_name());
        values.put(Photo_cart, cart.getImage());
        values.put(date_time, dates);
        db.insert(My_Cart_table, null, values);
        db.close();
    }

    public void addlike(mylikemodel like) {
        SQLiteDatabase db = this.getWritableDatabase();

        if (!isCategoryExists(db, like.getCat_id())) {
            ContentValues values = new ContentValues();
            values.put(Product_id_like, like.getProduct_id());
            values.put(Category_id_like, like.getCat_id());
            values.put(Price_like, like.getPrice());
            values.put(Quantity_like, like.getQunatity());
            values.put(Product_name_like, like.getProduct_name());
            values.put(Photo, like.getImage());

            db.insert(Like_table, null, values);
        }

        db.close();
    }

    private boolean isCategoryExists(SQLiteDatabase db, int categoryId) {
        String query = "SELECT COUNT(*) FROM " + Like_table +
                " WHERE " + Category_id_like + " = ?";

        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(categoryId)});

        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }

        cursor.close();
        return count > 0;
    }


    public List<mylikemodel> getlike() {
        String query = "SELECT * FROM " + Like_table +
                " GROUP BY " + Category_id_like +
                " HAVING COUNT(" + Category_id_like + ") = 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(query, null);
        List<mylikemodel> like = new ArrayList<>();

        if (cur.moveToFirst()) {
            do {
                String product_name = cur.getString(cur.getColumnIndexOrThrow(Product_name_like));
                String price = cur.getString(cur.getColumnIndexOrThrow(Price_like));
                String quantity = cur.getString(cur.getColumnIndexOrThrow(Quantity_like));
                int product_id_like = cur.getInt((cur.getColumnIndexOrThrow(Product_id_like)));
                int category_id = cur.getInt((cur.getColumnIndexOrThrow(Category_id_like)));
                String image = cur.getString((cur.getColumnIndexOrThrow(Photo)));

                like.add(new mylikemodel(product_name, price, product_id_like, category_id, quantity, image));
                Log.e("all", price);
            }
            while (cur.moveToNext());
        }

        cur.close();
        return like;
    }


    public ArrayList<mycartmodel12> getcart() {
        String query = "SELECT * FROM " + My_Cart_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(query, null);
        ArrayList<mycartmodel12> cart = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                String name = cur.getString(cur.getColumnIndexOrThrow(Product_name));
                String quantity = cur.getString(cur.getColumnIndexOrThrow(Quantity));
                String price = cur.getString(cur.getColumnIndexOrThrow(Price));
                Date d = new Date();
                CharSequence date = DateFormat.format("dd MM yyyy", d.getTime());
                int id = cur.getInt((cur.getColumnIndexOrThrow(Category_id)));
                String image = cur.getString((cur.getColumnIndexOrThrow(Photo_cart)));
                String color = "Black";
                String final_price = cur.getString(cur.getColumnIndexOrThrow(Price));

                cart.add(new mycartmodel12(name, quantity, price, (String) date, id, image, color, final_price, cashbackprice, cashbackdate, cashbackpercentage ));
                Log.e("all", price);
            }
            while (cur.moveToNext());
        }
        cur.close();
        return cart;
    }

    public ArrayList<mycartmodel> getcarting() {
        String query = "SELECT * FROM " + My_Cart_table;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery(query, null);
        ArrayList<mycartmodel> cartss = new ArrayList<>();
        if (cur.moveToFirst()) {
            do {
                String name = cur.getString(cur.getColumnIndexOrThrow(Product_name));
                String quantity = cur.getString(cur.getColumnIndexOrThrow(Quantity));
                String price = cur.getString(cur.getColumnIndexOrThrow(Price));
                int category_id = cur.getInt((cur.getColumnIndexOrThrow(Category_id)));
                int productid = cur.getInt((cur.getColumnIndexOrThrow(Product_id)));
                String image = cur.getString((cur.getColumnIndexOrThrow(Photo_cart)));

                cartss.add(new mycartmodel(name, quantity, price, category_id, productid, image));
                Log.e("catall", String.valueOf(category_id));
                Log.e("proall", String.valueOf(productid));
            }
            while (cur.moveToNext());
        }
        cur.close();
        return cartss;
    }

    public void deleteCartItem(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(My_Cart_table, Product_id + " = ?", new String[]{String.valueOf(productId)});
        db.close();
    }

    public void deleteLikeItem(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Like_table, Product_id_like + " = ?", new String[]{String.valueOf(productId)});
        db.close();
    }
}
