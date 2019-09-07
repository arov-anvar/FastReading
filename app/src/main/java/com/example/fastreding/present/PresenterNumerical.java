package com.example.fastreding.present;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.example.fastreding.MainContract;
import com.example.fastreding.db.DatabaseHelper;
import com.example.fastreding.db.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PresenterNumerical implements MainContract.Presenter {

    private Context context;
    private int level;
    private SharedPreferences preferences;
    private Model model;

    public PresenterNumerical(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        level = preferences.getInt("level", 0);
        model = new Model(context);
    }

    @Override
    public void setResult(int point) {
        model.setResult(DatabaseHelper.TABLE_NUMERICAL, point);
    }

    public ArrayList<Integer> getPastResult() {
        return model.getPastResult(DatabaseHelper.TABLE_NUMERICAL);
    }

    public int getTimeFromLevel() {
        if (level % 2 == 0) return 500;
        else return 300;
    }

    public int getCountNumericalFromLevel() {
        switch (level) {
            case 0:
            case 1:
                return 4;
            case 2:
            case 3:
                return 5;
            case 4:
            case 5:
                return 6;
            case 6:
            case 7:
                return 7;
            case 8:
            case 9:
                return 8;
        }
        return 0;
    }

    public String createUnderLine() {
        int count = getCountNumericalFromLevel();
        String numberString = "";
        for (int i = 0; i < count; ++i) {
            numberString += i == count - 1 ? "_" : "_" + " ";
        }

        return numberString;
    }

    public String createStringNumerical(int count) {
        String numberString = "";
        Integer numerical;
        for (int i = 0; i < count; ++i) {
            Random rand = new Random();
            numerical = rand.nextInt(9);
            numberString += i == count - 1 ? numerical.toString() : numerical.toString() + " ";
        }

        return numberString;
    }

    public void changeLevel(boolean ans) {
        if (ans) ++level;
        else --level;
        if (level < 1) level = 1;
    }

    public int getLevel() {
        return level;
    }

    public Integer getRecord() {
        ArrayList<Integer> array = getPastResult();
        if (array == null) return 0;
        return Collections.max(array);
    }
}
