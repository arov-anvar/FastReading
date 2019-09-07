package com.example.fastreding.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Model {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor myCursor;

    public Model(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void setResult(String tableName, int point) {
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_COUNT_POINT, point);
        // надо доработать id пользователя
        cv.put(DatabaseHelper.COLUMN_USERS_ID, 1);
        db.insert(tableName, null, cv);
        db.close();
    }

    public ArrayList<Integer> getPastResult(String tableName) {
        db = dbHelper.getReadableDatabase();
        ArrayList<Integer> outArray = new ArrayList<>();
        myCursor = db.rawQuery("SELECT * FROM " + tableName, null);
        if (myCursor.getCount() < 1) return null;
        myCursor.moveToFirst();
        for (int i = 0; i < myCursor.getCount(); ++i) {
            outArray.add(myCursor.getInt(1));//уточнить какой столбец
            myCursor.moveToNext();
        }
        db.close();
        return outArray;
    }
}
