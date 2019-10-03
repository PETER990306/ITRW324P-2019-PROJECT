package com.itrw324.lights_on;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public Database(Context context){
        super(context,"Login.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase userDatabase) {
        userDatabase.execSQL("Create table Users(product_id number primary key,email text,cellphone text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addUser(int product_id,String email, String cellphone,String password){
        SQLiteDatabase userdb = this.getWritableDatabase();
        ContentValues contentValues  = new ContentValues();
        contentValues.put("Product ID",product_id);
        contentValues.put("Email",email);
        contentValues.put("Cellphone",cellphone);
        contentValues.put("Password",password);

        long insert = userdb.insert("Users",null,contentValues);
        if (insert==1){
            return false;
        }
        else return true;

    }

    public boolean emailVerification(String email){
        SQLiteDatabase userdb = this.getReadableDatabase();
        Cursor cursor = userdb.rawQuery("SELECT * FROM users WHERE email=?",new String[]{(email)});

        if (cursor.getCount()>0){
            return false;
        }
        else{
            return true;
        }
    }
}
