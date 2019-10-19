package com.example.fastreding.present;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;

import com.example.fastreding.MainContract;
import com.example.fastreding.db.DatabaseHelper;
import com.example.fastreding.db.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PresenterTableSchulte {

    private Context myContext;
    private Model model;

    public PresenterTableSchulte(Context context) {
        model = new Model(context);
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

    public Integer getRecord() {
        ArrayList<Integer> a = getPastResult();
        return Collections.max(a);
    }

    public ArrayList<Integer> getPastResult()  {
        return model.getPastResult(DatabaseHelper.TABLE_SCHULTE);
    }

    public void setResult(int point) {
        model.setResult(DatabaseHelper.TABLE_SCHULTE, point);
    }

    public Integer getCountPointFromTime(float time) {
        if (time >= 35f) return 0;
        return Math.round((350f - time*10)/3);
    }
}
