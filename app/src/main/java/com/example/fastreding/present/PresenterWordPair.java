package com.example.fastreding.present;

import android.content.Context;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;

import java.util.ArrayList;

public class PresenterWordPair implements MainContract.Presenter {

    final private Integer ID_EXERCISE = 1;

    private Integer level;
    private ArrayList<Integer> lastTenResult;
    private Integer record;
    private Context context;
    private

    public PresenterWordPair(Context context) {
        this.context = context;
        //сделать вывод из бд
        level = 1;
        lastTenResult = new ArrayList<>();
        record = 45;
    }

    public String getResFromLevel(Integer lvl) {
        String arrWords;
        lvl = --lvl / 2;
        switch (lvl) {
            case 0:
                //arrWords = context.getResources().getStringArray(R.array.name_exercise);
        }
    }

    public String getWords() {

    }

    @Override
    public void onDestroy() {

    }
}
