package com.example.payfast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9820969516,'Ayush',10000,'ayush@gmail.com','012345678911','PYFT1234567')");
        db.execSQL("insert into user_table values(9769359179,'Ajay',10000,'ajay@gmail.com','123456789012','PYST1234567')");
        db.execSQL("insert into user_table values(9724316366,'Dhruvil',10000,'dhruvil@gmail.com','123434345667','PYST1234567')");
        db.execSQL("insert into user_table values(9925813262,'Dhyanesh',10000,'dhyanesh@gmail.com','123456767899','PYST1234567')");
        db.execSQL("insert into user_table values(7874570524,'Adarsh',10000,'adarsh@gmail.com','98765645553','PYST1234567')");
        db.execSQL("insert into user_table values(8237457866,'Pulkit',10000,'pulkit@gmail.com','456784536779','PYST1234567')");
        db.execSQL("insert into user_table values(9624742385,'Tushar',10000,'tushar@gmail.com','2345645366787','PYST1234567')");
        db.execSQL("insert into user_table values(8879486944,'Snehil',10000,'snehil@gmail.com','4365476432423','PYST1234567')");
        db.execSQL("insert into user_table values(7224069001,'Pavitra',10000,'pavitra@gmail.com','23465765422435','PYST1234567')");
        db.execSQL("insert into user_table values(9351896820,'Kritagya',10000,'kritagya@gmail.com','3875475890594','PYST1234567')");}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
