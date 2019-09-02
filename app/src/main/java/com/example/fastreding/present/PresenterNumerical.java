package com.example.fastreding.present;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.fastreding.MainContract;

import java.util.Random;

public class PresenterNumerical implements MainContract.Presenter {

    private Context context;
    private int level;
    SharedPreferences preferences;

    PresenterNumerical(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        level = preferences.getInt("level", 0);
    }

    @Override
    public void setResult(int point) {

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

    public String createUnderLine(int count) {
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
}
