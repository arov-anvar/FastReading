package com.example.fastreding.present;

import android.content.Context;

import com.example.fastreding.MainContract;
import com.example.fastreding.db.DatabaseHelper;
import com.example.fastreding.db.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PresenterEvenNumbers implements MainContract.Presenter {

    private Model model;

    public PresenterEvenNumbers(Context context) {
        model = new Model(context);
    }

    @Override
    public void setResult(int point) {
        model.setResult(DatabaseHelper.TABLE_EVEN_NUMBERS, point);
    }

    private String getRandomNotOddNumber() {
        Random r = new Random();
        Integer num = r.nextInt(8999) + 1000;
        if (num % 2 == 0) return (++num).toString();
        return num.toString();
    }

    private int[] getRandomNotRepeatNumber() {
        int[] count = new int[10];
        ArrayList<Integer> countSample = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            countSample.add(i);
        }
        Collections.shuffle(countSample);

        for (int i = 0; i < 10; i++) {
            count[i] = countSample.get(i);
        }

        return count;
    }

    public String[] getArrayNumbers() {
        String[] array = new String[40];
        int[] arrRightLetterPosition = getRandomNotRepeatNumber();
        for (int i = 0; i < 40; i++) {
            array[i] = getRandomNotOddNumber();
        }

        for (int i = 0; i < 10; i++) {
            array[arrRightLetterPosition[i]] = Integer.toString(Integer.parseInt(array[arrRightLetterPosition[i]]) + 1);
        }

        return array;
    }

    public ArrayList<Integer> getPastResult() {
        return model.getPastResult(DatabaseHelper.TABLE_NUMERICAL);
    }

    public Integer getRecord() {
        return Collections.max(getPastResult());
    }

}
