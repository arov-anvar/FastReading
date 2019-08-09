package com.example.fastreding.View.exercise;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;
import com.example.fastreding.present.*;

public class WordPair extends AppCompatActivity implements MainContract.ViewExercise {

    final private int BORDER_CHANGE_TIME = 200;
    final private int COUNT_TEST = 10;
    final private int VIEW_COUNT = 5;

    private EditText wordsWatch;
    private int countRightAnswer = 0;
    private int timeViewWords = 500;
    private Boolean state = false;
    private Integer counter = 0;
    private String rightAsnwer;
    private PresenterWordPair presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_pair);
        init();
        preparationToStart();
    }

    @Override
    public void init() {
        wordsWatch = (EditText) findViewById(R.id.wordsWatch);
        presenter = new PresenterWordPair();
    }

    @Override
    public void preparationToStart() {
        final Handler handler = new Handler();
        wordsWatch.setClickable(false);
        wordsWatch.setText("");
        handler.post(new Runnable() {
            @Override
            public void run() {
                ++counter;
                if (state) {
                    state = false;
                    wordsWatch.setBackground(getResources().getDrawable(R.drawable.border_edit));
                } else {
                    state = true;
                    wordsWatch.setBackground(getResources().getDrawable(R.color.colorWhite));
                }
                handler.postDelayed(this, BORDER_CHANGE_TIME);
                if (counter == VIEW_COUNT) {
                    handler.removeCallbacks(this);
                    counter = 0;
                    state = false;
                    startExercise();
                }
            }
        });
    }

    @Override
    public void startExercise() {
        rightAsnwer = presenter.getWords();
        wordsWatch.setText("гном пока");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                wordsWatch.setText("");
                wordsWatch.setClickable(true);
            }
        }, timeViewWords);
    }

    @Override
    public void checkAnswer(View view) {
        String answer = wordsWatch.getText().toString().toLowerCase();
        //добавить точку входа
        String rightAnswer = "гном пока";
        if (answer.equals(rightAnswer)) {
            wordsWatch.setBackground(getResources().getDrawable(R.color.yellow));
            ++countRightAnswer;
            // добавить счетчик
        } else wordsWatch.setBackground(getResources().getDrawable(R.color.red));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                preparationToStart();
            }
        }, 1000);
    }

    @Override
    public int changeLevel(int nowLevel, boolean lastAnswer) {
        return 0;
    }

    @Override
    public void exerciseEnd() {

    }
}
