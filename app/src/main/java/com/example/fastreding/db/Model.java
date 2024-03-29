package com.example.fastreding.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fastreding.MainContract;

import java.util.ArrayList;

public class Model implements MainContract.Model {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor myCursor;

    public Model(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public void setResult(String tableName, int point) {
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_COUNT_POINT, point);
        // надо доработать id пользователя
        cv.put(DatabaseHelper.COLUMN_USERS_ID, 1);
        long a = db.insert(tableName, null, cv);
        db.close();
    }

    @Override
    public ArrayList<Integer> getPastResult(String tableName) {
        db = dbHelper.getReadableDatabase();
        ArrayList<Integer> outArray = new ArrayList<>();
        myCursor = db.rawQuery("SELECT * FROM " + tableName, null);
        if (myCursor.getCount() < 1) {
            outArray.add(0);
            myCursor.close();
            return outArray;
        }
        myCursor.moveToFirst();
        for (int i = 0; i < myCursor.getCount(); ++i) {
            outArray.add(myCursor.getInt(2));//уточнить какой столбец
            myCursor.moveToNext();
        }
        db.close();
        myCursor.close();
        return outArray;
    }

    @Override
    public String getUserName() {
        db = dbHelper.getReadableDatabase();
        myCursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_USERS, null);
        if (myCursor.getCount() < 1) return "";
        myCursor.moveToFirst();
        String name =  myCursor.getString(1);
        db.close();
        myCursor.close();
        return name;
    }

    @Override
    public void setUserName(String name) {
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        db.update(DatabaseHelper.TABLE_USERS, cv, DatabaseHelper.COLUMN_ID + "=1", null);
        db.close();
    }
}
