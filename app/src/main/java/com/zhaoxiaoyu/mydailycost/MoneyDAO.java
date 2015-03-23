package com.zhaoxiaoyu.mydailycost;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoxiaoyu on 15/3/19.
 */
public class MoneyDAO extends SQLiteOpenHelper{

    private SQLiteDatabase db;
    private static String DATABASE_NAME = "MyDailyCost";
    private static int DATABASE_VERSION = 1;
    public static final String CREATE_MONEY = "create table Money (" +
            "id integer primary key autoincrement, " +
            "amount real, " +
            "purpose text, " +
            "remark text, " +
            "date timestamp, " +
            "amount_type integer " +
            ")";

    public MoneyDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public MoneyDAO(Context context){
        this(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MONEY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            //no breaks
            default:
        }
    }

    /**
     * 添加一笔记
     * @return 添加成功返回 true，失败返回 false
     */
    public boolean addOneRecord(Record record){
        boolean isSuccessful = false;
        try {
            ContentValues values = new ContentValues();
            values.put("amount", record.getAmount());
            values.put("purpose", record.getPurpose());
            values.put("remark", record.getRemark());
            values.put("date", record.getDate().getTime());
            values.put("amount_type", record.getAmountType());
            if(!db.isOpen()){
                db = getWritableDatabase();
            }
            db.beginTransaction();
            db.insert("Money", null, values);
            db.setTransactionSuccessful();
            isSuccessful = true;
        } catch (Exception e){
            isSuccessful = false;
        } finally{
            db.endTransaction();
            db.close();
        }
        return isSuccessful;
    }

    public List<Record> getAllRecords(){
        List<Record> recordList = new ArrayList<Record>();
        try{
            if(!db.isOpen()){
                db = getWritableDatabase();
            }
            Cursor cursor = db.rawQuery("select amount, date from money", null);
            while(cursor.moveToNext()){
                Record record = new Record();
                record.setAmount(cursor.getDouble(0));
                record.setDate(new Date(cursor.getInt(1)));
                recordList.add(record);
            }
            cursor.close();
        } catch (Exception e){
            Log.e("getAllRecords", e.toString());
        }
        return recordList;
    }

}
