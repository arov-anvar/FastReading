package com.example.fastreding.present;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;

import com.example.fastreding.MainContract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public List<String> getArrayNumbers() {
        List<String> array = new ArrayList<>();
        for (Integer i = 1; i <= 25; i++) {
            array.add(i.toString());
        }
        Collections.shuffle(array);
        return array;
    }

    // дописать
}
