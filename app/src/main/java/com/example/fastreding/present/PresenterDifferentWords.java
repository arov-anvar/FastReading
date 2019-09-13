package com.example.fastreding.present;

import android.content.Context;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;
import com.example.fastreding.db.DatabaseHelper;
import com.example.fastreding.db.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PresenterDifferentWords implements MainContract.Presenter {

    private Model model;
    private Context myContext;

    public PresenterDifferentWords(Context context) {
        model = new Model(context);
        myContext = context;
    }

    @Override
    public void setResult(int point) {
        model.setResult(DatabaseHelper.TABLE_DIFFERENT_WORDS, point);
    }

    public ArrayList<Integer> getPastResult() {
        return model.getPastResult(DatabaseHelper.TABLE_DIFFERENT_WORDS);
    }

    public Integer getRecord() {
        return Collections.max(getPastResult());
    }

    private int[] getRandomNotRepeatNumber() {
        int[] count = new int[5];
        ArrayList<Integer> countSample = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            countSample.add(i);
        }
        Collections.shuffle(countSample);

        for (int i = 0; i < 5; i++) {
            count[i] = countSample.get(i);
        }

        return count;
    }

    private String[] getRandomWords() {
        String[] arr = myContext.getResources().getStringArray(R.array.different_words);
        List<String> shuffleArr = Arrays.asList(arr);
        Collections.shuffle(shuffleArr);
        arr = new String[15];
        for (int i = 0; i < 15; i++) {
            arr[i] = shuffleArr.get(i) + "\n" + shuffleArr.get(i);
        }
        return arr;
    }

    private String getRandomLetter(String notThis) {
        Random r = new Random();
        int c = r.nextInt(26) + (byte)'a';
        String str = String.valueOf((char)c);
        if (str.equals(notThis)) {
            if (!str.equals("z"))
                return String.valueOf((char)((byte)str.charAt(0) + 1));
            else return "x";
        }
        return str;
    }

    public String[] getArrayWords() {
        Random r = new Random();
        int[] arrRightWordsPosition = getRandomNotRepeatNumber();
        String[] arr = getRandomWords();

        for (int i = 0; i < 5; i++) {
            String word = arr[arrRightWordsPosition[i]];
            int pos = r.nextInt(word.length());
            arr[arrRightWordsPosition[i]] = word.substring(0, pos) +
                                            getRandomLetter(word.substring(pos, pos + 1)) +
                                            word.substring(pos + 1);
        }
        return arr;
    }

}
