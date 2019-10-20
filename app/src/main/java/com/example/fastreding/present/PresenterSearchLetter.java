package com.example.fastreding.present;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.fastreding.MainContract;
import com.example.fastreding.db.DatabaseHelper;
import com.example.fastreding.db.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PresenterSearchLetter implements MainContract.Presenter {

    private String rightLetter;
    private Model model;


    public PresenterSearchLetter(Context context) {
        model = new Model(context);
    }

    @Override
    public void setResult(int point) {
        model.setResult(DatabaseHelper.TABLE_SEARCH_LETTER, point);
    }

    public ArrayList<Integer> getPastResult() {
        return model.getPastResult(DatabaseHelper.TABLE_SEARCH_LETTER);
    }

    public String getRightLetter() {
        return rightLetter;
    }

    private String getRandomLetter(String notThis) {
        Random r = new Random();
        int c = r.nextInt(26) + (byte)'a';
        String str = String.valueOf((char)c);
        if (str.equals(notThis)) {
            return String.valueOf((char)((byte)str.charAt(0) + 1));
        }
        return str;
    }

    private int[] getRandomNotRepeatNumber() {
        int[] count = new int[10];
        ArrayList<Integer> countSample = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            countSample.add(i);
        }
        Collections.shuffle(countSample);

        for (int i = 0; i < 10; i++) {
            count[i] = countSample.get(i);
        }

        return count;
    }

    public String[] getArrayLetter() {
        Random r = new Random();
        int[] arrRightLetterPosition = getRandomNotRepeatNumber();
        rightLetter = String.valueOf((char)(r.nextInt(26) + (byte)'a'));
        String[] arr = new String[100];
        for (int i = 0; i < 100; ++i) {
            arr[i] = getRandomLetter(rightLetter);
        }

        for (int i = 0; i < 10; i++) {
            arr[arrRightLetterPosition[i]] = rightLetter;
        }

        return arr;
    }

    public Integer getRecord() {
        return Collections.max(getPastResult());
    }
}
