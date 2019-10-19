package com.example.fastreding.present;

import android.content.Context;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;
import com.example.fastreding.db.DatabaseHelper;
import com.example.fastreding.db.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PresenterReadingSpeed implements MainContract.Presenter {

    private Context myContext;
    private Model model;

    public PresenterReadingSpeed(Context context) {
        myContext = context;
        model = new Model(context);
    }

    private int getRandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    public String selectRandomText() {
        String[] arrText = myContext.getResources().getStringArray(R.array.text);
        return arrText[getRandomNumber(arrText.length)];
    }

    public int getCountWords(String str) {
        int count = 0;
        for (char chr : str.toCharArray()) {
            if (chr == ' ') {
                ++count;
            }
        }
        return count;
    }

    public ArrayList<Integer> getPastResult() {
        return model.getPastResult(DatabaseHelper.TABLE_READING_SPEED);
    }

    public Integer getRecord() {
        return Collections.max(getPastResult());
    }

    @Override
    public void setResult(int point) {
        model.setResult(DatabaseHelper.TABLE_READING_SPEED, point);
    }
}
