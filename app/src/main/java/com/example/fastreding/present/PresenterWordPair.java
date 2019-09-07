package com.example.fastreding.present;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.example.fastreding.MainContract;
import com.example.fastreding.R;
import com.example.fastreding.db.DatabaseHelper;
import com.example.fastreding.db.Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PresenterWordPair implements MainContract.Presenter {

    final private Integer ID_EXERCISE = 1;
    private Model model;
    private Integer level;
    private Context context;
    private SharedPreferences preferences;

    public PresenterWordPair(Context context) {
        this.context = context;
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        model = new Model(context);
        level = preferences.getInt("level", 0);
    }

    public String[] getResFromLevel(Integer lvl) {
        switch (lvl) {
            case 0:
            case 1:
                return context.getResources().getStringArray(R.array.word_pair_4);
            case 2:
            case 3:
                return context.getResources().getStringArray(R.array.word_pair_5);
            case 4:
            case 5:
                return context.getResources().getStringArray(R.array.word_pair_6);
            case 6:
            case 7:
                return context.getResources().getStringArray(R.array.word_pair_7);
            case 8:
            case 9:
                return context.getResources().getStringArray(R.array.word_pair_8);
        }
        return null;
    }

    @Override
    public void setResult(int point) {
        model.setResult(DatabaseHelper.TABLE_WORD_PAIR, point);
    }

    public ArrayList<Integer> getPastResult() {
        return model.getPastResult(DatabaseHelper.TABLE_WORD_PAIR);
    }

    private String[] mixArray(String[] arr) {
        Random random = new Random();
        for (int i = 1; i < arr.length; i++) {
            int j = random.nextInt(i);
            String str = arr[i];
            arr[i] = arr[j];
            arr[j] = str;
        }
        return arr;
    }

    public String getWords() {
        String[] arrWords = getResFromLevel(level);
        arrWords = mixArray(arrWords);
        return arrWords[0] + " " + arrWords[1];
    }

    public Integer getLevel() {
        return level;
    }

    public void changeLevel(boolean ans) {
        if (ans) ++level;
        else --level;
        if (level < 1) level = 1;
    }

    public Integer getRecord() {
        return Collections.max(getPastResult());
    }
}
