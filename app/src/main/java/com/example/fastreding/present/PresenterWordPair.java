package com.example.fastreding.present;

import android.content.Context;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;

import java.util.ArrayList;
import java.util.Random;

public class PresenterWordPair implements MainContract.Presenter {

    final private Integer ID_EXERCISE = 1;

    private Integer level;
    private ArrayList<Integer> lastTenResult;
    private ArrayList<String> words;
    private Integer record;
    private Context context;

    public PresenterWordPair(Context context) {
        this.context = context;
        //сделать вывод из бд
        level = 1;
        lastTenResult = new ArrayList<>();
        record = 45;
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

    @Override
    public void setResult(int point) {
        // добавление в бд результата
    }

    public Integer getLevel() {
        return level;
    }

    public void changeLevel(boolean ans) {
        if (ans) ++level;
        else --level;
        if (level < 1) level = 1;
    }
}
