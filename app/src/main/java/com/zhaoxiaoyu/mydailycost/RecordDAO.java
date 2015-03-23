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
public class RecordDAO extends SQLiteOpenHelper{

    private static String DATABASE_NAME = "MyDailyCost";
    private static int DATABASE_VERSION = 1;
    public static final String CREATE_MONEY = "create table COST_RECORD(" +
            "id integer primary key autoincrement, " +
            "amount real, " +
            "purpose text, " +
            "remark text, " +
            "date timestamp, " +
            "amount_type integer " +
            ")";

    public RecordDAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    public RecordDAO(Context context){
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
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("amount", record.getAmount());
            values.put("purpose", record.getPurpose());
            values.put("remark", record.getRemark());
            values.put("date", record.getDate().getTime());
            values.put("amount_type", record.getAmountType());
            db.beginTransaction();
            db.insert("COST_RECORD", null, values);
            db.setTransactionSuccessful();
            isSuccessful = true;
        } catch (Exception e){
            isSuccessful = false;
        } finally{
            db.endTransaction();
            if(db!=null){
                db.close();
            }
        }
        return isSuccessful;
    }

    public List<Record> getAllRecords(){
        Log.d("RecordDAO", "getAllRecords executed!");
        SQLiteDatabase db = null;
        Cursor cursor = null;
        List<Record> recordList = new ArrayList<Record>();
        try{
            db = getWritableDatabase();
            cursor = db.rawQuery("select amount, date, amount_type, purpose from COST_RECORD", null);
            while(cursor.moveToNext()){
                Record record = new Record();
                record.setAmount(cursor.getDouble(0));
                record.setDate(new Date(cursor.getLong(1)));
                record.setAmountType(cursor.getInt(2));
                record.setPurpose(cursor.getString(3));
                recordList.add(record);
                Log.d("RecordDAO", record.toString());
            }
        } catch (Exception e){
            Log.e("getAllRecords", e.toString());
        } finally{
            if(cursor!=null){
                cursor.close();
            }
            if(db!=null){
                db.close();
            }
        }
        return recordList;
    }

}
