package com.example.fastreding.present;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;

import com.example.fastreding.MainContract;

public class PresenterTableSchulte {

    private Context myContext;

    public PresenterTableSchulte(Context context) {
        myContext = context;
    }

    public int getWidth(Display display) {
        Display myDisplay = display;
        Point size = new Point();
        myDisplay.getSize(size);
        return size.x;
    }

    public String[] getArrayNumbers() {
        String[] arr = new String[25];
        for (Integer i = 0; i < 25; ++i) {
            arr[i] = Integer.toString(i + 1);
        }
        return arr;
    }
}
