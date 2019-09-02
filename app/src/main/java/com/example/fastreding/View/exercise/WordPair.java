package com.example.fastreding.View.exercise;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fastreding.MainContract;
import com.example.fastreding.R;
import com.example.fastreding.View.ResultExercise;
import com.example.fastreding.db.DatabaseHelper;
import com.example.fastreding.present.*;

public class WordPair extends AppCompatActivity implements MainContract.ViewExercise {

    final private int BORDER_CHANGE_TIME = 200;
    final private int COUNT_TEST = 10;
    final private int VIEW_COUNT = 5;
    final private String name = "Слово-пары";

    private EditText wordsWatch;
    private Button checkAnswer;
    private Integer countPoint = 0;
    private int timeViewWords = 500;
    private Boolean state = false;
    private Integer counterBackground = 0;
    private Integer counter = 0;
    private String rightAnswer;
    private PresenterWordPair presenter;
    private SharedPreferences preferences;

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
        checkAnswer = (Button) findViewById(R.id.checkAnswer);
        presenter = new PresenterWordPair(getApplicationContext());
        rightAnswer = presenter.getWords();
    }

    @Override
    public void preparationToStart() {
        final Handler handler = new Handler();
        wordsWatch.setClickable(false);
        wordsWatch.setText("");
        handler.post(new Runnable() {
            @Override
            public void run() {
                ++counterBackground;
                if (state) {
                    state = false;
                    wordsWatch.setBackground(getResources().getDrawable(R.drawable.border_edit));
                } else {
                    state = true;
                    wordsWatch.setBackground(getResources().getDrawable(R.color.colorWhite));
                }
                handler.postDelayed(this, BORDER_CHANGE_TIME);
                if (counterBackground == VIEW_COUNT) {
                    handler.removeCallbacks(this);
                    counterBackground = 0;
                    state = false;
                    startExercise();
                }
            }
        });
    }

    @Override
    public void startExercise() {
        rightAnswer = presenter.getWords();
        wordsWatch.setText(rightAnswer);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                wordsWatch.setText("");
                wordsWatch.setClickable(true);
                checkAnswer.setClickable(true);
            }
        }, timeViewWords);
    }

    @Override
    public void checkAnswer(View view) {
        checkAnswer.setClickable(false);
        String answer = wordsWatch.getText().toString().toLowerCase();
        ++counter;
        if (answer.equals(rightAnswer.toLowerCase())) {
            wordsWatch.setBackground(getResources().getDrawable(R.color.yellow));
            countPoint += presenter.getLevel();
            presenter.changeLevel(true);
        } else {
            wordsWatch.setBackground(getResources().getDrawable(R.color.red));
            presenter.changeLevel(false);
        }
        if (counter < COUNT_TEST) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    preparationToStart();
                }
            }, 1000);
        } else exerciseEnd();
    }

    @Override
    public void exerciseEnd() {
        //добавление результа упражнения
        presenter.setResult(countPoint);

        // Добавление уровня по оканчанию игры
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.edit().putInt("level", countPoint).apply();

        Intent intent = new Intent(WordPair.this, ResultExercise.class);
        intent.putExtra("countPoint", countPoint.toString());       //передача количесво очков
        intent.putExtra("exerciseName", this.name);                 //         название упражнения
        intent.putExtra("record", presenter.getRecord().toString());//         рекорда
        intent.putIntegerArrayListExtra("pastResults", presenter.getPastResult());
        startActivity(intent);
    }
}
